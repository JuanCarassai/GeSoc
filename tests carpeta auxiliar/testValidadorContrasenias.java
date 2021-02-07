package utn.disenio.tp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import validadorContrasenias.ValidadorContrasenias;


public class testValidadorContrasenias {
	@Test
	public void validarContrasenias() {

		assertTrue(!ValidadorContrasenias.getInstance().validar("juan"));
		assertTrue(ValidadorContrasenias.getInstance().validar("dgdvcdgrgcvgdr"));
		assertTrue(ValidadorContrasenias.getInstance().validar("platense10"));
		
	}
}
