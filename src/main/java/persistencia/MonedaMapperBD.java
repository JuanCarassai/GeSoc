package persistencia;


import java.util.List;

import javax.persistence.EntityManager;

import com.API.Moneda;


public class MonedaMapperBD extends MapperBD<Moneda> {	
	private static final MonedaMapperBD instance = new MonedaMapperBD();
	
	private MonedaMapperBD () {}
	public static MonedaMapperBD getInstance() {
		return instance;
	}
	public boolean monedasCargadasEnBD() {
		return MonedaMapperBD.getInstance().obtenerTodos().size()>0;
	}
	public List<Moneda> obtenerTodos() {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		
		return em.createQuery("select m from Moneda m", Moneda.class)
				.getResultList();
	}
}
