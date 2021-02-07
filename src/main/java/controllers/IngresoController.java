package controllers;

import static app.JsonUtil.dataToJson;
import static app.RequestUtil.clientAcceptsHtml;
import static app.RequestUtil.clientAcceptsJson;
import static app.RequestUtil.getQueryDescripcion;
import static app.RequestUtil.getQueryFecha;
import static app.RequestUtil.getQueryMontoTotal;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Path;
import app.ViewUtil;
import egresosIngresos.OperacionIngreso;
import spark.Request;
import spark.Response;
import spark.Route;


public class IngresoController {
	public static Route cargarIngreso = (Request request, Response response) -> {
		Map<String, Object> model = new HashMap<>();
		LoginController.ensureUserIsLoggedIn(request, response);
		String descripcion = "";
		float montoTotal = 0;
		LocalDate fechaOperacion = null;
		try {
			if (!getQueryFecha(request).equals("") && !getQueryMontoTotal(request).equals("")
					&& !getQueryDescripcion(request).equals("")) {
				descripcion = getQueryDescripcion(request).trim();
				try {
					montoTotal = Float.parseFloat(getQueryMontoTotal(request).trim());
				} catch (NumberFormatException e1) {
					model.put("FormatoNumeroIncorrecto", true);
					return ViewUtil.render(request, model, Path.Template.INGRESOS);
				}
				if (montoTotal <= 0) {
					model.put("montoMenorACero", true);
					return ViewUtil.render(request, model, Path.Template.INGRESOS);
				}
				String[] fecha = getQueryFecha(request).split("/");
				try {
					fechaOperacion = LocalDate.of(Integer.parseInt(fecha[2]), Integer.parseInt(fecha[1]),
							Integer.parseInt(fecha[0]));
				} catch (java.time.DateTimeException e2) {
					model.put("errorFecha", true);
					return ViewUtil.render(request, model, Path.Template.INGRESOS);
				}
				OperacionIngreso ingreso = new OperacionIngreso(descripcion, montoTotal, fechaOperacion, null, null);
				OperacionIngreso.insertarEnBD(ingreso);
				model.put("cargaIngresoExitosa", true);
				model.put("numeroIngreso", ingreso.getId());
				
			} else {
				model.put("OperacionIncompleta", true);
				return ViewUtil.render(request, model, Path.Template.INGRESOS);
			}
		} catch (NullPointerException e) {

		}

		return ViewUtil.render(request, model, Path.Template.INGRESOS);
	};

	public static Route mis_ingresos = (Request request, Response response) -> {
		LoginController.ensureUserIsLoggedIn(request, response);
		if (clientAcceptsHtml(request)) {
			// obtencion, generacion del Modelo (MVC)
			HashMap<String, Object> model = new HashMap<>();
			List<OperacionIngreso> ingresos = OperacionIngreso.buscarIngresos();
			DecimalFormat formatoPrecio = new DecimalFormat("#.##");
			model.put("formato", formatoPrecio);
			model.put("ingresos", ingresos);
			// actualiza la Vista (MVC) que es un HTML
			return ViewUtil.render(request, model, Path.Template.MIS_INGRESOS);
		}

		if (clientAcceptsJson(request)) {
			// actualiza la Vista, que es un JSON
			
			return dataToJson(OperacionIngreso.buscarIngresos());
		}
		// actualiza la Vista con un mensaje de error
		return ViewUtil.notAcceptable.handle(request, response);
	};
}
