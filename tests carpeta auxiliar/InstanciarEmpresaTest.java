package utn.disenio.tp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import entidades.Empresa;
import entidades.InstanciarEmpresa;
import entidades.MedianaTramo1;
import entidades.MedianaTramo2;
import entidades.Micro;
import entidades.Pequenia;

public class InstanciarEmpresaTest {

	@Test
	public void microEmpresaXVentasAnualestest() {
		Empresa empresa = new Empresa();
		 empresa.setActividad("AGROPECUARIO");
		 empresa.setPersonal(707770);
		 empresa.setVtasAnuales(11750000);
		 assertTrue(InstanciarEmpresa.definirEmpresa(empresa) instanceof Micro);
	}
	
	@Test
	public void microEmpresaXPersonaltest() {
		Empresa empresa = new Empresa();
		 empresa.setActividad("AGROPECUARIO");
		 empresa.setPersonal(2);
		 empresa.setVtasAnuales(0);
		 assertTrue(InstanciarEmpresa.definirEmpresa(empresa) instanceof Micro);
	}
	@Test
	public void EmpresaPequeniaXVentasAnualestest() {
		Empresa empresa = new Empresa();
		 empresa.setActividad("AGROPECUARIO");
		 empresa.setPersonal(707770);
		 empresa.setVtasAnuales(20000000);
		 assertTrue(InstanciarEmpresa.definirEmpresa(empresa) instanceof Pequenia);
	}
	@Test
	public void EmpresaPequeniaXPersonaltest() {
		Empresa empresa = new Empresa();
		 empresa.setActividad("AGROPECUARIO");
		 empresa.setPersonal(7);
		 empresa.setVtasAnuales(0);
		 assertTrue(InstanciarEmpresa.definirEmpresa(empresa) instanceof Pequenia);
	}
	@Test
	public void EmpresaMedianaT1XVentasAnualestest() {
		Empresa empresa = new Empresa();
		 empresa.setActividad("AGROPECUARIO");
		 empresa.setPersonal(707770);
		 empresa.setVtasAnuales(50000000);
		 assertTrue(InstanciarEmpresa.definirEmpresa(empresa) instanceof MedianaTramo1);
	}
	@Test
	public void EmpresaMedianaT1XPersonaltest() {
		Empresa empresa = new Empresa();
		 empresa.setActividad("AGROPECUARIO");
		 empresa.setPersonal(15);
		 empresa.setVtasAnuales(0);
		 assertTrue(InstanciarEmpresa.definirEmpresa(empresa) instanceof MedianaTramo1);
	}
	@Test
	public void EmpresaMedianaT2XVentasAnualestest() {
		Empresa empresa = new Empresa();
		 empresa.setActividad("AGROPECUARIO");
		 empresa.setPersonal(707770);
		 empresa.setVtasAnuales(400000000);
		 assertTrue(InstanciarEmpresa.definirEmpresa(empresa) instanceof MedianaTramo2);
	}
	@Test
	public void EmpresaMedianaT2XPersonaltest() {
		Empresa empresa = new Empresa();
		 empresa.setActividad("AGROPECUARIO");
		 empresa.setPersonal(200);
		 empresa.setVtasAnuales(0);
		 assertTrue(InstanciarEmpresa.definirEmpresa(empresa) instanceof MedianaTramo2);
	}

}
