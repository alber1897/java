package PAC;


import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class Libro {
    private int idLibro;
    private String titulo;
    private String autor;
    private int anio_publicacion;
    private boolean disponible;

    public Libro(){
        this.disponible = true;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnio_publicacion() {
        return anio_publicacion;
    }

    public void setAnio_publicacion(int anio_publicacion) {
        this.anio_publicacion = anio_publicacion;
    }

    public boolean isDisponible() {
        if(this.disponible == true){
            return true;
        }else{
            return false;
        }
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    //Metodo para insertar un nuevo libro
    public static boolean insertarLibro(Session session, Scanner sc){

        
        Transaction t = session.beginTransaction();

        try{
            System.out.println("Introduzca titulo: " );
            String titulo = sc.nextLine();

            System.out.println("Introduzca nombre: " );
            String nombre = sc.nextLine();

            System.out.println("Introduzca año: " );
            int year = sc.nextInt();
            
            Libro libroNuevo = new Libro();
            libroNuevo.setTitulo(titulo);
            libroNuevo.setAutor(nombre);
            libroNuevo.setAnio_publicacion(year);

            session.save(libroNuevo);
            t.commit();
            System.out.println("Libro introducido correctamente");
            return true;
        }catch(Exception e){
            System.out.println("Error al introducir este libro: " + e.getMessage());
            t.rollback();
            return false;
        }
    }

    //Metodo para listar todos los libros
    public static List<Libro> listadoLibros(Session session) {
        List<Libro> libros = session.createQuery("FROM Libro", Libro.class).getResultList();
        return libros;
    }

    //Metodo para listar un libro pasandole la id
    public static Libro libroPorId(int id,Session session){
        Libro libro = session.createQuery("FROM Libro WHERE id = :id", Libro.class)
                            .setParameter("id", id)
                            .uniqueResult();
        return libro;
    }

    //Metodo para listar todos los libros disponibles para prestar
    public static List<Libro> listadoLibrosDisponibles(Session session) {
        List<Libro> libros = session.createQuery("FROM Libro WHERE disponible = 1", Libro.class).getResultList();
        return libros;
    }

    //Metodo que nos traduce el booleano en si esta disponible o no un libro
    public String disponible(){
        if(this.disponible){
            return "Disponible";
        }else{
            return "Prestado";
        }
    }

    public void actualizarDisponible(Libro libro, Session session, boolean estado){
        Transaction t = null;
        libro.setDisponible(estado);
        t = session.beginTransaction();
        session.update(libro);
        t.commit();
    }
    //Metodo para eliminar Libro segun la id
    public static String eliminarLibro(int id, Session session){
        
        Transaction t = null;
        try{
                
                Libro libro = session.createQuery("FROM Libro WHERE id = :id", Libro.class)
                .setParameter("id", id).uniqueResult();

                if(libro!=null){
                    t = session.beginTransaction();
                    session.delete(libro);
                    t.commit();
                    return "Libro eliminado correctamente!";
                }else{
                    return "El libro con la id " + id + " no existe";
                }
            }catch(HibernateException e){
                return "Error al eliminar el libro " + e.getMessage();
            }
    }

    public static  String actualizarLibro(int id,Session session, Scanner sc){
       
       Transaction t = null;
        Libro libro = libroPorId(id, session);
        sc.nextLine();
       int contador=0;

       System.out.println(libro);

       System.out.println("¿Quiere cambiar el titulo?[si/no]");
       String r1 = sc.nextLine();
        if(r1.equals("si")){
            System.out.println("Introduzca nuevo titulo: ");
            libro.setTitulo(sc.nextLine());
            contador++;
        }

        System.out.println("¿Quiere cambiar el autor?[si/no]");
        String r2 = sc.nextLine();
        if(r2.equals("si")){
            System.out.println("Introduzca nuevo autor: ");
            libro.setAutor(sc.nextLine());
            contador++;
        }

        System.out.println("¿Quiere cambiar el año?[si/no]");
        String r3 = sc.nextLine();
        if(r3.equals("si")){
            System.out.println("Introduzca nuevo año: ");
            libro.setAnio_publicacion(sc.nextInt());
            contador++;
        }
        
        if(contador>0){
            
            try{
                t = session.beginTransaction();
                session.update(libro);
                t.commit();
                return "Libro actualizado correctamente";
    
            }catch(HibernateException e){
                return "La actualizacion no ha sido posible";
            }
        }else{
            return"No has querido actualizar ningun dato. Hasta pronto.";
            
        }
        
    }

    public String toString(){
        return "ID: " + this.getIdLibro() +  " Titulo: " + this.getTitulo() + "  Autor: " + this.getAutor() + "  Año: "
                 + this.getAnio_publicacion() + "  Estado: " + this.disponible();
    }
}
