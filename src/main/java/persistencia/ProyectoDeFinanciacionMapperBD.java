package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import RendicionDeCuentas.ProyectoDeFinanciacion;

public class ProyectoDeFinanciacionMapperBD extends MapperBD<ProyectoDeFinanciacion> {
	private static final ProyectoDeFinanciacionMapperBD instance = new ProyectoDeFinanciacionMapperBD();

	private ProyectoDeFinanciacionMapperBD() {
	}

	public static ProyectoDeFinanciacionMapperBD getInstance() {
		return instance;
	}

	public ProyectoDeFinanciacion buscarProyectoPorIdentificador(Long identificadorPresupuesto) {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		ProyectoDeFinanciacion proyectoEncontrado;
		try {
			proyectoEncontrado = em.createQuery("select p from ProyectoDeFinanciacion p where p.id = :identificador", ProyectoDeFinanciacion.class)
					.setParameter("identificador", identificadorPresupuesto).getSingleResult();
		}
		catch(NoResultException e) {
			proyectoEncontrado = null;
		}
		return proyectoEncontrado;
	}
}
