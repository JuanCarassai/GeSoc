package comprasPresupuestos;

import java.util.Collections;
import java.util.Comparator;

import javax.persistence.Entity;

@Entity
public class ProveedorMenorValor extends CriterioSeleccionPresupuesto {

	private static final ProveedorMenorValor instance = new ProveedorMenorValor();
	
	private ProveedorMenorValor () {}
	public static ProveedorMenorValor getInstance() {
		return instance;
	}
	
	public Presupuesto obtenerPresupuesto(Compra compra) {
		Comparator <Presupuesto> comparador = new Comparator<Presupuesto>(){
			@Override
			public int compare(Presupuesto presupuesto1,Presupuesto presupuesto2) {
				if (presupuesto1.getValorTotal()>presupuesto2.getValorTotal())
					return 1;
				else
					return -1;
			}
		};
		Presupuesto presMin = Collections.min(compra.getPresupuestos(), comparador);
		return presMin;

	}
}
