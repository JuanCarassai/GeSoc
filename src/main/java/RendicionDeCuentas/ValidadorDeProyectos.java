package RendicionDeCuentas;

import egresosIngresos.OperacionEgreso;

public class ValidadorDeProyectos {

	
	public boolean validar(ProyectoDeFinanciacion proyecto) {
		float MontoTotalEgresos = 0;
		
		for(OperacionEgreso egreso : proyecto.getEgresos()) {
			MontoTotalEgresos += egreso.getValorDeEgreso();
		}
		
		return MontoTotalEgresos <= proyecto.getMontoLimiteSinPresupuesto();
		
				
	}
}
