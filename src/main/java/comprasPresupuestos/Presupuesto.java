package comprasPresupuestos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import egresosIngresos.DocumentoComercial;
import egresosIngresos.Proveedor;
import persistencia.PresupuestoMapperBD;

@Entity
public class Presupuesto {
	//1 presupuesto con su lista de egresos de 1 proovedor
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Proveedor proveedor;
	
	@OneToMany
	@JoinColumn(name = "id_presupuesto")
	private List<DocumentoComercial> documentosComerciales = new ArrayList<DocumentoComercial>();
	
	@ManyToMany
	@JoinColumn(name = "id_presupuesto")
	private List<PresupuestoDetallado> detalles = new ArrayList<PresupuestoDetallado>();

	public Presupuesto(Proveedor proveedor, List<DocumentoComercial> documentosComerciales,
			List<PresupuestoDetallado> detalles1) {
		super();
		this.proveedor = proveedor;
		this.documentosComerciales = documentosComerciales;
		this.detalles = detalles1;
	}

	public Presupuesto() {}
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public Long getId() {
		return id;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<DocumentoComercial> getDocumentosComerciales() {
		return documentosComerciales;
	}

	public void setDocumentosComerciales(List<DocumentoComercial> documentosComerciales) {
		this.documentosComerciales = documentosComerciales;
	}

	public List<PresupuestoDetallado> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<PresupuestoDetallado> detalles) {
		this.detalles = detalles;
	}

	public float getValorTotal() {
		float total = 0;
		for(PresupuestoDetallado unDetalle : detalles) { // For Each
			total += unDetalle.getPrecio();
		}
		// detalles.stream().forEach(detalle -> total += detalle.getPrecio());
		return total;
	}

	public static Presupuesto buscarPresupuestoPorIdEnBD(Long identificadorOperacion) {
		return PresupuestoMapperBD.getInstance().buscarPresupuestoPorId(identificadorOperacion);
	}

	public static List<Presupuesto> obtenerTodosEnBD() {
		return PresupuestoMapperBD.getInstance().obtenerTodos();
	}

	public static void insertarNuevoPresupuestoEnBD(Presupuesto presupuestoNuevo) {
		PresupuestoMapperBD.getInstance().insert(presupuestoNuevo);
		
	}

	/* Esto seria Interfaz, donde se ve el detalle de los productos

	public static void mostrarDetalle() {
		for(int i = 0; i < compra.getProductos().size(); i++) {
			System.out.println(compra.getProductos().get(i).getDescripcion());
			System.out.println(compra.getProductos().get(i).getValor());
			}
	}
	 */

}
