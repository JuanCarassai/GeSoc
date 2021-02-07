package persistencia;

import comprasPresupuestos.CriterioSeleccionPresupuesto;

public class CriterioSeleccionPresupuestoMapperBD  extends MapperBD<CriterioSeleccionPresupuesto>{
	private static final CriterioSeleccionPresupuestoMapperBD instance = new CriterioSeleccionPresupuestoMapperBD();
	
	private CriterioSeleccionPresupuestoMapperBD () {}
	public static CriterioSeleccionPresupuestoMapperBD getInstance() {
		return instance;
	}
}
