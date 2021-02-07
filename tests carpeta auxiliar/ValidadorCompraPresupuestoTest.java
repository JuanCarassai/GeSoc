package utn.disenio.tp;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import comprasPresupuestos.Compra;
import comprasPresupuestos.Presupuesto;
import comprasPresupuestos.PresupuestoDetallado;
import comprasPresupuestos.Producto;
import comprasPresupuestos.ProveedorMenorValor;
import validadorDeCompras.Mensaje;
import validadorDeCompras.Usuario;
import validadorDeCompras.ValidadorCompras;

public class ValidadorCompraPresupuestoTest {

	
	@Test
public void testCantidad() {
		
		List<Producto> listaProducto = new ArrayList<Producto>();
		ArrayList<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
		Presupuesto presupuesto1 = new Presupuesto(null,null,null);
		Presupuesto presupuesto2 = new Presupuesto(null,null,null);
		Presupuesto presupuesto3 = new Presupuesto(null,null,null);
		presupuestos.add(presupuesto1);
		presupuestos.add(presupuesto2);
		presupuestos.add(presupuesto3);
		ArrayList<Mensaje> bandejaDeMensajes1 = new ArrayList<Mensaje>();
		ArrayList<Mensaje> bandejaDeMensajes2 = new ArrayList<Mensaje>();
		ArrayList<Mensaje> bandejaDeMensajes3 = new ArrayList<Mensaje>();
		ArrayList<Mensaje> bandejaDeMensajes4 = new ArrayList<Mensaje>();
		Usuario usuario1 = new Usuario(null,null,null, bandejaDeMensajes1);
		Usuario usuario2 = new Usuario(null,null,null,bandejaDeMensajes2);
		Usuario usuario3 = new Usuario(null,null,null,bandejaDeMensajes3);
		Usuario usuario4 = new Usuario(null,null,null,bandejaDeMensajes4);
		
		
		
		ArrayList<Usuario> revisores = new ArrayList<Usuario>();
		revisores.add(usuario1);
		revisores.add(usuario2);
		revisores.add(usuario3);
		revisores.add(usuario4);
		ProveedorMenorValor criterio =  ProveedorMenorValor.getInstance();
		
		
		Compra compra = new Compra(null, listaProducto, presupuestos,presupuesto1, 3,revisores, criterio);

		assertTrue(ValidadorCompras.verificarCantidad(compra));
		
		Compra compra2 = new Compra(null, listaProducto, presupuestos,presupuesto1, 6,revisores, criterio);
		
		assertTrue(!ValidadorCompras.verificarCantidad(compra2));
		
	}
	
	@Test
	public void testPresupuestoElegido() {



		ArrayList<Mensaje> bandejaDeMensajes1 = new ArrayList<Mensaje>();
		ArrayList<Mensaje> bandejaDeMensajes2 = new ArrayList<Mensaje>();
		ArrayList<Mensaje> bandejaDeMensajes3 = new ArrayList<Mensaje>();
		ArrayList<Mensaje> bandejaDeMensajes4 = new ArrayList<Mensaje>();
		Usuario usuario1 = new Usuario(null,null,null,bandejaDeMensajes1);
		Usuario usuario2 = new Usuario(null,null,null,bandejaDeMensajes2);
		Usuario usuario3 = new Usuario(null,null,null,bandejaDeMensajes3);
		Usuario usuario4 = new Usuario(null,null,null,bandejaDeMensajes4);
		
		
		Producto producto1 = new Producto("manzanas",120) ;
		PresupuestoDetallado presDetallado1 = new PresupuestoDetallado(120,producto1);
		
		
		Producto producto2 = new Producto("naranjas",160) ;
		PresupuestoDetallado presDetallado2 = new PresupuestoDetallado(160,producto2);
		
		Producto producto3 = new Producto("peras",200) ;
		PresupuestoDetallado presDetallado3 = new PresupuestoDetallado(180,producto3);
		
		Producto producto4 = new Producto("duraznos",400) ;
		PresupuestoDetallado presDetallado4 = new PresupuestoDetallado(250,producto4);
		
		ArrayList<PresupuestoDetallado> detalles1 = new ArrayList<PresupuestoDetallado>();
		detalles1.add(presDetallado1);
		detalles1.add(presDetallado2);
		
		ArrayList<PresupuestoDetallado> detalles2 = new ArrayList<PresupuestoDetallado>();
		detalles2.add(presDetallado2);
		detalles2.add(presDetallado3);
		
		ArrayList<PresupuestoDetallado> detalles3 = new ArrayList<PresupuestoDetallado>();
		detalles3.add(presDetallado3);
		detalles3.add(presDetallado4);
		
		Presupuesto presupuesto1 = new Presupuesto(null,null,null);
		Presupuesto presupuesto2 = new Presupuesto(null,null,null);
		Presupuesto presupuesto3 = new Presupuesto(null,null,null);	
		presupuesto1.setDetalles(detalles1);
		presupuesto2.setDetalles(detalles2);
		presupuesto3.setDetalles(detalles3);
		
		
		ArrayList<Presupuesto> presupuestosValidos = new ArrayList<Presupuesto>();	
		presupuestosValidos.add(presupuesto1);
		presupuestosValidos.add(presupuesto1);
		presupuestosValidos.add(presupuesto1);
		
		ArrayList<Presupuesto> presupuestosAlgunoValido = new ArrayList<Presupuesto>();	
		presupuestosAlgunoValido.add(presupuesto1);
		presupuestosAlgunoValido.add(presupuesto2);
		presupuestosAlgunoValido.add(presupuesto3);
		
		ArrayList<Presupuesto> presupuestosTodosInvalidos = new ArrayList<Presupuesto>();	
		presupuestosTodosInvalidos.add(presupuesto2);
		presupuestosTodosInvalidos.add(presupuesto2);
		presupuestosTodosInvalidos.add(presupuesto3);
		
		ArrayList<Usuario> revisores = new ArrayList<Usuario>();
		revisores.add(usuario1);
		revisores.add(usuario2);
		revisores.add(usuario3);
		revisores.add(usuario4);
		ProveedorMenorValor criterio =  ProveedorMenorValor.getInstance();
		
		List<Producto> listaProducto = new ArrayList<Producto>();
		listaProducto.add(producto1);
		listaProducto.add(producto2);

		
		Compra compra = new Compra(null, listaProducto, presupuestosValidos,presupuesto1, 3,revisores, criterio);

		assertTrue(ValidadorCompras.verificarPresupuestoElegido(compra));
		
		Compra compra2 = new Compra(null, listaProducto, presupuestosAlgunoValido,presupuesto1, 6,revisores, criterio);
		assertTrue(ValidadorCompras.verificarPresupuestoElegido(compra2));
		
		Compra compra3 = new Compra(null, listaProducto, presupuestosTodosInvalidos,presupuesto1, 6,revisores, criterio);
		assertTrue(!ValidadorCompras.verificarPresupuestoElegido(compra3));

	}
	
	@Test
	public void testCriterioPresupuesto() {

		ArrayList<Mensaje> bandejaDeMensajes1 = new ArrayList<Mensaje>();
		ArrayList<Mensaje> bandejaDeMensajes2 = new ArrayList<Mensaje>();
		ArrayList<Mensaje> bandejaDeMensajes3 = new ArrayList<Mensaje>();
		ArrayList<Mensaje> bandejaDeMensajes4 = new ArrayList<Mensaje>();
		Usuario usuario1 = new Usuario(null,null,null,bandejaDeMensajes1);
		Usuario usuario2 = new Usuario(null,null,null,bandejaDeMensajes2);
		Usuario usuario3 = new Usuario(null,null,null,bandejaDeMensajes3);
		Usuario usuario4 = new Usuario(null,null,null,bandejaDeMensajes4);
		
		
		Producto producto1 = new Producto("manzanas",120) ;
		PresupuestoDetallado presDetallado1 = new PresupuestoDetallado(120,producto1);
		
		
		Producto producto2 = new Producto("naranjas",160) ;
		PresupuestoDetallado presDetallado2 = new PresupuestoDetallado(160,producto2);
		
		Producto producto3 = new Producto("peras",200) ;
		PresupuestoDetallado presDetallado3 = new PresupuestoDetallado(180,producto3);
		
		Producto producto4 = new Producto("duraznos",400) ;
		PresupuestoDetallado presDetallado4 = new PresupuestoDetallado(250,producto4);
		
		ArrayList<PresupuestoDetallado> detalles1 = new ArrayList<PresupuestoDetallado>();
		detalles1.add(presDetallado1);
		detalles1.add(presDetallado2);
		
		ArrayList<PresupuestoDetallado> detalles2 = new ArrayList<PresupuestoDetallado>();
		detalles2.add(presDetallado2);
		detalles2.add(presDetallado3);
		
		ArrayList<PresupuestoDetallado> detalles3 = new ArrayList<PresupuestoDetallado>();
		detalles3.add(presDetallado3);
		detalles3.add(presDetallado4);
		
		Presupuesto presupuesto1 = new Presupuesto(null,null,null);
		Presupuesto presupuesto2 = new Presupuesto(null,null,null);
		Presupuesto presupuesto3 = new Presupuesto(null,null,null);	
		presupuesto1.setDetalles(detalles1);
		presupuesto2.setDetalles(detalles2);
		presupuesto3.setDetalles(detalles3);
		
		

		
		ArrayList<Presupuesto> presupuestos = new ArrayList<Presupuesto>();	
		presupuestos.add(presupuesto1);
		presupuestos.add(presupuesto2);
		presupuestos.add(presupuesto3);
		

		
		ArrayList<Usuario> revisores = new ArrayList<Usuario>();
		revisores.add(usuario1);
		revisores.add(usuario2);
		revisores.add(usuario3);
		revisores.add(usuario4);
		ProveedorMenorValor criterio =  ProveedorMenorValor.getInstance();
		
		List<Producto> listaProducto = new ArrayList<Producto>();
		listaProducto.add(producto1);
		listaProducto.add(producto2);

		
		Compra compra1 = new Compra(null, listaProducto, presupuestos,presupuesto1, 3,revisores, criterio);
		assertTrue(ValidadorCompras.verificarCriterio(compra1));

		Compra compra2 = new Compra(null, listaProducto, presupuestos,presupuesto2, 3,revisores, criterio);
		assertTrue(!ValidadorCompras.verificarCriterio(compra2));
		
		Compra compra3 = new Compra(null, listaProducto, presupuestos,presupuesto3, 3,revisores, criterio);
		assertTrue(!ValidadorCompras.verificarCriterio(compra3));
	}
	
}
