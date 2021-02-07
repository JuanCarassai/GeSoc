package utn.disenio.tp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import criteriosCategorias.CriterioCategorizacion;
import validadorDeCompras.Administrador;

public class AdministradorTest {

	@Test
	public void otorgarJerarquiaTest() {
		Administrador admin= new Administrador();
		CriterioCategorizacion padre=new CriterioCategorizacion();
		CriterioCategorizacion hijo=new CriterioCategorizacion();
		admin.otorgarJerarquia(padre, hijo);
		assertEquals(padre.getCriterioHijo().get(0),hijo);
		assertEquals(hijo.getCriterioPadre(),padre);
		
		
	}

}
