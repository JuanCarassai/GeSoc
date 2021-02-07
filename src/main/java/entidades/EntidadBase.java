package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import persistencia.EntidadBaseMapperBD;
@Entity
public class EntidadBase extends Entidad {

	

	
	@ManyToOne
	private EntidadJuridica entidad_juridica;
	private String descripcion;

	public EntidadBase() {
	}

	public EntidadJuridica getEntidad_juridica() {
		return entidad_juridica;
	}

	public void setEntidad_juridica(EntidadJuridica entidad_juridica) {
		this.entidad_juridica = entidad_juridica;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static List<EntidadBase> obtenerTodosSinAsignar() {
		return EntidadBaseMapperBD.getInstance().obtenerTodosSinAsignar();
	}

	public static void insertarNuevaBase(EntidadBase entidad) {
		EntidadBaseMapperBD.getInstance().insert(entidad);
	}

	public static List<EntidadBase> obtenerMisEntidades(String[] entidadBaseSelec,
			List<EntidadBase> entidadesBaseLista) {
		List<EntidadBase> entidadBaseReturn = new ArrayList<EntidadBase>();
		int tam = entidadBaseSelec.length;
		for (int i = 0; i < tam; i++) {
			Long id = Long.parseLong(entidadBaseSelec[i]);
			entidadBaseReturn
					.add(entidadesBaseLista.stream()
							.filter(entidad -> entidad.getIdEntidad() == id).findFirst().get());
		}
		return entidadBaseReturn;
	}
	
	public static void asignarEntidadJuridica(List<EntidadBase> entidadesBase, EntidadJuridica entidadJur) {
		int tam = entidadesBase.size();
		for(int i=0;i<tam;i++) {
			entidadesBase.get(i).setEntidad_juridica(entidadJur);
			}
		EntidadBaseMapperBD.getInstance().updateAll(entidadesBase);
	}

}
