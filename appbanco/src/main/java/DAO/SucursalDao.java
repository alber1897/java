package DAO;

import com.projetsars.appbanco.model.Sucursal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

public class SucursalDao {
    private EntityManager entityManager;

    public SucursalDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public long aniadir(Sucursal sucursal){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(sucursal);
            entityManager.getTransaction().commit();

            return sucursal.getId();
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return 0;
        }
    }

    public Sucursal buscarPorId(long id){
        try{
            entityManager.getTransaction().begin();
            Sucursal sucursal = entityManager.find(Sucursal.class, id);
            entityManager.getTransaction().commit();

            if(sucursal!= null){
                return sucursal;
            }else{
                throw new EntityNotFoundException("Sucursal con ID " + id + " no encontrada");
            }
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            
            return null;
        }
    }
    public String actualizar(Sucursal sucursal){
        try{
            entityManager.getTransaction().begin();
            Sucursal sucursalBd = entityManager.find(Sucursal.class, sucursal.getId());
            if(null != sucursalBd){
                sucursalBd.setNombre(sucursal.getNombre());
                sucursalBd.setUbicacion(sucursal.getUbicacion());

                entityManager.merge(sucursalBd);
                entityManager.getTransaction().commit();
                return "Sucursal actualizada con exito";
            }else{
                return "Sucursal no encontrado";
            }
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Ha ocurrido un error: " + e.getMessage();
        }
    }
    
    public String eliminar(long id){
        try{

            entityManager.getTransaction().begin();
            Sucursal sucursal = entityManager.find(Sucursal.class, id);
            entityManager.remove(sucursal);
            entityManager.getTransaction().commit();
            return "Sucursal eliminada con exito";

        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            
            return "Ha ocurrido un error: " + e.getMessage();
        }
    }
}
