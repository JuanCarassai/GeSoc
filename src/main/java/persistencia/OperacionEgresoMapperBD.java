package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import egresosIngresos.OperacionEgreso;

public class OperacionEgresoMapperBD extends MapperBD<OperacionEgreso> {
	private static final OperacionEgresoMapperBD instance = new OperacionEgresoMapperBD();

	private OperacionEgresoMapperBD() {
	}

	public static OperacionEgresoMapperBD getInstance() {
		return instance;
	}

	public List<OperacionEgreso> obtenerEgresosQueSeanVinculables() {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		return em.createQuery("select e from OperacionEgreso e where e.ingreso is null", OperacionEgreso.class)
				.getResultList();
	}

	public List<OperacionEgreso> obtenerEgresos() {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		
		return em.createQuery("select e from OperacionEgreso e", OperacionEgreso.class)
				.getResultList();
	}

	public OperacionEgreso buscarEgresoPorId(Long identificadorOperacion) {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		OperacionEgreso egresoEncontrado;
		try {
			egresoEncontrado = em.createQuery("select e from OperacionEgreso e where e.id = :identificador", OperacionEgreso.class)
					.setParameter("identificador", identificadorOperacion).getSingleResult();
		}
		catch(NoResultException e) {
			egresoEncontrado = null;
		}
		return egresoEncontrado;
	}

	public List<OperacionEgreso> obtenerEgresosLazy() {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		
		return em.createQuery("select e from OperacionEgreso e", OperacionEgreso.class)
				.getResultList();
	}

}
