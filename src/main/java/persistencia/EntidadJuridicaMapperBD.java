package persistencia;

import entidades.EntidadJuridica;

public class EntidadJuridicaMapperBD extends MapperBD<EntidadJuridica>{
	private static final EntidadJuridicaMapperBD instance = new EntidadJuridicaMapperBD();

	private EntidadJuridicaMapperBD () {}
	public static EntidadJuridicaMapperBD getInstance() {
		return instance;
	}
}