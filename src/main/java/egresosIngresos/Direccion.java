package egresosIngresos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.API.Ciudad;

@Entity
public class Direccion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DIRECCION")
	private Long id;
	@Column(name="CALLE")
	private String calle;
	@Column(name="LOCALIDAD")
	private String localidad;
	@Column(name="PISO")
	private String piso;
	@ManyToOne
	private Ciudad ciudad;
	
	
	@Override
	public String toString() {
		return "Direccion [id=" + id + ", calle=" + calle + ", localidad=" + localidad + ", piso=" + piso
				+ ", ciudad=" + ciudad + "]";
	}
	
	public Direccion(Long id, String calle, String localidad, String piso, Ciudad ciudad) {
		super();
		this.id = id;
		this.calle = calle;
		this.localidad = localidad;
		this.piso = piso;
		this.ciudad = ciudad;
	}
	public Direccion() {
		// TODO Auto-generated constructor stub
	}



	public long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
	
}
