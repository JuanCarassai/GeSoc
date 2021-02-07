package entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class Entidad {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int idEntidad;
	private String nombreFicticio;
	
	@OneToOne(cascade = CascadeType.ALL)
	private TipoEntidad tipoDeEntidad;
	
	public Entidad() {}
	
	public String getNombreFicticio() {
		return nombreFicticio;
	}
	public void setNombreFicticio(String nombreFicticio) {
		this.nombreFicticio = nombreFicticio;
	}
	public TipoEntidad getTipoDeEntidad() {
		return tipoDeEntidad;
	}
	public void setTipoDeEntidad(TipoEntidad tipoDeEntidad) {
		this.tipoDeEntidad = tipoDeEntidad;
	}

	public int getIdEntidad() {
		return idEntidad;
	}
	
	
}
