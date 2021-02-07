package comprasPresupuestos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import persistencia.CompraMapperBD;
import validadorDeCompras.Usuario;

@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long numeroCompra;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Producto> productos = new ArrayList<Producto>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_compra")
	private List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Presupuesto presupuestoElegido;
	private int cantidadMinimaPresupuestos;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Usuario> revisores = new ArrayList<Usuario>();

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private CriterioSeleccionPresupuesto criterio;

	private Long id_operacion_egreso = (long) -1;
	
	
	private boolean fueValidada = false;

	public Compra() {
	}

	public Compra(Long numeroCompra, List<Producto> productos, List<Presupuesto> presupuestos,
			Presupuesto presupuestoElegido, int cantidadMinimaPresupuestos, List<Usuario> revisores,
			CriterioSeleccionPresupuesto criterio) {
		super();
		this.numeroCompra = numeroCompra;
		this.productos = productos;
		this.presupuestos = presupuestos;
		this.presupuestoElegido = presupuestoElegido;
		this.cantidadMinimaPresupuestos = cantidadMinimaPresupuestos;
		this.revisores = revisores;
		this.criterio = criterio;
	}

	public float getValorTotal() {
		float total = 0;
		for (Producto unProducto : this.getProductos())
			total += unProducto.getValor();
		return total;
	}

	public List<Presupuesto> getPresupuestos() {
		return presupuestos;
	}

	public void setPresupuestos(List<Presupuesto> _presupuestos) {
		presupuestos = _presupuestos;
	}

	public void agregarPresupuesto(Presupuesto presupuesto) {
		presupuestos.add(presupuesto);
	}

	public Presupuesto getPresupuestoElegido() {
		return presupuestoElegido;
	}

	public void setPresupuestoElegido(Presupuesto _presupuestoElegido) {
		presupuestoElegido = _presupuestoElegido;
	}

	public boolean isRequierePresupuestos() {
		return cantidadMinimaPresupuestos > 0;
	}

	public List<Usuario> getRevisores() {
		return revisores;
	}

	public void setRevisores(List<Usuario> revisores) {
		this.revisores = revisores;
	}

	public CriterioSeleccionPresupuesto getCriterio() {
		return criterio;
	}

	public void setCriterio(CriterioSeleccionPresupuesto criterio) {
		this.criterio = criterio;
	}

	public void agregarRevisor(Usuario usuario) {
		revisores.add(usuario);
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public int getCantidadMinimaPresupuestos() {
		return cantidadMinimaPresupuestos;
	}

	public void setCantidadMinimaPresupuestos(int cantidadMinimaPresupuestos) {
		this.cantidadMinimaPresupuestos = cantidadMinimaPresupuestos;
	}

	public Long getNumeroCompra() {
		return numeroCompra;
	}

	public void setNumeroCompra(Long numeroCompra) {
		this.numeroCompra = numeroCompra;
	}

	public boolean isFueValidada() {
		return fueValidada;
	}

	public void setFueValidada(boolean fueValidada) {
		this.fueValidada = fueValidada;
	}

	
	
	public Long getId_operacion_egreso() {
		return id_operacion_egreso;
	}

	public void setId_operacion_egreso(Long id_operacion_egreso) {
		this.id_operacion_egreso = id_operacion_egreso;
	}

	public static Compra buscarCompraPorNumeroEnBD(Long numero) {
		return CompraMapperBD.getInstance().buscarCompraPorNumero(numero);
	}

	public static void insertarNuevaCompraEnBD(Compra compra) {
		CompraMapperBD.getInstance().insert(compra);
	}
	
	public static List<Compra> obtenerComprasSinEgresos() {
		return CompraMapperBD.getInstance().obtenerComprasSinEgresos();
	}

}
