package utn.disenio.tp;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import comprasPresupuestos.Presupuesto;
import comprasPresupuestos.PresupuestoDetallado;
import comprasPresupuestos.Producto;
import egresosIngresos.DocumentoComercial;
import egresosIngresos.Proveedor;

public class PresupuestoTest {

	@Test
	public void validarMethodsPresupuesto() {
		Producto producto1 = new Producto("heladera",2000);
		Producto producto2 = new Producto("televisor",300);
		Producto producto3 = new Producto("televisor",350);
		
		Proveedor proveedor1 = new Proveedor();
		Proveedor proveedor2 = new Proveedor();
		
		ArrayList<DocumentoComercial> documentosComerciales = new ArrayList<DocumentoComercial>();
		DocumentoComercial doc1= new DocumentoComercial(20,'a');
		DocumentoComercial doc2= new DocumentoComercial(30,'b');
		DocumentoComercial doc3= new DocumentoComercial(35,'c');
		
		documentosComerciales.add(doc1);
		documentosComerciales.add(doc2);
		documentosComerciales.add(doc3);
		
		ArrayList<PresupuestoDetallado> detalles = new ArrayList<PresupuestoDetallado>();
		PresupuestoDetallado pd1 = new PresupuestoDetallado(0,null);
		PresupuestoDetallado pd2 = new PresupuestoDetallado(0,null);
		PresupuestoDetallado pd3 = new PresupuestoDetallado(0,null);
		
		pd1.setPrecio(5000);
		pd1.setProductoCompra(producto1);
		pd2.setPrecio(300);
		pd2.setProductoCompra(producto2);
		pd3.setPrecio(100);
		pd3.setProductoCompra(producto3);
		
		detalles.add(pd1);
		detalles.add(pd2);
		detalles.add(pd3);
		
		Presupuesto presupuesto = new Presupuesto(null,null,null);
		presupuesto.setProveedor(proveedor1);
		presupuesto.setDocumentosComerciales(documentosComerciales);
		presupuesto.setDetalles(detalles);
		
		assertTrue(presupuesto.getProveedor() == proveedor1);
		assertTrue(presupuesto.getProveedor() != proveedor2);
		assertTrue(presupuesto.getValorTotal() == 5400);
	}

}
