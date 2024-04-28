/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Alberto
 */
public class Cliente {
    
	private final String HOST = "localhost";
	private final int PORT = 4321;
	private Socket socket;
	
	public Cliente() throws  IOException {
		socket = new Socket(HOST,PORT);	
	}
	
	//Creamos metodo que inicie el cliente
	public void interactua() throws IOException {
		
		try {
		//Creamos variable de tipo datainput para poder leer lo que recibimos del server
		DataInputStream leerServer = new DataInputStream(socket.getInputStream());
		
		//Creamos variable de tipo datainput para poder escribir al server
		DataOutputStream escribirServer = new DataOutputStream(socket.getOutputStream());
		
		Scanner sc = new Scanner(System.in);
			
			//Leemos pregunta y mandamos nombre
			System.out.println(leerServer.readUTF()); 
			String nombre = sc.nextLine();
			escribirServer.writeUTF(nombre);
			
			//Estas dos lineas son para mostrar texto informativo sobre la actividad
			System.out.println(leerServer.readUTF());
			System.out.println(leerServer.readUTF()); 
			
			
			//EN EL SIGUIENTE PASO DE PARAMETROS UTILIZAMOS INPUTMISMATCHEXCEPION PARA UNA INTRODUCCIÓN CORRECTA DE DATOS
			
			//Leemos pregunta y mandamos numero de minusculas para la contraseña
			System.out.println(leerServer.readUTF()); 
			int numMinusculas = sc.nextInt();
			if(numMinusculas>=0) {
				escribirServer.writeInt(numMinusculas);
			}else {
				throw new InputMismatchException();
			}
			
			
			//Leemos pregunta y mandamos numero de mayusculas para la contraseña
			System.out.println(leerServer.readUTF()); 
			int numMayusculas = sc.nextInt();
			if(numMayusculas>=0) {
				escribirServer.writeInt(numMayusculas);
			}else {
				throw new InputMismatchException();
			}
			
			//Leemos pregunta y mandamos numero de digitos para la contraseña
			System.out.println(leerServer.readUTF()); 
			int numDigitos = sc.nextInt();
			if(numDigitos>=0) {
				escribirServer.writeInt(numDigitos);
			}else {
				throw new InputMismatchException();
			}
			
			//Leemos pregunta y mandamos numero de caracteres especiales para la contraseña
			System.out.println(leerServer.readUTF()); 
			int numCaractEspeciales = sc.nextInt();
			if(numCaractEspeciales>=0) {
				escribirServer.writeInt(numCaractEspeciales);
			}else {
				throw new InputMismatchException();
			}
				
			// Consumir el salto de línea pendiente
			sc.nextLine();
			
			//Mostramos longitud de contraseña
			System.out.println(leerServer.readUTF()); 

			//Mostramos pregunta para generar o no la contraseña
			System.out.println(leerServer.readUTF());
			String respuesta = sc.nextLine();
			escribirServer.writeUTF(respuesta);
			
			System.out.println(leerServer.readUTF());
			
			sc.close();
		}catch(EOFException ex) {
			System.out.println("Conexion cerrada excepcion");
			socket.close();
		}
		catch (InputMismatchException ex) {
			
			//AQUI ENVIAMOS ESTE MENSAJE COMO SE PIDE EN LA PAC SI SE HA INGRESADO MAL UN DATO
        	System.out.println("DATO INTRODUCIDO ERRONEAMENTE, CONEXION CLIENTE CERRADA");
        }
			
		
		
		
		
	}
}
