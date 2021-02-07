package egresosIngresos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.quartz.SchedulerException;

import com.example.demoquartz.QuartzSchedulerCronTriggerExample;

import comprasPresupuestos.Compra;
import comprasPresupuestos.Presupuesto;
import comprasPresupuestos.PresupuestoDetallado;
import comprasPresupuestos.Producto;
import comprasPresupuestos.ProveedorMenorValor;
import validadorDeCompras.Usuario;
import validadorDeCompras.ValidadorCompras;

public class Consola {
	private static ValidadorCompras validador = ValidadorCompras.getInstance();
	private static QuartzSchedulerCronTriggerExample scheduler = QuartzSchedulerCronTriggerExample.getInstance();
	public static void ejecutar() throws SchedulerException, InterruptedException{	
		scheduler.iniciar();
		Producto producto1 = new Producto("heladera",2000);
		Producto producto2 = new Producto("televisor",300);
		Producto producto3 = new Producto("televisor",350);
		List<Producto> listaProductos = Arrays.asList(producto1,producto2,producto3);
		
		PresupuestoDetallado detalle1 = new PresupuestoDetallado(2000, producto1);
		PresupuestoDetallado detalle2 = new PresupuestoDetallado(300, producto2);
		PresupuestoDetallado detalle3 = new PresupuestoDetallado(350, producto3);
		
		PresupuestoDetallado detalle4 = new PresupuestoDetallado(25, producto1);
		PresupuestoDetallado detalle5 = new PresupuestoDetallado(30, producto2);
		PresupuestoDetallado detalle6 = new PresupuestoDetallado(50, producto3);
		
		PresupuestoDetallado detalle7 = new PresupuestoDetallado(200000, producto1);
		PresupuestoDetallado detalle8 = new PresupuestoDetallado(30000, producto2);
		PresupuestoDetallado detalle9 = new PresupuestoDetallado(5000, producto3);
		
		List<PresupuestoDetallado> detalles1 = Arrays.asList(detalle1,detalle2,detalle3);
		List<PresupuestoDetallado> detalles2 = Arrays.asList(detalle4,detalle5,detalle6);
		List<PresupuestoDetallado> detalles3 = Arrays.asList(detalle7,detalle8,detalle9);

		ArrayList<Presupuesto> listaPresupuestos = new ArrayList<Presupuesto>();
		Presupuesto presupuesto1 = new Presupuesto(null,null,detalles1);
		Presupuesto presupuesto2 = new Presupuesto(null,null,detalles2);
		Presupuesto presupuesto3 = new Presupuesto(null,null,detalles3);

		listaPresupuestos.add(presupuesto1);
		listaPresupuestos.add(presupuesto2);
		listaPresupuestos.add(presupuesto3);

		ArrayList<Usuario> listaUsuariosRevisores = new ArrayList<Usuario>();
		Usuario usuario1 = new Usuario(null,null, null, null);
		Usuario usuario2 = new Usuario(null,null, null, null);
		Usuario usuario3 = new Usuario(null,null, null, null);
		Usuario usuario4 = new Usuario(null,null, null, null);


		listaUsuariosRevisores.add(usuario1);
		listaUsuariosRevisores.add(usuario2);
		listaUsuariosRevisores.add(usuario3);

		ProveedorMenorValor criterio = ProveedorMenorValor.getInstance();

		Compra compra = new Compra(null, listaProductos, listaPresupuestos,presupuesto1, 3,listaUsuariosRevisores, criterio);
		Compra compra2 = new Compra(null, listaProductos, listaPresupuestos,presupuesto3, 5,listaUsuariosRevisores, criterio);
		Compra compra3 = new Compra(null, listaProductos, listaPresupuestos,presupuesto2, 3,listaUsuariosRevisores, criterio);
		

		compra.agregarRevisor(usuario4);
		
		validador.notificar(compra);
		Thread.sleep(10000);
		validador.notificar(compra2);
		Thread.sleep(10000);
		validador.notificar(compra3);
		Thread.sleep(10000);

	}
	
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		Consola.ejecutar();
	}

}
