package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import comprasPresupuestos.Presupuesto;

public class PresupuestoMapperBD extends MapperBD<Presupuesto> {
	private static final PresupuestoMapperBD instance = new PresupuestoMapperBD();
	
	private PresupuestoMapperBD () {}
	public static PresupuestoMapperBD getInstance() {
		return instance;
	}
	public Presupuesto buscarPresupuestoPorId(Long identificadorOperacion) {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		Presupuesto presupuestoEncontrado;
		try {
			presupuestoEncontrado = em.createQuery("select p from Presupuesto p where p.id = :identificador", Presupuesto.class)
					.setParameter("identificador", identificadorOperacion).getSingleResult();
		}
		catch(NoResultException e) {
			presupuestoEncontrado = null;
		}
		return presupuestoEncontrado;
	}
	public List<Presupuesto> obtenerTodos() {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		
		return em.createQuery("select p from Presupuesto p", Presupuesto.class)
				.getResultList();
	}
}
