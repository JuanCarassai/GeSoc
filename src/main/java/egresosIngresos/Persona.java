package egresosIngresos;

import javax.persistence.Entity;

import persistencia.ProveedorMapperBD;

@Entity
public class Persona extends Proveedor {
	int dni;
	String nombreApellido;

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	@Override
	public String getNombre() {
		return nombreApellido;
	}
	
	public static Persona buscarPersonaPorDNIEnBD(int dni) {
		return ProveedorMapperBD.getInstance().buscarPersonaPorDNI(dni);
	}

	public static void insertarNuevaPersonaEnBD(Persona nuevaPersona) {
		ProveedorMapperBD.getInstance().insert(nuevaPersona);
		
	}

}
