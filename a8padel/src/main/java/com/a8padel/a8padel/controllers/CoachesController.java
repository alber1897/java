package com.a8padel.a8padel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.a8padel.a8padel.entities.User;
import com.a8padel.a8padel.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/profesores")
public class CoachesController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String coachController(Model model, HttpSession session) {

        if(session.getAttribute("user")==null){
            return "redirect:/index";
        }else{
            Long userLogged = (Long) session.getAttribute("user");
            User userLogin = userRepository.findById(userLogged).orElse(null);
            model.addAttribute("username", userLogin);

            return "profesores";
        }
    }
    
}
