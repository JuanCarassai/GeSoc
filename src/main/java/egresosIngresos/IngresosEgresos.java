package egresosIngresos;

import java.util.List;

public class IngresosEgresos {
	public IngresosEgresos(List<OperacionEgreso> egresosRestantes, List<OperacionIngreso> ingresosRestantes) {
		super();
		this.egresosRestantes = egresosRestantes;
		this.ingresosRestantes = ingresosRestantes;
	}
	private List<OperacionEgreso> egresosRestantes;
	private List<OperacionIngreso> ingresosRestantes;
	public List<OperacionEgreso> getEgresosRestantes() {
		return egresosRestantes;
	}
	public void setEgresosRestantes(List<OperacionEgreso> egresosRestantes) {
		this.egresosRestantes = egresosRestantes;
	}
	public List<OperacionIngreso> getIngresosRestantes() {
		return ingresosRestantes;
	}
	public void setIngresosRestantes(List<OperacionIngreso> ingresosRestantes) {
		this.ingresosRestantes = ingresosRestantes;
	}
	
}
