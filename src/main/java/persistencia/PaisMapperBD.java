package persistencia;

import java.util.List;

import javax.persistence.EntityManager;

import com.API.Pais;

public class PaisMapperBD extends MapperBD <Pais>{
	private static final PaisMapperBD instance = new PaisMapperBD();
	
	private PaisMapperBD () {}
	public static PaisMapperBD getInstance() {
		return instance;
	}
	public boolean paisesCargadosEnBD() {
		return PaisMapperBD.getInstance().obtenerTodos().size()>0;
	}
	public List<Pais> obtenerTodos() {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		
		return em.createQuery("select p from Pais p", Pais.class)
				.getResultList();
	}
}
