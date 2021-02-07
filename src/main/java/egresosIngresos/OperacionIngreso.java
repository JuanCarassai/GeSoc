package egresosIngresos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import persistencia.OperacionIngresoMapperBD;

@Entity
public class OperacionIngreso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;


	private String descripcion;


	private float montoTotal;


	private LocalDate fechaOperacion;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OperacionEgreso> egresos = new ArrayList<OperacionEgreso>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Organizacion organizacion;

	private LocalDate fechaInicio;

	private LocalDate fechaFin;

	@Override
	public String toString() {
		return "OperacionIngreso [id=" + id + ", descripcion=" + descripcion + ", montoTotal=" + montoTotal
				+ ", egresos=" + egresos + ", fechaOperacion=" + fechaOperacion + "]";
	}

	public OperacionIngreso() {
	}

	public OperacionIngreso(String descripcion, float montoTotal, LocalDate fechaOperacion,
			List<OperacionEgreso> egresos, Organizacion organizacion) {
		super();
		this.descripcion = descripcion;
		this.montoTotal = montoTotal;
		this.fechaOperacion = fechaOperacion;
		this.egresos = egresos;
		this.organizacion = organizacion;
		this.fechaInicio = sumarDiasAFecha(fechaOperacion, -5);
		this.fechaFin = sumarDiasAFecha(fechaOperacion, 5);
	}

	public Long getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}

	public LocalDate getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(LocalDate fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public List<OperacionEgreso> getEgresos() {
		return egresos;
	}

	public void setEgresos(List<OperacionEgreso> egresos) {
		this.egresos = egresos;
	}

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public static LocalDate sumarDiasAFecha(LocalDate fecha, int dias) {

		return fecha.plusDays(dias);

	}

	public static void insertarEnBD(OperacionIngreso ingreso) {
		OperacionIngresoMapperBD.getInstance().insert(ingreso);
	}

	public static List<OperacionIngreso> buscarIngresos() {
		return OperacionIngresoMapperBD.getInstance().obtenerIngresos();
	}

	public static OperacionIngreso buscarIngresoPorIdEnBD(Long identificadorOperacion) {
		return OperacionIngresoMapperBD.getInstance().buscarIngresoPorId(identificadorOperacion);
	}

	public float getMontoTotalEgresos() {
		float monto = 0;
		if (egresos != null) {
			for (OperacionEgreso egreso : egresos) {
				monto += egreso.getValorDeEgreso();
			}
		}
		return monto;
	}

}
