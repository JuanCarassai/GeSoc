package RendicionDeCuentas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import comprasPresupuestos.Presupuesto;
import egresosIngresos.OperacionEgreso;
import egresosIngresos.OperacionIngreso;
import persistencia.ProyectoDeFinanciacionMapperBD;
import validadorDeCompras.Usuario;

@Entity
public class ProyectoDeFinanciacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Usuario directorResponsable;
	private Float montoTotalAsignado;
	private Float montoLimiteSinPresupuesto;
	private int cantPresupuestosMinima;
	@ManyToMany
	private List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
	@ManyToMany
	private List<OperacionIngreso> ingresos = new ArrayList<OperacionIngreso>();
	
	@ManyToMany
	private List<OperacionEgreso> egresos = new ArrayList<OperacionEgreso>();

	public ProyectoDeFinanciacion(Long id, Usuario directorResponsable, Float montoTotalAsignado,
			int cantPresupuestosMinima, List<Presupuesto> presupuestos, List<OperacionIngreso> ingresos) {
		super();
		this.id = id;
		this.directorResponsable = directorResponsable;
		this.montoTotalAsignado = montoTotalAsignado;
		this.cantPresupuestosMinima = cantPresupuestosMinima;
		this.presupuestos = presupuestos;
		this.ingresos = ingresos;
	}

	public ProyectoDeFinanciacion() {
		super();
	}

	public Float getMontoLimiteSinPresupuesto() {
		return montoLimiteSinPresupuesto;
	}

	public void setMontoLimiteSinPresupuesto(Float montoLimiteSinPresupuesto) {
		this.montoLimiteSinPresupuesto = montoLimiteSinPresupuesto;
	}

	public List<OperacionEgreso> getEgresos() {
		return egresos;
	}

	public void setEgresos(List<OperacionEgreso> egresos) {
		this.egresos = egresos;
	}

	public Usuario getDirectorResponsable() {
		return directorResponsable;
	}

	public void setDirectorResponsable(Usuario directorResponsable) {
		this.directorResponsable = directorResponsable;
	}

	public Float getMontoTotalAsignado() {
		return montoTotalAsignado;
	}

	public void setMontoTotalAsignado(Float montoTotalAsignado) {
		this.montoTotalAsignado = montoTotalAsignado;
	}

	public int getCantPresupuestosMinima() {
		return cantPresupuestosMinima;
	}

	public void setCantPresupuestosMinima(int cantPresupuestos) {
		this.cantPresupuestosMinima = cantPresupuestos;
	}

	public List<Presupuesto> getPresupuestos() {
		return presupuestos;
	}

	public void setPresupuestos(List<Presupuesto> presupuestos) {
		this.presupuestos = presupuestos;
	}

	public List<OperacionIngreso> getIngresos() {
		return ingresos;
	}

	public void setIngresos(List<OperacionIngreso> ingresos) {
		this.ingresos = ingresos;
	}

	public static void insertarNuevoProyectoEnBD(ProyectoDeFinanciacion proyectoNuevo) {
		ProyectoDeFinanciacionMapperBD.getInstance().insert(proyectoNuevo);

	}

	public Long getId() {
		return id;
	}

	public static ProyectoDeFinanciacion buscarProyectoPorIdentificadorEnBD(Long identificadorPresupuesto) {
		return ProyectoDeFinanciacionMapperBD.getInstance().buscarProyectoPorIdentificador(identificadorPresupuesto);
	}

	public static void ActualizarProyecto(ProyectoDeFinanciacion proyectoEncontrado) {
		ProyectoDeFinanciacionMapperBD.getInstance().update(proyectoEncontrado);
		
	}

	
	

}
