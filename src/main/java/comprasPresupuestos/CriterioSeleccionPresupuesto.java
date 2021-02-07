package comprasPresupuestos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class CriterioSeleccionPresupuesto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public abstract Presupuesto obtenerPresupuesto(Compra compra);
}
