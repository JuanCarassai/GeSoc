package persistencia;

import java.util.List;

import javax.persistence.EntityManager;

import comprasPresupuestos.Producto;

public class ProductoMapperBD extends MapperBD<Producto>{
	private static final ProductoMapperBD instance = new ProductoMapperBD();
	
	private ProductoMapperBD () {}
	public static ProductoMapperBD getInstance() {
		return instance;
	}
	public List<Producto> obtenerTodos() {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		
		return em.createQuery("select p from Producto p", Producto.class)
				.getResultList();
	
	}
}
