package persistencia;

import criteriosCategorias.Categoria;

public class CategoriaMapper extends MapperBD<Categoria>{
	private static final CategoriaMapper instance = new CategoriaMapper();
	
	private CategoriaMapper () {}
	public static CategoriaMapper getInstance() {
		return instance;
	}
}
