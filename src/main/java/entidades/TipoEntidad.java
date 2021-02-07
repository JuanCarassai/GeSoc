package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class TipoEntidad {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int idTipoEntidad;
	
	public TipoEntidad() {}

	public int getIdTipoEntidad() {
		return idTipoEntidad;
	}

	public void setIdTipoEntidad(int idTipoEntidad) {
		this.idTipoEntidad = idTipoEntidad;
	}
	
}
