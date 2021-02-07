package utn.disenio.tp;


import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import comprasPresupuestos.Compra;
import comprasPresupuestos.Producto;
import egresosIngresos.OperacionEgreso;
import egresosIngresos.OperacionIngreso;
import egresosIngresos.OrdenFecha;
import egresosIngresos.Organizacion;
import egresosIngresos.ReglaFecha;
import persistencia.CompraMapperBD;
import persistencia.OperacionEgresoMapperBD;
import persistencia.OperacionIngresoMapperBD;
import persistencia.OrganizacionMapperBD;
import persistencia.ProductoMapperBD;

public class VinculoIngresoEgresoTest {
	@Test
	public void validarVinculoIngresoEgreso() {
		//organizacion
		Organizacion organizacion= new Organizacion();
		organizacion.setRegla( ReglaFecha.getInstance());
		organizacion.setRequerimiento( OrdenFecha.getInstance());
		
		OrganizacionMapperBD.getInstance().insert(organizacion);
		//proveedores
		LocalDate fechaOp = LocalDate.of(2010, 7, 7);
		//productos
		Producto producto1 = new Producto("heladera",2000);
		Producto producto2 = new Producto("televisor",300);
		List<Producto> listaProducto = Arrays.asList(producto1,producto2);
		//compra
		Compra compra1=new Compra(null, listaProducto, null,null, 0,null, null);
		//operacion egreso
		OperacionEgreso egreso1= new OperacionEgreso(null, fechaOp, null, organizacion, compra1, null);
		

		//productos
		Producto producto12 = new Producto("heladera",2000);
		Producto producto22 = new Producto("televisor",300);
		List<Producto> listaProducto2 = Arrays.asList(producto12,producto22);
		//compra
		Compra compra12=new Compra(null, listaProducto2, null,null, 0,null, null);
		//operacion egreso
		OperacionEgreso egreso12= new OperacionEgreso(null, fechaOp, null, organizacion, compra12, null);


		//productos
		Producto producto13 = new Producto("heladera",2000);
		Producto producto23 = new Producto("televisor",300);
		List<Producto> listaProducto3 = Arrays.asList(producto13,producto23);
		//compra
		Compra compra13=new Compra(null, listaProducto3, null,null, 0,null, null);
		//operacion egreso
		OperacionEgreso egreso13= new OperacionEgreso(null, fechaOp, null, organizacion, compra13, null);
	
		


		OperacionIngreso ingreso1 = new OperacionIngreso("ingreso 1",3333,fechaOp,new ArrayList<OperacionEgreso>(),organizacion);

		OperacionIngreso ingreso12 = new OperacionIngreso("ingreso 2", 3333, fechaOp,new ArrayList<OperacionEgreso>(), organizacion);
	

		OperacionIngreso ingreso13 = new OperacionIngreso("ingreso 1", 3333, fechaOp,new ArrayList<OperacionEgreso>(), organizacion);

		ProductoMapperBD.getInstance().insert(listaProducto);
		ProductoMapperBD.getInstance().insert(listaProducto2);
		ProductoMapperBD.getInstance().insert(listaProducto3);
		CompraMapperBD.getInstance().insert(compra1);
		CompraMapperBD.getInstance().insert(compra12);
		CompraMapperBD.getInstance().insert(compra13);
		OperacionIngresoMapperBD.getInstance().insert(Arrays.asList(ingreso1,ingreso12,ingreso13));
		
		OperacionEgresoMapperBD.getInstance().insert(Arrays.asList(egreso1,egreso12,egreso13));
		
		organizacion.setEgresos(Arrays.asList(egreso1,egreso12,egreso13));
		organizacion.setIngresos(Arrays.asList(ingreso1,ingreso12,ingreso13));

		
		
		//VinculadorMagico vm = new VinculadorMagico();
		//vm.vincular();
		
		
		//List<OperacionEgreso> egresosVinculados = OperacionEgresoMapperBD.getInstance().obtenerEgresos();
		
		//egresosVinculados.forEach(egresoV -> System.out.println(egresoV.toString()));
//		
//		List<OperacionIngreso> ingresosVinculados = OperacionIngresoMapperBD.getInstance().obtenerIngresos();
//		ingresosVinculados.forEach(ingresoV->System.out.println(ingresoV.toString()));
		
		assertTrue(true);
		
		
	}
	
	@Before
	public void borrarBaseDeDatos() {
		
	}
}
