package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import egresosIngresos.OperacionIngreso;

public class OperacionIngresoMapperBD extends MapperBD<OperacionIngreso> {
	private static final OperacionIngresoMapperBD instance = new OperacionIngresoMapperBD();

	private OperacionIngresoMapperBD() {
	}

	public static OperacionIngresoMapperBD getInstance() {
		return instance;
	}

	public List<OperacionIngreso> obtenerIngresosQueSeanVinculables() {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		return em.createQuery("select i from OperacionIngreso i where i.egresos is empty", OperacionIngreso.class)
				.getResultList();
	}

	public List<OperacionIngreso> obtenerIngresos() {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		return em.createQuery("FROM OperacionIngreso", OperacionIngreso.class).getResultList();
	}

	public OperacionIngreso buscarIngresoPorId(Long identificadorOperacion) {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		OperacionIngreso ingresoEncontrado;
		try {
			ingresoEncontrado = em.createQuery("select i from OperacionIngreso i where i.id = :identificador", OperacionIngreso.class)
					.setParameter("identificador", identificadorOperacion).getSingleResult();
		}
		catch(NoResultException e) {
			ingresoEncontrado = null;
		}
		return ingresoEncontrado;
	}


}
