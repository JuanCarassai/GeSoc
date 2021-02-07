package persistencia;

import java.util.List;

import javax.persistence.EntityManager;

import egresosIngresos.Organizacion;

public class OrganizacionMapperBD extends MapperBD<Organizacion>{
	private static final OrganizacionMapperBD instance = new OrganizacionMapperBD();
	
	private OrganizacionMapperBD () {}
	public static OrganizacionMapperBD getInstance() {
		return instance;
	}
	public List<Organizacion> obtenerOrganizaciones() {

		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);

		List<Organizacion> organizaciones =	em.createQuery("select o from Organizacion o", Organizacion.class).getResultList();
		return organizaciones;
	}
}
