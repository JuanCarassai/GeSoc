package persistencia;

import java.util.List;

import javax.persistence.EntityManager;

import com.API.MedioDePago;

public class MedioDePagoMapperBD extends MapperBD<MedioDePago>{
	private static final MedioDePagoMapperBD instance = new MedioDePagoMapperBD();
	
	private MedioDePagoMapperBD () {}
	public static MedioDePagoMapperBD getInstance() {
		return instance;
	}
	public List<MedioDePago> obtenerTodos() {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		
		return em.createQuery("select m from MedioDePago m", MedioDePago.class)
				.getResultList();
	
	}
	
	public boolean mediosCargadosEnBD() {
		return MedioDePagoMapperBD.getInstance().obtenerTodos().size()>0;
	}
	
	
	
}
