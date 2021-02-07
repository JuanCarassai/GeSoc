package egresosIngresos;

import javax.persistence.Entity;

@Entity
public class ReglaFecha extends ReglaVinculacion {
	private static final ReglaFecha instance = new ReglaFecha();

	private ReglaFecha() {
	}

	public static ReglaFecha getInstance() {
		return instance;
	}

	public boolean esVinculable(OperacionIngreso ingresoAVincular, OperacionEgreso egresoAVincular) {
		boolean primero = (egresoAVincular.getFechaOperacion().compareTo(ingresoAVincular.getFechaInicio())) > 0;
		boolean segundo = (egresoAVincular.getFechaOperacion().compareTo(ingresoAVincular.getFechaFin())) < 0;
		boolean tercero = (ingresoAVincular.getMontoTotal() - ingresoAVincular.getMontoTotalEgresos() ) >= egresoAVincular.getValorDeEgreso();

		return primero && segundo && tercero;
	}
}
