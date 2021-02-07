package egresosIngresos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class OrdenMix extends Requerimiento {

	@OneToMany
	private List<Requerimiento> requerimientos;

	public List<Requerimiento> getRequerimientos() {
		return requerimientos;
	}

	public void setRequerimientos(List<Requerimiento> requerimientos) {
		this.requerimientos = requerimientos;
	}

	@Override
	public IngresosEgresos vincular(ReglaVinculacion regla) {
		for (Requerimiento requerimiento : requerimientos) {
			requerimiento.vincular(regla);
//			if (restante.getEgresosRestantes().isEmpty() || restante.getIngresosRestantes().isEmpty()) {
//				break;
//			}
		}
		return null;
	}

	@Override
	public IngresosEgresos ordenar(List<OperacionEgreso> egresosAVincular, List<OperacionIngreso> ingresoAVincular) {
		// TODO Auto-generated method stub
		return null;
	}

}
