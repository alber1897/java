/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import java.io.IOException;

/**
 *
 * @author Alberto
 */
public class MainServidor {
    
    public static void main(String[] args) throws IOException{
        
    	//Creamos objeto servidor para inicializar
    	Servidor s1 = new Servidor();
    	s1.Start();
    	
    }
}
