package utn.disenio.tp;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import comprasPresupuestos.Compra;
import comprasPresupuestos.Presupuesto;
import comprasPresupuestos.Producto;
import comprasPresupuestos.ProveedorMenorValor;
import validadorDeCompras.Usuario;

public class CompraTest 
{

	@Test
	public void validarMethodsCompra() {
		Producto producto1 = new Producto("heladera",2000);
		Producto producto2 = new Producto("televisor",300);
		Producto producto3 = new Producto("televisor",350);
		List<Producto> listaProductos = Arrays.asList(producto1,producto2,producto3);
		
		ArrayList<Presupuesto> listaPresupuestos = new ArrayList<Presupuesto>();
		Presupuesto presupuesto1 = new Presupuesto(null,null,null);
		Presupuesto presupuesto2 = new Presupuesto(null,null,null);
		Presupuesto presupuesto3 = new Presupuesto(null,null,null);
		
		listaPresupuestos.add(presupuesto1);
		listaPresupuestos.add(presupuesto2);
		listaPresupuestos.add(presupuesto3);
		
		ArrayList<Usuario> listaUsuariosRevisores = new ArrayList<Usuario>();
		Usuario usuario1 = new Usuario(null,null,null, null);
		Usuario usuario2 = new Usuario(null,null,null, null);
		Usuario usuario3 = new Usuario(null,null,null, null);
		Usuario usuario4 = new Usuario(null,null,null, null);
						
		
		listaUsuariosRevisores.add(usuario1);
		listaUsuariosRevisores.add(usuario2);
		listaUsuariosRevisores.add(usuario3);
		
		ProveedorMenorValor criterio =  ProveedorMenorValor.getInstance();
		
		Compra compra = new Compra(null, listaProductos, listaPresupuestos,presupuesto1, 3,listaUsuariosRevisores, criterio);
		
		compra.agregarRevisor(usuario4);
		
		assertTrue(compra.getValorTotal() == 2650);
		assertTrue(compra.getPresupuestoElegido()== presupuesto1);
		assertTrue(compra.isRequierePresupuestos());
		assertTrue(compra.getCriterio()== criterio);
		assertTrue(compra.getCantidadMinimaPresupuestos()== 3);
	}
}
