package validadorDeCompras;

import java.util.LinkedList;
import java.util.Queue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import comprasPresupuestos.Compra;
import comprasPresupuestos.CriterioSeleccionPresupuesto;
import comprasPresupuestos.Presupuesto;
import persistencia.CompraMapperBD;
import persistencia.MensajeMapper;


@Entity
public class ValidadorCompras {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	static Queue<Compra> listaCompra = new LinkedList<Compra>(); // Deberian cargarse las comrpas en esta lista, y el validador deberia ir tomando cada compra como un FIFO.

	private static ValidadorCompras instance = new ValidadorCompras();
	public static ValidadorCompras getInstance() {
		return instance;
	}
	private ValidadorCompras(){

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static Queue<Compra> getListaCompra() {
		return listaCompra;
	}

	public static void setListaCompra(Queue<Compra> listaCompra) {
		ValidadorCompras.listaCompra = listaCompra;
	}





	public void notificar(Compra compra) {
		listaCompra.add(compra);
	}

	public static boolean verificarCantidad(Compra compra){
		return compra.getPresupuestos().size() >= compra.getCantidadMinimaPresupuestos();	
	}

	public static boolean verificarPresupuestoElegido(Compra compra){
		return compra.getPresupuestos().stream()
				.anyMatch(presupuesto->presupuesto.getDetalles().stream()
						.allMatch(detallePresupuesto->detallePresupuesto.coincidePrecio()));
	}

	public static boolean verificarCriterio(Compra compra){
		CriterioSeleccionPresupuesto criterio = compra.getCriterio();
		if(criterio == null)
			return true;
		if(compra.getCantidadMinimaPresupuestos() == 0)
			return true;
		if(compra.getCantidadMinimaPresupuestos() > 0 && compra.getPresupuestos() == null)
			return false;
		if(compra.getCantidadMinimaPresupuestos() > 0 && compra.getPresupuestos().size() == 0)
			return false;
		Presupuesto presCriterio = criterio.obtenerPresupuesto(compra);
		
		return presCriterio.equals(compra.getPresupuestoElegido());
	}

	public void validar(){
		
		
		listaCompra = CompraMapperBD.obtenerComprasParaValidar();
		while(listaCompra.size() > 0) {
			Compra compra = listaCompra.poll();
			compra.setFueValidada(true);
			CompraMapperBD.getInstance().update(compra);			
			Mensaje mensaje = new Mensaje();
			mensaje.setCompra(compra);
			mensaje.setCantidadPresupuestosIndicada(verificarCantidad(compra));
			System.out.printf("Verifica cantidad: ");
			System.out.printf(Boolean.toString(verificarCantidad(compra)));
			System.out.println();
			mensaje.setPresupuestoElegido(verificarPresupuestoElegido(compra));
			System.out.printf("Verifica presupuesto elegido: ");
			System.out.printf(Boolean.toString(verificarPresupuestoElegido(compra)));
			System.out.println();
			mensaje.setCriterioCorrecto(verificarCriterio(compra));
			System.out.printf("Verifica criterio: ");
			System.out.printf(Boolean.toString(verificarCriterio(compra)));
			System.out.println();
			MensajeMapper.getInstance().insert(mensaje);
			NotificarRevisores nr = NotificarRevisores.getInstance();
			nr.notificar(compra.getRevisores(),mensaje);			
		}
		
	}
	
	

}

