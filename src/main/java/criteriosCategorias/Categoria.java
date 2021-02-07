package criteriosCategorias;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import persistencia.CategoriaMapper;


@Entity
//@Table(name = "USUARIO")

public class Categoria {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String nombre;
	
	@ManyToOne
	private CriterioCategorizacion criterio;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public CriterioCategorizacion getCriterio() {
		return criterio;
	}
	public void setCriterio(CriterioCategorizacion criterioCategorizacion) {
		this.criterio = criterioCategorizacion;
	}
	public static void actualizarCategorias(List<Categoria> categorias) {
		CategoriaMapper.getInstance().updateAll(categorias);
	}

}