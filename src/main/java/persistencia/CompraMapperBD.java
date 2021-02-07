package persistencia;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import comprasPresupuestos.Compra;


public class CompraMapperBD extends MapperBD<Compra>{

	private static final CompraMapperBD instance = new CompraMapperBD();
	
	private CompraMapperBD () {}
	public static CompraMapperBD getInstance() {
		return instance;
	}
	
	
	public Compra buscarCompraPorNumero(Long numeroCompra) {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		Compra compraEncontrada;
		try {
			compraEncontrada = em.createQuery("select c from Compra c where c.numeroCompra = :n", Compra.class)
					.setParameter("n", numeroCompra).getSingleResult();
		}
		catch(NoResultException e) {
			compraEncontrada = null;
		}
		return compraEncontrada;
	}
	public static Queue<Compra> obtenerComprasParaValidar() {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		List<Compra> comprasParaValidar = em.createQuery("select c from Compra c where c.fueValidada = false", Compra.class)
				.getResultList();
		Queue<Compra> colaCompras = new LinkedList<Compra>();
		colaCompras.addAll(comprasParaValidar);
		return colaCompras;
		
	}
	
	public List<Compra> obtenerComprasSinEgresos() {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		return em.createQuery("select c from Compra c where c.id_operacion_egreso = -1", Compra.class)
				.getResultList();
	}
	
	
}