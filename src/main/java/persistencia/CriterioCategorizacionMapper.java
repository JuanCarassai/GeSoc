package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import criteriosCategorias.CriterioCategorizacion;

public class CriterioCategorizacionMapper extends MapperBD<CriterioCategorizacion>{
	private static final CriterioCategorizacionMapper instance = new CriterioCategorizacionMapper();
	
	private CriterioCategorizacionMapper () {}
	public static CriterioCategorizacionMapper getInstance() {
		return instance;
	}
	public CriterioCategorizacion buscarCriterioPorNombre(String nombre) {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		CriterioCategorizacion criterioEncontrado;
		try {
			criterioEncontrado = em.createQuery("select c from CriterioCategorizacion c where c.nombre = :n", CriterioCategorizacion.class)
					.setParameter("n", nombre).getSingleResult();
		}
		catch(NoResultException e) {
			criterioEncontrado = null;
		}
		return criterioEncontrado;
	}
	public List<CriterioCategorizacion> obtenerTodos() {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		
		return em.createQuery("select c from CriterioCategorizacion c", CriterioCategorizacion.class)
				.getResultList();
	}
}
