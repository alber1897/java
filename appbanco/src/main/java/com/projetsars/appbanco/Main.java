package com.projetsars.appbanco;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

import com.projetsars.appbanco.DAO.BancoDao;
import com.projetsars.appbanco.DAO.ClienteDao;
import com.projetsars.appbanco.DAO.CuentaDao;
import com.projetsars.appbanco.DAO.SucursalDao;
import com.projetsars.appbanco.model.Banco;
import com.projetsars.appbanco.model.Cliente;
import com.projetsars.appbanco.model.Cuenta;
import com.projetsars.appbanco.model.Sucursal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) throws ParseException {
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("miUnidadPersistencia");
        EntityManager em = entityManagerFactory.createEntityManager();

        Scanner sc = new Scanner(System.in);

        BancoDao bancoDao = new BancoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        SucursalDao sucursalDao = new SucursalDao(em);
        CuentaDao cuentaDao = new CuentaDao(em);

        System.out.println(
            "*********************************\n" +
            "****     MENU DE OPCIONES    ****\n" +
            "*********************************\n" +
            "\n"+
            "*** 1 - Añadir Banco ***\n"+
            "*** 2 - Actualizar Banco ***\n"+
            "*** 3 - Buscar Banco ***\n"+
            "*** 4 - Eliminar Banco ***\n"+
            "\n"+
            "*** 5 - Añadir Sucursal ***\n"+
            "*** 6 - Actualizar Sucursal ***\n"+
            "*** 7 - Buscar Sucursal ***\n"+
            "*** 8 - Eliminar Sucursal ***\n"+
            "\n"+
            "*** 9 - Añadir Cuenta ***\n"+
            "*** 10 - Actualizar Cuenta ***\n"+
            "*** 11 - Buscar Cuenta ***\n"+
            "*** 12 - Eliminar Cuenta ***\n"+
            "\n"+
            "*** 13 - Añadir Cliente ***\n"+
            "*** 14 - Actualizar Cliente ***\n"+
            "*** 15 - Buscar Cliente ***\n"+
            "*** 16 - Eliminar Cliente ***\n"
            );

            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    String nombre,pais;
                    System.out.println("Introduzca nombre del banco a crear: ");
                    nombre = sc.nextLine();
                    System.out.println("Introduzca pais del banco: ");
                    pais = sc.nextLine();
                    System.out.println(bancoDao.aniadir(new Banco(nombre, pais)));
                    break;

                case 2: 

                    System.out.println("Introduzca ID del banco a editar: ");
                    Banco oldBanco = bancoDao.buscarPorId(sc.nextLong());
                    sc.nextLine();

                    if(null != oldBanco){
                        System.out.println("¿Desea cambiar el nombre del banco? [si/no]");
                        if(sc.nextLine().equals("si")){
                            System.out.println("Introduzca nuevo nombre del banco: ");
                            oldBanco.setNombre(sc.nextLine());
                        }

                        System.out.println("¿Desea cambiar el pais del banco? [si/no]");
                        if(sc.nextLine().equals("si")){
                            System.out.println("Introduzca nuevo pais: ");
                            oldBanco.setPais(sc.nextLine());
                        }

                        System.out.println(bancoDao.actualizar(oldBanco));
                    }else{
                        System.out.println("Banco no encontrado");
                    }

                    
                    

                case 3:
                    System.out.println("Introduzca id del Banco a buscar: ");
                    System.out.println(bancoDao.buscarPorId(sc.nextLong()).toString());
                    
                case 4:
                    System.out.println("Introduzca id del Banco a eliminar: ");
                    System.out.println(bancoDao.eliminar(sc.nextLong()));


                case 5:
                    String nombreSucursal,ubicacion;
                    Long idBanco;
                    System.out.println("Introduzca nombre de la Sucursal a crear: ");
                    nombreSucursal = sc.nextLine();
                    System.out.println("Introduzca ubicacion de la sucursal: ");
                    ubicacion = sc.nextLine();
                    System.out.println("Introduzca ID del banco al que pertenece esta sucursal: ");
                    idBanco = sc.nextLong();
                    System.out.println(sucursalDao.aniadir(new Sucursal(nombreSucursal, ubicacion, bancoDao.buscarPorId(idBanco))));
                    break;

                case 6: 

                    System.out.println("Introduzca la ID de la sucursal a editar: ");
                    Sucursal oldSucursal = sucursalDao.buscarPorId(sc.nextLong());
                    sc.nextLine();

                    if(null != oldSucursal){
                        System.out.println("¿Desea introducir nuevo nombre de sucursal? [si/no]");
                        if(sc.nextLine().equals("si")){
                            System.out.println("Introduzca nuevo nombre de la sucursal: ");
                            oldSucursal.setNombre(sc.nextLine());
                        }

                        System.out.println("¿Desea introducir nueva ubucación? [si/no]");
                        if(sc.nextLine().equals("si")){
                            System.out.println("Introduzca nueva ubicación: ");
                            oldSucursal.setUbicacion(sc.nextLine());
                        }

                        System.out.println(sucursalDao.actualizar(oldSucursal));
                    }else{
                        System.out.println("Sucursal no encontrada");;
                    }

                case 7:
                    System.out.println("Introduzca id de la Sucursal a buscar: ");
                    System.out.println(sucursalDao.buscarPorId(sc.nextLong()).toString());
                    
                case 8:
                    System.out.println("Introduzca id de la Sucursal a eliminar: ");
                    System.out.println(sucursalDao.eliminar(sc.nextLong()));

                case 9:
                    String iban;
                    int saldo;
                    Long idCliente;
                    System.out.println("Introduzca IBAN para la Cuenta a crear: ");
                    iban = sc.nextLine();
                    System.out.println("Introduzca saldo: ");
                    saldo = sc.nextInt();
                    System.out.println("Introduzca ID del cliente al que pertenece la Cuenta: ");
                    idCliente = sc.nextLong();

                    System.out.println(cuentaDao.aniadir(new Cuenta(iban, saldo, LocalDate.now(), clienteDao.buscarPorId(idCliente))));
                    break;

                case 10: 

                    System.out.println("Introduzca la ID de la Cuenta a editar: ");
                    Cuenta oldCuenta = cuentaDao.buscarPorId(sc.nextLong());
                    sc.nextLine();
                    if(null != oldCuenta){
                        System.out.println("¿Desea introducir nuevo saldo? [si/no]");
                        if(sc.nextLine().equals("si")){
                            System.out.println("Introduzca nuevo saldo para la cuenta: ");
                            oldCuenta.setSaldo(sc.nextInt());
                        }
                        System.out.println(cuentaDao.actualizar(oldCuenta)); 
                    }else{
                        System.out.println("Cuenta no encontrada");;
                    }
                    break;
                case 11:
                    System.out.println("Introduzca id de la Cuenta a buscar: ");
                    System.out.println(cuentaDao.buscarPorId(sc.nextLong()).toString());
                    sc.nextLine();
                    break;
                case 12:
                    System.out.println("Introduzca id de la Cuenta a eliminar: ");
                    System.out.println(cuentaDao.eliminar(sc.nextLong()));
                    sc.nextLine();
                    break;
                case 13:
                    String nombreCliente,apellidos;
                    Long idSucursal;
                    String fechaNacimiento;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                    System.out.println("Introduzca nombre de Cliente: ");
                    nombreCliente = sc.nextLine();
                    System.out.println("Introduzca apellidos del Cliente: ");
                    apellidos = sc.nextLine();
                    System.out.println("Introduzca ID de la Sucursal a la que pertenece: ");
                    idSucursal = sc.nextLong();
                    sc.nextLine();
                    System.out.println("Introduzca fecha de nacimiento: ");
                    fechaNacimiento = sc.nextLine();
                    System.out.println(clienteDao.aniadir(new Cliente(nombreCliente, apellidos, dateFormat.parse(fechaNacimiento), sucursalDao.buscarPorId(idSucursal))));
                    break;

                case 14: 
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");

                    System.out.println("Introduzca id del Cliente a editar: ");
                    Cliente oldCliente = clienteDao.buscarPorId(sc.nextLong());
                    sc.nextLine();
                    Date fechaFormateada;

                    if(null != oldCliente){
                        System.out.println("¿Desea introducir nuevo nombre? [si/no]");
                        if(sc.nextLine().equals("si")){
                            System.out.println("Introduzca nuevo nombre de Cliente: ");
                            oldCliente.setNombre(sc.nextLine());
                        }

                        System.out.println("¿Desea introducir nuevos apellidos? [si/no]");
                        if(sc.nextLine().equals("si")){
                            System.out.println("Introduzca nuevos Apellidos: ");
                            oldCliente.setApellidos(sc.nextLine());
                        }

                        System.out.println("¿Desea introducir nueva ID de Sucursal? [si/no]");
                        if(sc.nextLine().equals("si")){
                            System.out.println("Introduzca nueva ID: ");
                            oldCliente.setSucursal(sucursalDao.buscarPorId(sc.nextLong()));
                            sc.nextLine();
                        }

                        System.out.println("¿Desea introducir nueva fecha de nacimiento? [si/no]");
                        if(sc.nextLine().equals("si")){
                            System.out.println("Introduzca nueva fecha: ");
                            fechaNacimiento = sc.nextLine();
                            fechaFormateada = (Date) dateFormat1.parse(fechaNacimiento);
                            oldCliente.setFecha_nacimiento(fechaFormateada);
                        }

                        System.out.println(clienteDao.actualizar(oldCliente));    
                    }else{
                        System.out.println("Cliente no encontrado");;
                    }
                    break;
                case 15:
                    System.out.println("Introduzca id del Cliente a buscar: ");
                    System.out.println(clienteDao.buscarPorId(sc.nextLong()).toString());
                    break;
                case 16:
                    System.out.println("Introduzca id del Cliente a eliminar: ");
                    System.out.println(clienteDao.eliminar(sc.nextLong()));
                    break;
                default:
                    break;
            }
            sc.close();
    }
}