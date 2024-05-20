package DAO;

import com.projetsars.appbanco.model.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

public class ClienteDao {

    private EntityManager entityManager;

    public ClienteDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public long aniadir(Cliente cliente){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();

            return cliente.getId();
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return 0;
        }
    }

    public Cliente buscarPorId(long id){
        try{
            entityManager.getTransaction().begin();
            Cliente cliente = entityManager.find(Cliente.class, id);
            entityManager.getTransaction().commit();

            if(cliente!= null){
                return cliente;
            }else{
                throw new EntityNotFoundException("Cliente con ID " + id + " no encontrado");
            }
        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            
            return null;
        }
    }
    public String actualizar(Cliente cliente){
        try{
            entityManager.getTransaction().begin();
            Cliente clienteBd = entityManager.find(Cliente.class, cliente.getId());
            if(null != clienteBd){
                clienteBd.setNombre(cliente.getNombre());
                clienteBd.setApellidos(cliente.getApellidos());
                clienteBd.setFecha_nacimiento(cliente.getFecha_nacimiento());
                clienteBd.setSucursal(cliente.getSucursal());

                entityManager.merge(clienteBd);
                entityManager.getTransaction().commit();
                return "Cliente actualizado con exito";
            }else{
                return "Cliente no encontrado";
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
            Cliente cliente = entityManager.find(Cliente.class, id);
            entityManager.remove(cliente);
            entityManager.getTransaction().commit();
            return "Cliente eliminado con exito";

        }catch(Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            
            return "Ha ocurrido un error: " + e.getMessage();
        }
    }

}
