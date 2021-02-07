package persistencia;

import validadorDeCompras.Mensaje;

public class MensajeMapper extends MapperBD<Mensaje> {
	private static final MensajeMapper instance = new MensajeMapper();
	
		private MensajeMapper () {}
		public static MensajeMapper getInstance() {
			return instance;
	}
}
