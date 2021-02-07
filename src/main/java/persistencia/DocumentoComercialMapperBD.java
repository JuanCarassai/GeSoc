package persistencia;

import egresosIngresos.DocumentoComercial;

public class DocumentoComercialMapperBD extends MapperBD<DocumentoComercial> {
	private static final DocumentoComercialMapperBD instance = new DocumentoComercialMapperBD();
	
	private DocumentoComercialMapperBD () {}
	public static DocumentoComercialMapperBD getInstance() {
		return instance;
	}
}
