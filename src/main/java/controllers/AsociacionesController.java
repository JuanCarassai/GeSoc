package controllers;

import static app.RequestUtil.getQueryOpcionesAsociacion;
import static app.RequestUtil.getQueryRegla1Mix;
import static app.RequestUtil.getQueryRegla2Mix;
import static app.RequestUtil.getQueryRegla3Mix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Path;
import app.ViewUtil;
import egresosIngresos.OrdenFecha;
import egresosIngresos.OrdenMix;
import egresosIngresos.OrdenValorPrimeroEgreso;
import egresosIngresos.OrdenValorPrimeroIngreso;
import egresosIngresos.ReglaFecha;
import egresosIngresos.ReglaVinculacion;
import egresosIngresos.Requerimiento;
import spark.Request;
import spark.Response;
import spark.Route;

public class AsociacionesController {
	public static Route handleAsociaciones = (Request request, Response response) -> {
		Map<String, Object> model = new HashMap<>();
		LoginController.ensureUserIsLoggedIn(request, response);
		if (getQueryOpcionesAsociacion(request) != null) {

			if (getQueryOpcionesAsociacion(request).equals("OrdenEgreso")) {
				ReglaVinculacion regla = ReglaFecha.getInstance();
				OrdenValorPrimeroEgreso.getInstance().vincular(regla);
				model.put("vinculacionCompletada", true);

			} else if (getQueryOpcionesAsociacion(request).equals("OrdenIngreso")) {

				ReglaVinculacion regla = ReglaFecha.getInstance();
				OrdenValorPrimeroIngreso.getInstance().vincular(regla);
				model.put("vinculacionCompletada", true);

			} else if (getQueryOpcionesAsociacion(request).equals("OrdenFecha")) {

				ReglaVinculacion regla = ReglaFecha.getInstance();
				OrdenFecha.getInstance().vincular(regla);
				model.put("vinculacionCompletada", true);
			} else if (getQueryOpcionesAsociacion(request).equals("OrdenMix")) {
				model.put("SolicitarRequerimientosMix", true);
			}

		} else if (getQueryRegla1Mix(request) != null && getQueryRegla2Mix(request) != null
				&& getQueryRegla3Mix(request) != null) {
			List<Requerimiento> requerimientos = new ArrayList<Requerimiento>();

			if (getQueryRegla1Mix(request).equals("OrdenEgreso")) {
				requerimientos.add(OrdenValorPrimeroEgreso.getInstance());
			} else if (getQueryRegla1Mix(request).equals("OrdenIngreso")) {
				requerimientos.add(OrdenValorPrimeroIngreso.getInstance());
			} else if (getQueryRegla1Mix(request).equals("OrdenFecha")) {
				requerimientos.add(OrdenFecha.getInstance());
			}

			if (getQueryRegla2Mix(request).equals("OrdenEgreso")) {
				requerimientos.add(OrdenValorPrimeroEgreso.getInstance());
			} else if (getQueryRegla2Mix(request).equals("OrdenIngreso")) {
				requerimientos.add(OrdenValorPrimeroIngreso.getInstance());
			} else if (getQueryRegla2Mix(request).equals("OrdenFecha")) {
				requerimientos.add(OrdenFecha.getInstance());
			}

			if (getQueryRegla3Mix(request).equals("OrdenEgreso")) {
				requerimientos.add(OrdenValorPrimeroEgreso.getInstance());
			} else if (getQueryRegla3Mix(request).equals("OrdenIngreso")) {
				requerimientos.add(OrdenValorPrimeroIngreso.getInstance());
			} else if (getQueryRegla3Mix(request).equals("OrdenFecha")) {
				requerimientos.add(OrdenFecha.getInstance());
			}

			OrdenMix criterio = new OrdenMix();
			criterio.setRequerimientos(requerimientos);
			ReglaVinculacion regla = ReglaFecha.getInstance();
			criterio.vincular(regla);
			model.put("vinculacionCompletada", true);
			model.put("SolicitarRequerimientosMix", false);
		}
		
		
		return ViewUtil.render(request, model, Path.Template.ASOCIACIONES);
	};
}
