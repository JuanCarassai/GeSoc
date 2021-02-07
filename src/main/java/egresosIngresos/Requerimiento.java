package egresosIngresos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import persistencia.BDUtils;
import persistencia.OperacionEgresoMapperBD;
import persistencia.OperacionIngresoMapperBD;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Requerimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Transient
	private IngresosEgresos restante = new IngresosEgresos(new ArrayList<OperacionEgreso>(),
			new ArrayList<OperacionIngreso>());

	public IngresosEgresos vincular(ReglaVinculacion regla) {
		BDUtils.getEntityManager().clear();
		List<OperacionIngreso> ingresosAVincular = OperacionIngresoMapperBD.getInstance()
				.obtenerIngresosQueSeanVinculables();
		List<OperacionEgreso> egresosAVincular = OperacionEgresoMapperBD.getInstance()
				.obtenerEgresosQueSeanVinculables();
		
		if(ingresosAVincular.size()>0 && egresosAVincular.size()>0) {
		IngresosEgresos ingresosEgresosOrdenados = ordenar(egresosAVincular, ingresosAVincular);

		int indexEgreso = 0;

		int sizeEgreso = ingresosEgresosOrdenados.getEgresosRestantes().size();

		for (OperacionIngreso ingresoAVincular : ingresosEgresosOrdenados.getIngresosRestantes()) {
			while (indexEgreso < sizeEgreso) {

				OperacionEgreso egresoAVincular = ingresosEgresosOrdenados.getEgresosRestantes().get(indexEgreso);
				if (regla.esVinculable(ingresoAVincular, egresoAVincular)) {
					ingresosEgresosOrdenados.getEgresosRestantes().remove(indexEgreso);
					ingresoAVincular.getEgresos().add(egresoAVincular);
					egresoAVincular.setIngreso(ingresoAVincular);
					egresoAVincular.setIdIngreso(ingresoAVincular.getId());
					indexEgreso = 0;
					sizeEgreso = ingresosEgresosOrdenados.getEgresosRestantes().size();
					continue;
				} else {
					restante.getEgresosRestantes().add(egresoAVincular);
					restante.getIngresosRestantes().add(ingresoAVincular);
				}
				indexEgreso++;
			}
			indexEgreso = 0;
		}
		OperacionIngresoMapperBD.getInstance().updateAll(ingresosAVincular);
		OperacionEgresoMapperBD.getInstance().updateAll(egresosAVincular);
		}
		return restante;
	}

	public abstract IngresosEgresos ordenar(List<OperacionEgreso> egresosAVincular,
			List<OperacionIngreso> ingresoAVincular);
}
