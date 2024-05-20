package DAO;

import com.projetsars.appbanco.model.Banco;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

public class BancoDao {
    private EntityManager entityManager;

    public BancoDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public long aniadir(Banco banco){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(banco);
            entityManager.getTransaction().commit();

            return banco.getId();
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return 0;
        }
    }

    public Banco buscarPorId(long id){
        try{
            entityManager.getTransaction().begin();
            Banco banco = entityManager.find(Banco.class, id);
            entityManager.getTransaction().commit();

            if(banco!= null){
                return banco;
            }else{
                throw new EntityNotFoundException("Banco con ID " + id + " no encontrado");
            }
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            
            return null;
        }
    }
    public String actualizar(Banco banco){
        try{
            entityManager.getTransaction().begin();
            Banco bancoBd = entityManager.find(Banco.class, banco.getId());
            if(null != bancoBd){
                bancoBd.setNombre(banco.getNombre());
                bancoBd.setPais(banco.getPais());

                entityManager.merge(bancoBd);
                entityManager.getTransaction().commit();
                return "Banco actualizado con exito";
            }else{
                return "Banco no encontrado";
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
            Banco banco = entityManager.find(Banco.class, id);
            entityManager.remove(banco);
            entityManager.getTransaction().commit();
            return "Banco eliminado con exito";

        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            
            return "Ha ocurrido un error: " + e.getMessage();
        }
    }

}
