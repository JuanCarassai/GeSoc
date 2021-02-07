package persistencia;

import java.util.List;

import javax.persistence.EntityManager;

import entidades.EntidadBase;

public class EntidadBaseMapperBD extends MapperBD<EntidadBase>{
	private static final EntidadBaseMapperBD instance = new EntidadBaseMapperBD();
	
	private EntidadBaseMapperBD () {}
	public static EntidadBaseMapperBD getInstance() {
		return instance;
	}
	public List<EntidadBase> obtenerTodosSinAsignar() {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		return em.createQuery("select e from EntidadBase e where e.entidad_juridica is null", EntidadBase.class)
				.getResultList();
	}
	
}