package persistencia;

import java.util.List;

import javax.persistence.EntityManager;

public abstract class MapperBD<T> {

	
    public void insert(T c) {
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);
        em.persist(c);
        BDUtils.commit(em);
    }

    public void insert(List<T> objetos) {
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);
        objetos.forEach(objeto -> em.persist(objeto));
        BDUtils.commit(em);
    }

    public void update(T c) {
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);
        em.merge(c);
        em.flush();
        BDUtils.commit(em);
    } //Cuando persisits algo queda guardado dentro del Entity Manager, commit guarda cambios, el Em es el q sabe q existe.

    
    public void updateAll(List<T> c) {
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);
        c.forEach(c_i -> em.merge(c_i));
        em.flush();
        BDUtils.commit(em);
    }
    public void delete(T c) {
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);
        em.remove(c);
        BDUtils.commit(em);
    }
    
    
   
    
    

}
