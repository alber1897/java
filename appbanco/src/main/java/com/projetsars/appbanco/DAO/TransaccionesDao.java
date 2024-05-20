package com.projetsars.appbanco.DAO;

import com.projetsars.appbanco.model.Transacciones;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

public class TransaccionesDao {
    private EntityManager entityManager;

    public TransaccionesDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public long aniadir(Transacciones transaccion){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(transaccion);
            entityManager.getTransaction().commit();

            return transaccion.getId();
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return 0;
        }
    }

    public Transacciones buscarPorId(long id){
        try{
            entityManager.getTransaction().begin();
            Transacciones transaccion = entityManager.find(Transacciones.class, id);
            entityManager.getTransaction().commit();

            if(transaccion!= null){
                return transaccion;
            }else{
                throw new EntityNotFoundException("Transaccion con ID " + id + " no encontrada");
            }
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            
            return null;
        }
    }
    public String actualizar(Transacciones transaccion){
        try{
            entityManager.getTransaction().begin();
            Transacciones transaccionBd = entityManager.find(Transacciones.class, transaccion.getId());
            if(null != transaccionBd){
                transaccionBd.setFecha(transaccion.getFecha());
                transaccionBd.setCuentaOrigen(transaccion.getCuentaOrigen());
                transaccionBd.setCuentaDestino(transaccion.getCuentaDestino());
                transaccion.setCantidad(transaccion.getCantidad());

                entityManager.merge(transaccionBd);
                entityManager.getTransaction().commit();
                return "Transaccion actualizado con exito";
            }else{
                return "Transaccion no encontrado";
            }
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Ha ocurrido un erro: " + e.getMessage();
        }
    }
    
    public String eliminar(long id){
        try{

            entityManager.getTransaction().begin();
            Transacciones transaccion = entityManager.find(Transacciones.class, id);
            entityManager.remove(transaccion);
            entityManager.getTransaction().commit();
            return "Transaccion eliminado con exito";

        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            
            return "Ha ocurrido un error: " + e.getMessage();
        }
    }
}
