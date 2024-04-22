package PAC;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Lector {
    private int idLector;
    private String nombre;
    private String apellido;
    private String email;

    public int getIdLector() {
        return idLector;
    }

    public void setIdLector(int idLector) {
        this.idLector = idLector;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Metodo para insertar un nuevo libro
    public static boolean insertarLector(Session session, Scanner sc){

        Transaction t = session.beginTransaction();

        try{
            Lector lector = new Lector();

            System.out.println("Introduzca nombre: " );
            String nombre = sc.nextLine();

            System.out.println("Introduzca apellido: " );
            String apellido = sc.nextLine();

            System.out.println("Introduzca email: " );
            String email = sc.nextLine();
            
            
            lector.setNombre(nombre);
            lector.setApellido(apellido);;
            lector.setEmail(email);;

            session.save(lector);
            t.commit();
            System.out.println("Lector introducido correctamente");
            return true;
        }catch(Exception e){
            System.out.println("Error al introducir el lector: " + e.getMessage());
            t.rollback();
            return false;
        }
    }

    //Metodo para listar todos los libros
    public static List<Lector> listadoLectores(Session session) {
        List<Lector> lectores = session.createQuery("FROM Lector", Lector.class).getResultList();
        return lectores;
    }

    //Metodo para listar un libro pasandole la id
    public static Lector lectorPorId(int id,Session session){
        Lector lector = session.createQuery("FROM Lector WHERE id = :id", Lector.class)
                            .setParameter("id", id)
                            .uniqueResult();
        return lector;
    }


    //Metodo para eliminar Libro segun la id
    public static String eliminarLector(int id, Session session){
        
        Transaction t = null;
        try{
                
                Lector lector = session.createQuery("FROM Lector WHERE id = :id", Lector.class)
                .setParameter("id", id).uniqueResult();

                if(lector!=null){
                    t = session.beginTransaction();
                    session.delete(lector);
                    t.commit();
                    return "Lector eliminado correctamente!";
                }else{
                    return "El lector con la id " + id + " no existe";
                }
            }catch(HibernateException e){
                return "Error al eliminar el lector " + e.getMessage();
            }
    }

    public static  String actualizarLector(int id,Session session, Scanner sc){
       
       Transaction t = null;
       Lector lector = lectorPorId(id, session);
       int contador = 0;
       sc.nextLine();
       
       System.out.println(lector);
        
       System.out.println("¿Quiere cambiar el nombre?[si/no]");
       String r1 = sc.nextLine().trim();

        if(r1.equals("si")){
            System.out.println("Introduzca nuevo nombre: ");
            lector.setNombre(sc.nextLine());
            contador++;
        }

        System.out.println("¿Quiere cambiar el apellido?[si/no]");
        String r2 = sc.nextLine();
        if(r2.equals("si")){
            System.out.println("Introduzca nuevo apellido: ");
            lector.setApellido(sc.nextLine());
            contador++;
        }

        System.out.println("¿Quiere cambiar el email?[si/no]");
        String r3 = sc.nextLine();
        if(r3.equals("si")){
            System.out.println("Introduzca nuevo email: ");
            lector.setEmail(sc.nextLine());
            contador++;
        }

        if(contador>0){
            try{
                t = session.beginTransaction();
                session.update(lector);
                t.commit();
                return "Lector actualizado correctamente";
    
            }catch(HibernateException e){
                return "La actualizacion no ha sido posible";
            }
        }else{return "No has querido actualizar ningun dato. Hasta pronto.";}
       
    }

    public String toString(){
        return "ID: " + this.getIdLector() +  " Nombre: " + this.getNombre() + "  Apellido: " + this.getApellido() + "  Email: " + this.getEmail();
    }
    
}
