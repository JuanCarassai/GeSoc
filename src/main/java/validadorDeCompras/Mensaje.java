package validadorDeCompras;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import comprasPresupuestos.Compra;

@Entity

public class Mensaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean cantidadPresupuestosIndicada;
	private boolean presupuestoElegido;
	private boolean criterioCorrecto;
	@OneToOne
	private Compra compra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Mensaje(boolean cantidadPresupuestosIndicada, boolean presupuestoElegido, boolean criterioCorrecto,
			Compra compra) {
		super();
		this.cantidadPresupuestosIndicada = cantidadPresupuestosIndicada;
		this.presupuestoElegido = presupuestoElegido;
		this.criterioCorrecto = criterioCorrecto;
		this.compra = compra;
	}
	public Mensaje() {
		
	}

	public boolean isCantidadPresupuestosIndicada() {
		return cantidadPresupuestosIndicada;
	}

	public void setCantidadPresupuestosIndicada(boolean cantidadPresupuestosIndicada) {
		this.cantidadPresupuestosIndicada = cantidadPresupuestosIndicada;
	}

	public boolean isPresupuestoElegido() {
		return presupuestoElegido;
	}

	public void setPresupuestoElegido(boolean presupuestoElegido) {
		this.presupuestoElegido = presupuestoElegido;
	}

	public boolean isCriterioCorrecto() {
		return criterioCorrecto;
	}

	public void setCriterioCorrecto(boolean criterioCorrecto) {
		this.criterioCorrecto = criterioCorrecto;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	

}
