package comprasPresupuestos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import criteriosCategorias.Categoria;
import criteriosCategorias.CriterioCategorizacion;
import persistencia.ProductoMapperBD;

@Entity
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	private float valor;
	@ManyToMany
	private List<Categoria> categorias = new ArrayList<Categoria>();
	public Producto() {}
	public Producto(String descripcion, float valor) {
		super();
		this.descripcion = descripcion;
		this.valor = valor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valorIngresado) {
		this.valor = valorIngresado;
	}
		
	public Long getId() {
		return id;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	private boolean buscarCriterio(CriterioCategorizacion criterio) {
		boolean encontrado = false;
			for(int i=0; i<categorias.size();i++) {
				Categoria cat = categorias.get(i);
				if(criterio == cat.getCriterio()) {
					encontrado = true;
					break;
				}
			}
	return encontrado;
	}
	
	public void agregarCategoria(Categoria categoria) {
		//Puede estar asociado a sólo una categoría de cada criterio
		if(categorias.isEmpty() || !this.buscarCriterio(categoria.getCriterio()))
		categorias.add(categoria);
	}
	public static List<Producto> obtenerTodosEnBD() {
		return ProductoMapperBD.getInstance().obtenerTodos();
	}
	public static void insertarNuevoProductoEnBD(Producto productoNuevo) {
		ProductoMapperBD.getInstance().insert(productoNuevo);
		
	}
}
