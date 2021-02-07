package comprasPresupuestos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import persistencia.PresupuestoDetalladoMapperBD;

@Entity
public class PresupuestoDetallado {
	public PresupuestoDetallado(float precio, Producto productoCompra) {
		super();
		this.precio = precio;
		this.productoCompra = productoCompra;
	}
	
	public PresupuestoDetallado() {}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private float precio;
	
	@ManyToOne
	private Producto productoCompra;
	
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public Producto getProductoCompra() {
		return productoCompra;
	}
	public void setProductoCompra(Producto productoCompra) {
		this.productoCompra = productoCompra;
	}
	
	public Long getId() {
		return id;
	}

	public boolean coincidePrecio() {
		return precio == productoCompra.getValor();
	}

	public static void insertarNuevoPDEnBD(PresupuestoDetallado presupuestoDetalladoNuevo) {
		PresupuestoDetalladoMapperBD.getInstance().insert(presupuestoDetalladoNuevo);
		
	}

	public static List<PresupuestoDetallado> obtenerTodosEnBD() {
		return PresupuestoDetalladoMapperBD.getInstance().obtenerTodos();
	}
}
