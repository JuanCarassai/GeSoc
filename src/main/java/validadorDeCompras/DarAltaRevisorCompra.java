package validadorDeCompras;

import comprasPresupuestos.Compra;

public class DarAltaRevisorCompra {
	
private static DarAltaRevisorCompra instance = null;
	
	private DarAltaRevisorCompra() {}
	
	public static DarAltaRevisorCompra getInstance()
	{
	if (instance == null)
	instance = new DarAltaRevisorCompra();
	return instance;
	}
	
	void darAlta(Usuario usuario,Compra compra) {
		compra.agregarRevisor(usuario);
	}
}
