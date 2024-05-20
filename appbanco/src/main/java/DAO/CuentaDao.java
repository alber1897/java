package DAO;

import com.projetsars.appbanco.model.Cuenta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

public class CuentaDao {
    private EntityManager entityManager;

    public CuentaDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public long aniadir(Cuenta cuenta){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(cuenta);
            entityManager.getTransaction().commit();

            return cuenta.getId();
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return 0;
        }
    }

    public Cuenta buscarPorId(long id){
        try{
            entityManager.getTransaction().begin();
            Cuenta cuenta = entityManager.find(Cuenta.class, id);
            entityManager.getTransaction().commit();

            if(cuenta!= null){
                return cuenta;
            }else{
                throw new EntityNotFoundException("Cuenta con ID " + id + " no encontrada");
            }
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            
            return null;
        }
    }
    public String actualizar(Cuenta cuenta){
        try{
            entityManager.getTransaction().begin();
            Cuenta cuentaBd = entityManager.find(Cuenta.class, cuenta.getId());
            if(null != cuentaBd){
                cuentaBd.setSaldo(cuenta.getSaldo());

                entityManager.merge(cuentaBd);
                entityManager.getTransaction().commit();
                return "Cuenta actualizada con exito";
            }else{
                return "Cuenta no encontrada";
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
            Cuenta cuenta = entityManager.find(Cuenta.class, id);
            entityManager.remove(cuenta);
            entityManager.getTransaction().commit();
            return "Cuenta eliminada con exito";

        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            
            return "Ha ocurrido un error: " + e.getMessage();
        }
    }

}
