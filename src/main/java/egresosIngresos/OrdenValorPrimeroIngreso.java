package egresosIngresos;

import java.util.Comparator;
import java.util.List;

import javax.persistence.Entity;

import persistencia.BDUtils;
import persistencia.OperacionEgresoMapperBD;
import persistencia.OperacionIngresoMapperBD;

@Entity
public class OrdenValorPrimeroIngreso extends Requerimiento {
	private static final OrdenValorPrimeroIngreso instance = new OrdenValorPrimeroIngreso();

	private OrdenValorPrimeroIngreso() {
	}

	public static OrdenValorPrimeroIngreso getInstance() {
		return instance;
	}

	@Override
	public IngresosEgresos ordenar(List<OperacionEgreso> egresosAVincular, List<OperacionIngreso> ingresosAVincular) {
		egresosAVincular.sort(Comparator.comparing(OperacionEgreso::getValorDeEgreso));
		ingresosAVincular.sort(Comparator.comparing(OperacionIngreso::getMontoTotal));
		return new IngresosEgresos(egresosAVincular, ingresosAVincular);
	}

	@Override
	public IngresosEgresos vincular(ReglaVinculacion regla) {
		BDUtils.getEntityManager().clear();
		List<OperacionIngreso> ingresosAVincular = OperacionIngresoMapperBD.getInstance()
				.obtenerIngresosQueSeanVinculables();
		List<OperacionEgreso> egresosAVincular = OperacionEgresoMapperBD.getInstance()
				.obtenerEgresosQueSeanVinculables();

		ingresosAVincular.sort(Comparator.comparing(OperacionIngreso::getMontoTotal));
		int index = 0;
		int sizeEgresos = egresosAVincular.size();
		while (index < sizeEgresos) {
			OperacionEgreso unEgreso = egresosAVincular.get(index);
			for (OperacionIngreso unIngreso : ingresosAVincular) {
				if (regla.esVinculable(unIngreso, unEgreso)) {
					unIngreso.getEgresos().add(unEgreso);
					unEgreso.setIdIngreso(unIngreso.getId());
					unEgreso.setIngreso(unIngreso);
					break;
				}
			}
			index++;
		}
		OperacionIngresoMapperBD.getInstance().updateAll(ingresosAVincular);
		OperacionEgresoMapperBD.getInstance().updateAll(egresosAVincular);
		
		return null; //TODO Y ESTE NULL
	}

}
