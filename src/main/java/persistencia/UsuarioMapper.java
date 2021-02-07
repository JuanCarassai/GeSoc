package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import validadorDeCompras.Usuario;

public class UsuarioMapper  extends MapperBD<Usuario>{
	private static final UsuarioMapper instance = new UsuarioMapper();
	
	private UsuarioMapper () {}
	public static UsuarioMapper getInstance() {
		return instance;
	}
	
	public Usuario buscarUsuario(String nombre) {
		EntityManager em = BDUtils.getEntityManager();
		BDUtils.comenzarTransaccion(em);
		Usuario usuarioEncontrado;
		try {
			usuarioEncontrado = em.createQuery("select s from Usuario s where s.nombre = :n", Usuario.class)
					.setParameter("n", nombre).getSingleResult();
		}
		catch(NoResultException e) {
			usuarioEncontrado = null;
		}
		return usuarioEncontrado;
	}
}
