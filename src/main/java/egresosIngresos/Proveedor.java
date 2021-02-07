package egresosIngresos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int codigoPostal;
    
    @OneToOne
    Direccion direccion;
    
	public String getNombre() {
		return "SIN NOMBRE";
	}

}