/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

/**
 *
 * @author Alberto
 */
public class RequisitosPass {
    
	private int numCaractEspeciales;
	private int numDigitos;
	private int numMayusculas;
	private int numMinusculas;
	
	public RequisitosPass(int numCaractEspeciales, int numDigitos, int numMayusculas, int numMinusculas) {
		this.numCaractEspeciales = numCaractEspeciales;
		this.numDigitos = numDigitos;
		this.numMayusculas = numMayusculas;
		this.numMinusculas = numMinusculas;
	}
	
	public int getNumCaractEspeciales() {
		return numCaractEspeciales;
	}
	
	public void setNumCaractEspeciales(int numCaractEspeciales) {
		this.numCaractEspeciales = numCaractEspeciales;
	}
	
	public int getNumDigitos() {
		return numDigitos;
	}
	
	public void setNumDigitos(int numDigitos) {
		this.numDigitos = numDigitos;
	}
	
	public int getNumMayusculas() {
		return numMayusculas;
	}
	
	public void setNumMayusculas(int numMayusculas) {
		this.numMayusculas = numMayusculas;
	}
	
	public int getNumMinusculas() {
		return numMinusculas;
	}
	
	public void setNumMinusculas(int numMinusculas) {
		this.numMinusculas = numMinusculas;
	}
	
	//Sobreescritura de toString para una mejor visualizacion de los datos
	public String toString() {
		return "PasswordReqs{minusculas = " + this.getNumMinusculas() + ", mayusculas = " +this.getNumMayusculas()
				+ ", digitos = " + this.getNumDigitos() + ", caracteresEspeciales = " + this.getNumCaractEspeciales() + "}";
	}
	
}
