package criteriosCategorias;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import persistencia.CriterioCategorizacionMapper;


@Entity

public class CriterioCategorizacion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String nombre;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<Categoria> categorias = new ArrayList<Categoria>();
	
	@ManyToOne
	private CriterioCategorizacion criterioPadre;
	
	@OneToMany
	private List<CriterioCategorizacion> criterioHijo = new ArrayList<CriterioCategorizacion>();
	
	
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

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void setCriterioHijo(List<CriterioCategorizacion> criterioHijo) {
		this.criterioHijo = criterioHijo;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}

	public CriterioCategorizacion getCriterioPadre() {
		return criterioPadre;
	}

	public void setCriterioPadre(CriterioCategorizacion criterioPadre) {
		this.criterioPadre = criterioPadre;
	}

	public List<CriterioCategorizacion> getCriterioHijo() {
		return criterioHijo;
	}

	public void setCriterioHijo(ArrayList<CriterioCategorizacion> criterioHijo) {
		this.criterioHijo = criterioHijo;
	}


	
	public void agregarCategoria(Categoria categoria) {
		categorias.add(categoria);
		categoria.setCriterio(this);
	}

	public static CriterioCategorizacion buscarCriterioPorNombreEnBD(String nombre) {
		return CriterioCategorizacionMapper.getInstance().buscarCriterioPorNombre(nombre);
	}

	public static void insertarNuevoCriterioEnBD(CriterioCategorizacion criterioNuevo) {
		CriterioCategorizacionMapper.getInstance().insert(criterioNuevo);
		
	}

	public static void actualizarCriterioEnBD(CriterioCategorizacion criterioPadreEncontrado) {
		CriterioCategorizacionMapper.getInstance().update(criterioPadreEncontrado);
		
	}

	public static List<CriterioCategorizacion> obtenerTodosEnBD() {
		return CriterioCategorizacionMapper.getInstance().obtenerTodos();
	}
}
