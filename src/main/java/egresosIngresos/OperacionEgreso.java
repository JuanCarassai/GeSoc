package egresosIngresos;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.API.MedioDePago;

import comprasPresupuestos.Compra;
import persistencia.OperacionEgresoMapperBD;
@Entity
public class OperacionEgreso {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate fechaOperacion;
	
	@OneToOne(fetch = FetchType.LAZY)
	private DocumentoComercial comprobante;

	@ManyToOne(fetch = FetchType.LAZY)
	private MedioDePago medioDePago;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Organizacion organizacion;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	private Compra compra;
	
	@ManyToOne
	private Proveedor proveedor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private OperacionIngreso ingreso;
	
	private Long idIngreso = (long) -1;
	
	private float montoTotal;
	
	public OperacionEgreso() {}

	public OperacionEgreso(Long id, DocumentoComercial comprobante, LocalDate fechaOperacion, MedioDePago medioDePago,
			Organizacion organizacion, Compra compra, Proveedor proveedor, OperacionIngreso ingreso) {
		super();
		this.id = id;
		this.comprobante = comprobante;
		this.fechaOperacion = fechaOperacion;
		this.medioDePago = medioDePago;
		this.organizacion = organizacion;
		this.compra = compra;
		this.proveedor = proveedor;
		this.ingreso= ingreso;
	}
	


	@Override
	public String toString() {
		return "OperacionEgreso [id=" + id + ", fechaOperacion=" + fechaOperacion + ", comprobante=" + comprobante
				+ ", medioDePago=" + medioDePago + ", organizacion=" + organizacion + ", compra=" + compra
				+ ", proveedor=" + proveedor + ", ingreso=" + ingreso + "]";
	}

	public long getId() {
		return id;
	}
	
	

	public Long getIdIngreso() {
		return idIngreso;
	}

	public void setIdIngreso(Long idIngreso) {
		this.idIngreso = idIngreso;
	}

	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public OperacionEgreso(DocumentoComercial comprobante, LocalDate fechaOperacion, MedioDePago medioDePago,
			Organizacion organizacion, Compra compra, Proveedor proveedor) {
		super();
		this.comprobante = comprobante;
		this.fechaOperacion = fechaOperacion;
		this.medioDePago = medioDePago;
		this.organizacion = organizacion;
		this.compra = compra;
		this.proveedor = proveedor;
	}
	public DocumentoComercial getComprobante() {
		return comprobante;
	}
	public void setComprobante(DocumentoComercial comprobante) {
		this.comprobante = comprobante;
	}
	public LocalDate getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(LocalDate fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public MedioDePago getMedioDePago() {
		return medioDePago;
	}
	public void setMedioDePago(MedioDePago medioDePago) {
		this.medioDePago = medioDePago;
	}
	public Organizacion getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public OperacionIngreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(OperacionIngreso ingreso) {
		this.ingreso = ingreso;
	}

	public float getValorDeEgreso() {
		return compra.isRequierePresupuestos() ?
				compra.getPresupuestoElegido().getValorTotal() : compra.getValorTotal();
	}

	public static List<OperacionEgreso> buscarEgresos() {
		return OperacionEgresoMapperBD.getInstance().obtenerEgresos();
	}

	public static void insertarNuevoEgresoEnBD(OperacionEgreso nuevaOperacionEgreso) {
		OperacionEgresoMapperBD.getInstance().insert(nuevaOperacionEgreso);
		
	}

	public static OperacionEgreso buscarEgresoPorIdEnBD(Long identificadorOperacion) {
		return OperacionEgresoMapperBD.getInstance().buscarEgresoPorId(identificadorOperacion);
	}

	public static List<OperacionEgreso> buscarEgresosLazy() {
		return OperacionEgresoMapperBD.getInstance().obtenerEgresosLazy();
	}

	public float getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}



}
