package persistencia;

import java.util.List;

import javax.persistence.EntityManager;

import comprasPresupuestos.PresupuestoDetallado;

public class PresupuestoDetalladoMapperBD extends MapperBD<PresupuestoDetallado>{
	private static final PresupuestoDetalladoMapperBD instance = new PresupuestoDetalladoMapperBD();
	
	private PresupuestoDetalladoMapperBD () {}
	public static PresupuestoDetalladoMapperBD getInstance() {
		return instance;
	}
	public List<PresupuestoDetallado> obtenerTodos() {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		
		return em.createQuery("select p from PresupuestoDetallado p", PresupuestoDetallado.class)
				.getResultList();
	}
}
