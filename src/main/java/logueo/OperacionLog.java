package logueo;

import java.time.LocalDateTime;

public class OperacionLog {
	
	private TipoOperacion tipoOperacion; //alta, baja o modificacion
	private String entidadAfectada;
	private LocalDateTime fecha;
	
	public TipoOperacion getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public String getEntidadAfectada() {
		return entidadAfectada;
	}
	public void setEntidadAfectada(String entidadAfectada) {
		this.entidadAfectada = entidadAfectada;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
}
