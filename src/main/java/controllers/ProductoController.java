package controllers;

import static app.RequestUtil.getQueryCantidadCategorias;
import static app.RequestUtil.getQueryCategoriasElegidas;
import static app.RequestUtil.getQueryCriteriosSeleccionados;
import static app.RequestUtil.getQueryDescripcion;
import static app.RequestUtil.getQueryMontoTotal;
import static app.RequestUtil.getQueryNombreCategorias;
import static app.RequestUtil.getQueryNombreCriterio;
import static app.RequestUtil.getQueryNombreCriterioPadre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Path;
import app.ViewUtil;
import comprasPresupuestos.Producto;
import criteriosCategorias.Categoria;
import criteriosCategorias.CriterioCategorizacion;
import spark.Request;
import spark.Response;
import spark.Route;

public class ProductoController {
	public static Route cargarProducto = (Request request, Response response) -> {
		Map<String, Object> model = new HashMap<>();
		LoginController.ensureUserIsLoggedIn(request, response);
		List<CriterioCategorizacion> criterios = CriterioCategorizacion.obtenerTodosEnBD();
		model.put("criterios", criterios);
		
		if(getQueryDescripcion(request)!=null && getQueryMontoTotal(request)!=null) {
			Producto productoNuevo = new Producto();
			productoNuevo.setDescripcion(getQueryDescripcion(request).trim());
			
			Float montoTotal;
			try {
				montoTotal = Float.parseFloat(getQueryMontoTotal(request).trim());
			} catch (NumberFormatException e1) {
				model.put("FormatoNumeroIncorrecto", true);
				return ViewUtil.render(request, model, Path.Template.PRODUCTOS);
			}
			if (montoTotal <= 0) {
				model.put("montoMenorACero", true);
				return ViewUtil.render(request, model, Path.Template.PRODUCTOS);
			}
			
			productoNuevo.setValor(montoTotal);
		
			if(getQueryCriteriosSeleccionados(request)!=null) {
				int index = 0;
				int l = getQueryCriteriosSeleccionados(request).length;
				while(index<l) {
					System.out.println(getQueryCriteriosSeleccionados(request)[index]);
					index++;
				}
				
			}
			
			
			if(getQueryCriteriosSeleccionados(request)!=null && getQueryCategoriasElegidas(request,criterios)!=null) {
				int index = 0;
				int l = getQueryCriteriosSeleccionados(request).length;
				Map<String,String> mapaCriterioCategoriaElegidos = new HashMap<>();
				while(index<l) {
					String nombreCriterioI = getQueryCriteriosSeleccionados(request)[index];
					String nombreCategoriaI = getQueryCategoriasElegidas(request,criterios).get(nombreCriterioI);
					if(nombreCategoriaI != null)
						mapaCriterioCategoriaElegidos.put(nombreCriterioI, nombreCategoriaI);
					else {
						model.put("errorEleccionCategorias", true);
						return ViewUtil.render(request, model, Path.Template.PRODUCTOS);
					}
					index++;
				}
				List<Categoria> categoriasElegidas = buscarCategorias(criterios,mapaCriterioCategoriaElegidos);
				
				productoNuevo.setCategorias(categoriasElegidas);
				
				Producto.insertarNuevoProductoEnBD(productoNuevo);
				model.put("cargaCorrecta",true);
				model.put("idProducto", productoNuevo.getId());
				
				
			}

			
		}
		
		
		return ViewUtil.render(request, model, Path.Template.PRODUCTOS);
	};

	public static Route cargarCategoria = (Request request, Response response) -> {
		Map<String, Object> model = new HashMap<>();
		LoginController.ensureUserIsLoggedIn(request, response);

		if (getQueryCantidadCategorias(request) != null && getQueryNombreCriterio(request) != null) {

			if (getQueryNombreCriterio(request).equals("")) {
				model.put("nombreCriterioVacio", true);
				return ViewUtil.render(request, model, Path.Template.CATEGORIA);
			}

			CriterioCategorizacion criterioEncontrado = CriterioCategorizacion
					.buscarCriterioPorNombreEnBD(getQueryNombreCriterio(request).trim());
			if (criterioEncontrado != null) {
				model.put("nombreCriterioYaExiste", true);
				return ViewUtil.render(request, model, Path.Template.CATEGORIA);
			}

			CriterioCategorizacion criterioPadreEncontrado = null;
			if (!getQueryNombreCriterioPadre(request).equals("")) {
				criterioPadreEncontrado = CriterioCategorizacion
						.buscarCriterioPorNombreEnBD(getQueryNombreCriterioPadre(request).trim());
				if (criterioPadreEncontrado == null) {
					model.put("nombreCriterioPadreNoExiste", true);
					return ViewUtil.render(request, model, Path.Template.CATEGORIA);
				}
			}
			CriterioCategorizacion criterioNuevo = new CriterioCategorizacion();
			criterioNuevo = new CriterioCategorizacion();

			criterioNuevo.setCriterioPadre(criterioPadreEncontrado);
			criterioNuevo.setNombre(getQueryNombreCriterio(request).trim());
			int cantidadCategorias = Integer.parseInt(getQueryCantidadCategorias(request));
			int index = 0;
			while (index < cantidadCategorias) {
				Categoria categoriaNueva = new Categoria();
				categoriaNueva.setCriterio(criterioNuevo);
				criterioNuevo.getCategorias().add(categoriaNueva);
				index++;
			}

			CriterioCategorizacion.insertarNuevoCriterioEnBD(criterioNuevo);

			if (criterioPadreEncontrado != null) {
				criterioPadreEncontrado.getCriterioHijo().add(criterioNuevo);
				CriterioCategorizacion.actualizarCriterioEnBD(criterioPadreEncontrado);
			}
			model.put("criterio", criterioNuevo);
			model.put("categorias", criterioNuevo.getCategorias());
		} else {
			if (getQueryNombreCategorias(request) != null) {
				CriterioCategorizacion cr = CriterioCategorizacion
						.buscarCriterioPorNombreEnBD(getQueryNombreCategorias(request)[0]);
				int index = 1;
				int length = getQueryNombreCategorias(request).length;
				while (index < length) {
					cr.getCategorias().get(index-1).setNombre(getQueryNombreCategorias(request)[index]);
					index++;
				}
				Categoria.actualizarCategorias(cr.getCategorias());
				model.put("criterio", null);
				model.put("criterioCargado", true);

			}

		}

		return ViewUtil.render(request, model, Path.Template.CATEGORIA);

	};

	private static List<Categoria> buscarCategorias(List<CriterioCategorizacion> criterios,
			Map<String, String> mapaCriterioCategoriaElegidos) {
		List<Categoria> categorias = new ArrayList<Categoria>();
		int index = 0;
		int size = criterios.size();
		
		while(index<size) {
			String nombreCategoria = mapaCriterioCategoriaElegidos.get(criterios.get(index).getNombre());
			if(nombreCategoria !=null)
			{
				int indexC = 0;
				int sizeC = criterios.get(index).getCategorias().size();
				while(indexC<sizeC) {
					if(criterios.get(index).getCategorias().get(indexC).getNombre().equals(nombreCategoria)) {
						categorias.add(criterios.get(index).getCategorias().get(indexC));
					}
					indexC++;
				}
			}
			
			index++;
		}
		
		return categorias;
	}


}
