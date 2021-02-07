package utn.disenio.tp;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.API.MedioDePago;

import comprasPresupuestos.Compra;
import comprasPresupuestos.Producto;
import comprasPresupuestos.ProveedorMenorValor;
import egresosIngresos.DocumentoComercial;
import egresosIngresos.OperacionEgreso;
import egresosIngresos.Organizacion;
import egresosIngresos.Proveedor;
import validadorDeCompras.Usuario;

public class OperacionEgresoTest {

	@Test
	public void validarOperacionEgreso() {
		//Documento Comercial
		DocumentoComercial comprobante1=new DocumentoComercial(25,'a');
		//Medio de pago
		MedioDePago tarjeta=new MedioDePago();
		//organizacion
		Organizacion organizacion= new Organizacion();
		//proveedores
		Proveedor proveedor1= new Proveedor();
		//fecha operacion
		LocalDate fechaOp=LocalDate.now();
		//productos
		Producto producto1 = new Producto("heladera",2000);
		Producto producto2 = new Producto("televisor",300);
		List<Producto> listaProducto = Arrays.asList(producto1,producto2);
		//usuarios
		ArrayList<Usuario> listaUsuariosRevisores = new ArrayList<Usuario>();
		Usuario usuario1 = new Usuario();
		Usuario usuario2 = new Usuario();
		Usuario usuario3 = new Usuario();
		
		listaUsuariosRevisores.add(usuario1);
		listaUsuariosRevisores.add(usuario2);
		listaUsuariosRevisores.add(usuario3);
		//criterio
		ProveedorMenorValor criterio =  ProveedorMenorValor.getInstance();
		//compra
		Compra compra1=new Compra(null, listaProducto, null,null, 0,listaUsuariosRevisores, criterio);
		//operacion egreso
		OperacionEgreso egreso1= new OperacionEgreso(comprobante1, fechaOp, tarjeta, organizacion, compra1, proveedor1);
		//TESTS
		assertTrue(egreso1.getComprobante()==comprobante1);
        assertTrue(egreso1.getFechaOperacion()==fechaOp);
        assertTrue(egreso1.getMedioDePago()==tarjeta);
        assertTrue(egreso1.getOrganizacion()==organizacion);
        assertTrue(egreso1.getProveedor()==proveedor1);
        assertTrue(egreso1.getValorDeEgreso()== 2300);
	}
}
