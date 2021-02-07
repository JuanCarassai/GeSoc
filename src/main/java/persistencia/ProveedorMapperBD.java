package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import egresosIngresos.OrganizacionProveedora;
import egresosIngresos.Persona;
import egresosIngresos.Proveedor;

public class ProveedorMapperBD extends MapperBD<Proveedor>{
	private static final ProveedorMapperBD instance = new ProveedorMapperBD();
	
	private ProveedorMapperBD () {}
	public static ProveedorMapperBD getInstance() {
		return instance;
	}
	
	public Persona buscarPersonaPorDNI(int dni) {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		Persona personaEncontrada;
		try {
			personaEncontrada = em.createQuery("select p from Persona p where p.dni = :d", Persona.class)
					.setParameter("d", dni).getSingleResult();
		}
		catch(NoResultException e) {
			personaEncontrada = null;
		}
		return personaEncontrada;
	}
	public OrganizacionProveedora buscarProveedorPorCuit(int cuit) {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		OrganizacionProveedora orgEncontrada;
		try {
			orgEncontrada = em.createQuery("select p from OrganizacionProveedora p where p.cuit = :c", OrganizacionProveedora.class)
					.setParameter("c", cuit).getSingleResult();
		}
		catch(NoResultException e) {
			orgEncontrada = null;
		}
		return orgEncontrada;
	}
}
