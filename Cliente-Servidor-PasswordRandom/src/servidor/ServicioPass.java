/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;


import java.util.Random;

/**
 *
 * @author Alberto
 */
public class ServicioPass {
    
	//Declaramos los atributos de esta clase
	private RequisitosPass requisitos;
	public ServicioPass(RequisitosPass requisitos) {
		this.requisitos = requisitos;
	}
	
	//Metodo que nos va a generar la contraseña, devuelve un StringBuilder
	public StringBuilder generaPass() {
		int countDigitos = 0, countMayusculas = 0, countMinusculas = 0, countCaracteres = 0;
		int numTotal = this.longitudPass();
		
		//Creamos variables donde vamos a recuperar los datos del objeto en elm cual tenemos los requisitos
		int [] digitos = this.generarDigitos();
		char [] mayusculas = this.generarMayusculas();
		char [] minusculas = this.generarMinusculas();
		char [] caracteres = this.generarCaracteres();
		
		//Variable donde crearemos la contraseña
		StringBuilder password = new StringBuilder(); 
		
		//Array que utilizo para la generación aleatoria de caracteres en la password
		String [] tiposCaracteres = {"may","min","dig","car"};

		for (int i = 0; i < numTotal; i++) {
			
		    // Seleccionar aleatoriamente un tipo de carácter en cada vuelta del bucle
		    String tipoSeleccionado = tiposCaracteres[new Random().nextInt(tiposCaracteres.length)];
		    
		    //Utilizamos las variables que empiezan por count para saber si ya hemos añadido todos los caracteres de ese tipo
		    //Aparte comprueba a que tipo del array corresponde el aleatorio, de esta forma va añadiendo
		    
		    if (tipoSeleccionado.equals("may") && countMayusculas < this.requisitos.getNumMayusculas()) {
		        password.append(mayusculas[countMayusculas]);
		        countMayusculas++;
		    } else if (tipoSeleccionado.equals("min") && countMinusculas < this.requisitos.getNumMinusculas()) {
		        password.append(minusculas[countMinusculas]);
		        countMinusculas++;
		    } else if (tipoSeleccionado.equals("dig") && countDigitos < this.requisitos.getNumDigitos()) {
		        password.append(digitos[countDigitos]);
		        countDigitos++;
		    } else if (tipoSeleccionado.equals("car") && countCaracteres < this.requisitos.getNumCaractEspeciales()) {
		        password.append(caracteres[countCaracteres]);
		        countCaracteres++;
		    } else {
		        // Si ya se han agregado suficientes caracteres de este tipo, continuar con la siguiente iteración
		        i--;
		    }
		}
		
		
		return password;
	}

	
	public char [] generarCaracteres() {
		Random random = new Random();
		int cantidad = this.requisitos.getNumCaractEspeciales();
		//Creamos un array de tipo char con todos los caracteres deseados
		char[] caracteres = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '-', '+', '=', '.', ':', '?'}; 
		
		//Creamos un array donde va a almacenar los caracteres totales para la contraseña
		char[] caracteresGenerados = new char[cantidad];
		
		 for (int i = 0; i < cantidad; i++) {
			 	//Genera un aleatorio de la longitud del array donde se almacenan todos los caracteres para elegir un por la posicion
	            int caracterAsignado = random.nextInt(caracteres.length);
	            caracteresGenerados[i] = caracteres[caracterAsignado];
	        }
		return caracteresGenerados;
		
	}
	
	public char [] generarMayusculas() {
		Random random = new Random();
		int cantidad = this.requisitos.getNumMayusculas();
		char[] mayusculasGeneradas = new char[cantidad];
		
		 for (int i = 0; i < cantidad; i++) {
	            char letra = (char) ('a' + random.nextInt(26)); // Genera un número aleatorio entre 0 y 25 y lo convierte a una letra
	            mayusculasGeneradas[i] = Character.toUpperCase(letra);//Con el metodo upperCase convertimos la letra en mayuscula
	        }
		return mayusculasGeneradas;
		
	}
	
	public int [] generarDigitos() {
		Random random = new Random();
		
		//Obtenemos al igual que todos los metodos el numero de digitos en este caso que desea el cliente
		int cantidad = this.requisitos.getNumDigitos();
		int[] digitosGenerados = new int[cantidad];
		
		 for (int i = 0; i < cantidad; i++) {
			 
			 	//Generamos un numero aleatorio entre 0 y 9, ya que si saliera el 10 contaria como 2 numeros
	            int numero = random.nextInt(10);
	            digitosGenerados[i] = numero;
	        }
		return digitosGenerados;
		
	}
	
	public char [] generarMinusculas() {
		Random random = new Random();
		int cantidad = this.requisitos.getNumMinusculas();
		char[] minusculasGeneradas = new char[cantidad];
		
		 for (int i = 0; i < cantidad; i++) {
	            char letra = (char) ('a' + random.nextInt(26)); // Genera un número aleatorio entre 0 y 25 y lo convierte a una letra minúscula
	            minusculasGeneradas[i] = letra;
	        }
		return minusculasGeneradas;
		
	}
	
	//Metodo para obtener el numero total de caracteres que compondrá la contraseña
	public int longitudPass() {	
		return this.requisitos.getNumCaractEspeciales() + this.requisitos.getNumDigitos()
				+ this.requisitos.getNumMayusculas() + this.requisitos.getNumMinusculas();
	}
	
}
