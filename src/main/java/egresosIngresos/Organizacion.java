 package egresosIngresos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import entidades.Entidad;

@Entity
public class Organizacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	public Organizacion(Long id, List<Entidad> entidades, List<OperacionIngreso> ingresos,
			List<OperacionEgreso> egresos, Requerimiento requerimiento, ReglaVinculacion regla) {
		super();
		this.id = id;
		this.entidades = entidades;
		this.ingresos = ingresos;
		this.egresos = egresos;
		this.requerimiento = requerimiento;
		this.regla = regla;
	}
	public Organizacion() {}

	@OneToMany
	private List<Entidad> entidades = new ArrayList<Entidad>();
	@OneToMany (mappedBy = "organizacion")
	private List<OperacionIngreso> ingresos = new ArrayList<OperacionIngreso>();
	@OneToMany (mappedBy = "organizacion")
	private List<OperacionEgreso> egresos = new ArrayList<OperacionEgreso>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Requerimiento requerimiento;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private ReglaVinculacion regla;
	
	
	public List<Entidad> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<Entidad> entidades) {
		this.entidades = entidades;
	}

	public List<OperacionIngreso> getIngresos() {
		return ingresos;
	}

	public void setIngresos(List<OperacionIngreso> ingresos) {
		this.ingresos = ingresos;
	}

	public List<OperacionEgreso> getEgresos() {
		return egresos;
	}

	public void setEgresos(List<OperacionEgreso> egresos) {
		this.egresos = egresos;
	}

	public ReglaVinculacion getRegla() {
		return regla;
	}

	public void setRegla(ReglaVinculacion regla) {
		this.regla = regla;
	}

	public void setRequerimiento(Requerimiento requerimiento) {
		this.requerimiento = requerimiento;
	}


	public Requerimiento getRequerimiento() {
		return requerimiento;
	}

	
	
}
