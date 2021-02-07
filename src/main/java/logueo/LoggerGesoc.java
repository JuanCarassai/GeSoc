package logueo;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class LoggerGesoc {

	@Pointcut(value = "execution(* persistencia.*.inser*(..))")
	public void todosLosInsertEnPaquetePersistencia() {

	}

	@Before(value = "todosLosInsertEnPaquetePersistencia()", argNames = "joinPoint")
	public void loguearAlta(JoinPoint jp) {

		OperacionLog logInsert = new OperacionLog();
		logInsert.setTipoOperacion(TipoOperacion.ALTA);
		String str = jp.getTarget().toString();
		logInsert.setEntidadAfectada(obtenerNombre(str));
		logInsert.setFecha(LocalDateTime.now());

		MapperMongoDB.getInstance().insertarLog(logInsert);
	}

	@Pointcut(value = "execution(* persistencia.*.delet*(..))")
	public void todosLosDeleteEnPaquetePersistencia() {

	}

	@Before(value = "todosLosDeleteEnPaquetePersistencia()", argNames = "joinPoint")
	public void loguearBaja(JoinPoint jp) {

		OperacionLog logDelete = new OperacionLog();
		logDelete.setTipoOperacion(TipoOperacion.BAJA);
		logDelete.setEntidadAfectada(obtenerNombre(jp.getTarget().toString()));
		logDelete.setFecha(LocalDateTime.now());

		MapperMongoDB.getInstance().insertarLog(logDelete);
	}

	@Pointcut(value = "execution(* persistencia.*.updat*(..))")
	public void todosLosUpdateEnPaquetePersistencia() {

	}

	@Before(value = "todosLosUpdateEnPaquetePersistencia()", argNames = "joinPoint")
	public void loguearUpdate(JoinPoint jp) {

		OperacionLog logUpdate = new OperacionLog();
		logUpdate.setTipoOperacion(TipoOperacion.MODIFICACION);
		logUpdate.setEntidadAfectada(obtenerNombre(jp.getTarget().toString()));
		logUpdate.setFecha(LocalDateTime.now());

		MapperMongoDB.getInstance().insertarLog(logUpdate);
	}

	public static String obtenerNombre(String cadena) {
		int j = 0, i = 0;
		while (cadena.charAt(i) != '.')
			i++;

		j = i;
		while (!(cadena.charAt(j) == 'M' && cadena.charAt(j + 1) == 'a' && cadena.charAt(j + 2) == 'p'))
			j++;

		return cadena.substring(i + 1, j);

	}

}
