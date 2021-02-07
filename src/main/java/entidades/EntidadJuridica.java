package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import persistencia.EntidadJuridicaMapperBD;


@Entity
public class EntidadJuridica extends Entidad{

	private int codigoInscripcion;
	private int codigoPostal;
	private int cuit;
	
	@OneToMany
	private List<EntidadBase> entidades = new ArrayList<EntidadBase>(); 
	
	private String razonSocial;
	
	public EntidadJuridica(int codigoInscripcion, int codigoPostal, int cuit, ArrayList<EntidadBase> entidades,
			String razonSocial) {
		super();
		this.codigoInscripcion = codigoInscripcion;
		this.codigoPostal = codigoPostal;
		this.cuit = cuit;
		this.entidades = entidades;
		this.razonSocial = razonSocial;
	}

	public EntidadJuridica() {}
	
	public int getCodigoInscripcion() {
		return codigoInscripcion;
	}
	public void setCodigoInscripcion(int codigoInscripcion) {
		this.codigoInscripcion = codigoInscripcion;
	}
	public int getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public int getCuit() {
		return cuit;
	}
	public void setCuit(int cuit) {
		this.cuit = cuit;
	}
	public List<EntidadBase> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<EntidadBase> entidades) {
		this.entidades = entidades;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public static void insertarNuevaJuridica(EntidadJuridica entidad) {
		EntidadJuridicaMapperBD.getInstance().insert(entidad);		
	}		
}
