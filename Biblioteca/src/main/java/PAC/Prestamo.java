package PAC;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Prestamo {
    private int idPrestamo;
    private Date fecha_prestamo;
    private Date fecha_devolucion;
    private Libro libro;
    private Lector lector;
    

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Date getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(Date fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public static String prestarLibro(int idLibro, int idLector,Session session,Scanner sc){
        Libro libro = Libro.libroPorId(idLibro, session);
        Lector lector = Lector.lectorPorId(idLector, session);
        Transaction t = null;
        LocalDate fechaActual = LocalDate.now();
        Date fechaActualDate = Date.valueOf(fechaActual);
        
        if(libro.isDisponible()){
            
            Prestamo prestamo = new Prestamo();
            prestamo.setFecha_prestamo(fechaActualDate);
            
            sc.nextLine(); //Uso esto para poder vaciar el buffer

            //Manera para poder meter fecha de devolucion, ayuda de la IA
            System.out.println("Introduce la fecha de devolución (formato dd/MM/yyyy):");
            String fechaStr = sc.nextLine();
                if (!fechaStr.isEmpty()) {
                    System.out.println("entra");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fechaDevolucion = LocalDate.parse(fechaStr, formatter);
                    Date fechaDev = Date.valueOf(fechaDevolucion);
                    prestamo.setFecha_devolucion(fechaDev);
                } else {
                    fechaStr = sc.nextLine();
                }
            
            prestamo.setLibro(libro);
            libro.actualizarDisponible(libro, session, false);
            prestamo.setLector(lector);

            t = session.beginTransaction();
            session.save(prestamo);
            t.commit();
            return "Prestamo realizado con exito";
        }else{
            return "No se ha podido realizar el prestamo";
        }
        
    }
    public static String registrarDevolucion(int idLibro, Session session){
        Libro libro = Libro.libroPorId(idLibro, session);

        if(!libro.isDisponible()){
            libro.actualizarDisponible(libro, session,true);
            return "Libro devuelto correctamente";
        }else{
            return "Este libro ya se encontraba disponible";
        }   
    }

    public static List <Libro> prestadosLector(int idLector, Session session){
            
            List <Prestamo> prestamos = session.createQuery("FROM Prestamo WHERE id_lector = :id", Prestamo.class)
                            .setParameter("id", idLector)
                            .getResultList();
            
            List <Libro> libros = null;
            for(Prestamo pr : prestamos){
                libros.add(session.createQuery("From Libro WHERE id = :id AND disponible=0", Libro.class)
                            .setParameter("id", pr.getLibro().getIdLibro())
                            .uniqueResult());                 
            }

            return libros;
        
    }
    
    //Metodo para listar historial de prestamo por lector
    public static List<Prestamo> prestamosHistorial(int id_lector,Session session){
        List <Prestamo> prestamos = session.createQuery("FROM Prestamo WHERE id_lector = :id", Prestamo.class)
                            .setParameter("id", id_lector)
                            .getResultList();
        return prestamos;
    }

    public String toString(){
        return "\n Fecha Prestado: " + this.getFecha_prestamo() + " \n Fecha Devolución: " + this.getFecha_devolucion() 
            + " \n Libro: " + this.getLibro() + " \n Lector: " + this.getLector() + "\n";
    }
}
