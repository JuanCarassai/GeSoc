package validadorContrasenias;

import org.mindrot.jbcrypt.BCrypt;

import persistencia.UsuarioMapper;
import validadorDeCompras.Usuario;

public class RegistrarUsuario {
	private static final RegistrarUsuario instance = new RegistrarUsuario();

	private RegistrarUsuario() {
	}

	public static RegistrarUsuario getInstance() {
		return instance;
	}

	public void registrar(String nombre, String contrasenia) {
		String newSalt = BCrypt.gensalt();
		String newHashedPassword = BCrypt.hashpw(contrasenia,newSalt);
		Usuario u = new Usuario(nombre, newSalt, newHashedPassword, null);
		UsuarioMapper.getInstance().insert(u);
	}

}
