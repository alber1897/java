package PAC;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {

		//Para indicar que queremos usar Hibernate definimos las interfaces
        Configuration cfg = new Configuration().configure();

        //Instancia de SessionFactory en nuestra sesion
        SessionFactory sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
        Session session = sessionFactory.openSession();
		Scanner sc = new Scanner(System.in);
		int opcion;

		menu();

		do{
			System.out.println("Introduzca una opcion: ");
			opcion = sc.nextInt();
			elegirOpcion(session, opcion, sc);
		}while(opcion!=16);

		

		sc.close();
		sessionFactory.close();
		session.close();
	}


	public static void menu(){
		System.out.println(   "----------------------------------------------------"
		+"\n" + "		    BIBLIOTECA \n"
		+ "----------------------------------------------------\n"
		+ "1 - Insertar Libro\n"
		+ "2 - Listado de Libros\n"
		+ "3 - Ver Libro por ID\n"
		+ "4 - Eliminar Libro por ID\n"
		+ "5 - Actualizar Libro por ID\n"
		+ "----------------------------------------------------\n"
		+ "6 - Insertar Lector\n"
		+ "7 - Listado de Lectores\n"
		+ "8 - Ver Lector por ID\n"
		+ "9 - Eliminar Lector por ID\n"
		+ "10 - Actualizar Lector por ID\n"
		+ "----------------------------------------------------\n"
		+ "11 - Ver Libros prestados a un Lector\n"
		+ "12 - Listado de libros disponibles\n"
		+ "13 - Historial prestamos de un Lector\n"
		+ "14 - Prestar Libro\n"
		+ "15 - Devolver Libro\n"
		+ "16 - Salir\n"
		);
	}

	public static void elegirOpcion(Session session, int opcion, Scanner sc){
		sc.nextLine();
		switch (opcion) {
			case 1:
				Libro.insertarLibro(session,sc);
				break;

			case 2:
				List <Libro> libros = Libro.listadoLibros(session);
				for(Libro l : libros){
					System.out.println(l);
				}
				break;

			case 3:
				System.out.println("Introduzca ID del libro a mostrar: ");
				System.out.println(Libro.libroPorId(sc.nextInt(), session));
				break;

			case 4:
				System.out.println("Introduzca ID del libro a eliminar: ");
				System.out.println(Libro.eliminarLibro(sc.nextInt(), session));
				break;

			case 5:
				System.out.println("Introduzca ID del libro a modificar: ");
				System.out.println(Libro.actualizarLibro(sc.nextInt(), session,sc));
				break;

			case 6:
				Lector.insertarLector(session,sc);
				break;

			case 7:
				List <Lector> lectores = Lector.listadoLectores(session);
				for(Lector l : lectores){
					System.out.println(l);
				}
				break;

			case 8:
				System.out.println("Introduzca ID del lector a mostrar: ");
				System.out.println(Lector.lectorPorId(sc.nextInt(), session));
				break;

			case 9:
				System.out.println("Introduzca ID del lector a eliminar: ");
				System.out.println(Lector.eliminarLector(sc.nextInt(), session));
				break;

			case 10:
				System.out.println("Introduzca ID del lector a modificar: ");
				System.out.println(Lector.actualizarLector(sc.nextInt(), session,sc));
				break;
			
			case 11:
				System.out.println("Introduzca ID del lector: ");
				List <Libro> librosPrestados = Prestamo.prestadosLector(sc.nextInt(), session);
				if(librosPrestados!=null){
					for(Libro l : librosPrestados){
						System.out.println(l);
					}
				}else{
					System.err.println("Este lector no dispone de libros prestados");
				}
				break;

			case 12:
				
				List <Libro> librosDisponibles = Libro.listadoLibrosDisponibles(session);
				for(Libro l : librosDisponibles){
					System.out.println("Libros disponibles para prestar: ");
					System.out.println(l);
				}
				if(librosDisponibles.isEmpty()){
					System.out.println("No hay libros disponibles en este momento");
				}
				break;

			case 13:
				System.out.println("Introduzca ID del lector que desea saber su historial de libros prestados: ");
				List <Prestamo> prestamos = Prestamo.prestamosHistorial(sc.nextInt(), session);
				for(Prestamo p : prestamos){
					System.out.println(p);
				}
				break;

			case 14:
				System.out.println("Introduzca ID de libro a prestar: ");
				int idLibro = sc.nextInt();
				System.out.println("Introduzca ID de Lector al que se le presta: ");
				int idLector = sc.nextInt();

				System.out.println(Prestamo.prestarLibro(idLibro, idLector, session,sc));
				
				break;

			case 15:
				System.out.println("Introduzca ID del libro a devolver: ");
				System.out.println(Prestamo.registrarDevolucion(sc.nextInt(), session));
				
				break;

			default:

					System.out.println("Introduzca una opción válida: ");

				break;
		}
	}
}
