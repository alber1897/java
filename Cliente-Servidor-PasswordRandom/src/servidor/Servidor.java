/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Alberto
 */
public class Servidor {
    
		private final int PORT = 4321;
		private ServerSocket serverSocket;
		private Socket socket;
		
		public Servidor() throws IOException {
			serverSocket = new ServerSocket(PORT);
		}
		
		public void Start() throws IOException{
			
			while(true) {
				
				System.out.println("Servidor arrancado");
				System.out.println("Esperando cliente...");
				
				//Aceptamos conexion de cliente
				socket = serverSocket.accept();
				System.out.println("Cliente conectado desde " + socket.getInetAddress());
				

	            
	            try {
	            	
					//Creamos variables de entrada y salida de mensajes
	            	
	            	/*
	            	 * Los he creado dentro del bloque try porque he visto en internet que de esta forma
	            	 * te aseguras que se cierran al salir del bloque y da mayor control en los recursos
	            	 * 
	            	 */
		            DataOutputStream escribirCliente = new DataOutputStream(socket.getOutputStream());
		            
		            DataInputStream leerCliente = new DataInputStream(socket.getInputStream());
	            		
		            //Mandamos el primer mensaje establecido y a continuacion iremos mandando y recibiendo
		            //datos que necesitaremos para la elaboración de la contraseña, alojandolos en variables
	            	escribirCliente.writeUTF("Hola, soy un servidor. ¿Como te llamas?");
	            		            	
	            	//Mensaje con bienvenida y muestra de nombre
	            	String nombre;
	                if (!(nombre = leerCliente.readUTF()).isEmpty()) {
	                     escribirCliente.writeUTF("Te doy la bienvenida " + nombre);
	                     System.out.println("Nombre del cliente: " + nombre);
	                }
	                
	                escribirCliente.writeUTF("Voy a solicitarte distintos requisitos para la contraseña"
	                						+ " que voy a generar");
	                
	                escribirCliente.writeUTF("¿Cuantas minusculas debe tener la contraseña?");
	                int numMinusculas = leerCliente.readInt();
	      
	                
	                escribirCliente.writeUTF("¿Cuantas mayusculas debe tener la contraseña?");
	                int numMayusculas = leerCliente.readInt();
	                
	                escribirCliente.writeUTF("¿Cuantas digitos debe tener la contraseña?");
	                int numDigitos = leerCliente.readInt();
	                
	                escribirCliente.writeUTF("¿Cuantas caracteres especiales debe tener la contraseña?");
	                int numCaractEspeciales = leerCliente.readInt();
	                
	                //Generamos un objeto de tipo Requisitos y posteriormente creamos otro de Servicio pasando por parametro el anterior
	                RequisitosPass rq = new RequisitosPass(numCaractEspeciales, numDigitos, numMayusculas, numMinusculas);
	                ServicioPass sp = new ServicioPass(rq);
	                
	                System.out.println("Los requisitos del cliente son los siguientes: \n" + rq.toString());
	                
	                
	                //Indicamos mensaje con longitud de contraseña utilizando el metodo creado
	                escribirCliente.writeUTF("La longitud de la contraseña que se va a generar es de " + sp.longitudPass() + " caracteres");
	                System.out.println("Se ha enviado la longitud de la contraseña al cliente");
	                
	                
	                escribirCliente.writeUTF("¿Quieres generar una contraseña ahora? [si/no]");
	                String generar = leerCliente.readUTF();
	                
	                //Manejamos el mostrar contraseña o no segun decida el cliente
	                if(generar.equals("si")) {
	                	escribirCliente.writeUTF(sp.generaPass().toString());
	                	System.out.println("Se ha enviado la contraseña al cliente");
	                }else {
	                	escribirCliente.writeUTF("No se generará la contraseña. Hasta la próxima.");
	                	System.out.println("El cliente no desea generar una contraseña");
	                }
	             
	                //Manejo de excepciones
	            }catch(EOFException ex){
	            	System.out.println("Comunicacion finalizada");
	            }
	            catch (SocketException ex) {
	            	System.out.println("CONEXION FALLIDA, VUELVA A INTENTARLO!");
	            }
	            
	            System.out.println("Conexion cerrada");
	            socket.close();
			}
		}
		
		 public void finalizarServer() throws IOException {
		        serverSocket.close();
		    }
		
		
}
