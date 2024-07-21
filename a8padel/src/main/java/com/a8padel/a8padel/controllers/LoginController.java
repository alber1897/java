package com.a8padel.a8padel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.a8padel.a8padel.entities.User;
import com.a8padel.a8padel.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;





    @Controller
    @RequestMapping("index")
    public class LoginController {

        @Autowired
        private UserRepository userRepository;

        @GetMapping
        public String loadLogin(HttpSession session) {
            if(session.getAttribute("user") != null){
                return "redirect:/home";
            }
            return "index";
        }
        
        @PostMapping("/login")
        public ResponseEntity<Map<String,String>> login(@RequestParam("username") String username,
                                                        @RequestParam("password") String password,
                                                        HttpSession session) {
                
                Map <String, String> response = new HashMap<>();
                try{
                    
                    User existingUser = userRepository.findByUsername(username);
                    if(existingUser != null && verifyPassword(password, existingUser.getPassword())){
                            session.setAttribute("user", existingUser.getId());
                            response.put("status","success");
                            response.put("redirect","/home");
                            return new ResponseEntity<>(response,HttpStatus.OK);
                    }else{
                        response.put("status", "error");
                        response.put("message", "Usuario o contraseña incorrectos");
                        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                    }

                }catch(Exception e){
                    response.put("status", "error");
                    response.put("message","Ha ocurrido un error al loguearse");
                    return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }
        

        @PostMapping("/register")
        public ResponseEntity <Map<String,String>> register(@RequestParam("registerUser") String username,
                                                            @RequestParam("registerPassword") String password,
                                                            @RequestParam("registerName") String name,
                                                            @RequestParam("registerLastName") String lastName,
                                                            @RequestParam("registerEmail") String email,
                                                            @RequestParam("registerPhone") int phone
                                                            ) {

            Map <String,String> response = new HashMap<>();
            
            try{
                User existingUser = userRepository.findByUsername(username);

                if(existingUser != null){
                    response.put("status", "error");
                    response.put("message","Ya existe un usuario con ese nombre");
                    return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
                }

                String hashedPassword = hashPassword(password);

                User newUser =  new User(name, lastName, username, hashedPassword, email, phone);
                userRepository.save(newUser);
                response.put("status","success");
                response.put("message", "Usuario creado con éxito");
                return new ResponseEntity<>(response,HttpStatus.OK);

            }catch(Exception e){
                response.put("status", "error");
                response.put("message", "Error al añadir el usuario");
                return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            
        }

        /*METODO PARA HASHEAR LAS CONTRASEÑAS */
        private String hashPassword(String password) throws NoSuchAlgorithmException {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(password.getBytes());
            return HexFormat.of().formatHex(hashBytes);
        }

        private boolean verifyPassword(String password, String hashedPassword) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] hashBytes = messageDigest.digest(password.getBytes());
                String hashedInputPassword = HexFormat.of().formatHex(hashBytes);
                return hashedInputPassword.equals(hashedPassword);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return false;
            }
        }

    }
