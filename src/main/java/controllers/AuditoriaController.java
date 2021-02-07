package controllers;

import static app.JsonUtil.dataToJson;
import static app.RequestUtil.clientAcceptsHtml;
import static app.RequestUtil.clientAcceptsJson;
import static app.RequestUtil.getQueryOperaciones;
import static app.RequestUtil.getQueryTipoOperacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;

import app.Path;
import app.ViewUtil;
import logueo.MapperMongoDB;
import logueo.TipoOperacion;
import spark.Request;
import spark.Response;
import spark.Route;

public class AuditoriaController {
	public static Route LogsView = (Request request, Response response) -> {
		LoginController.ensureUserIsLoggedIn(request, response);
		if (clientAcceptsHtml(request)) {
			// obtencion, generacion del Modelo (MVC)
			HashMap<String, Object> model = new HashMap<>();
			List<Document> logsDocumentos = new ArrayList<Document>();
			if (getQueryOperaciones(request) != null && getQueryTipoOperacion(request) != null) {
				if (getQueryTipoOperacion(request).equals("ALTA"))
					logsDocumentos = MapperMongoDB.getInstance().buscarLog(TipoOperacion.ALTA,
							getQueryOperaciones(request));
				if (getQueryTipoOperacion(request).equals("BAJA"))
					logsDocumentos = MapperMongoDB.getInstance().buscarLog(TipoOperacion.BAJA,
							getQueryOperaciones(request));
				if (getQueryTipoOperacion(request).equals("MODIFICACION"))
					logsDocumentos = MapperMongoDB.getInstance().buscarLog(TipoOperacion.MODIFICACION,
							getQueryOperaciones(request));
			}
			model.put("logsDocumentos", logsDocumentos);
			model.put("fecha", "fecha");
			model.put("tipoOperacion", "tipoOperacion");
			model.put("entidadAfectada", "entidadAfectada");
			
			// actualiza la Vista (MVC) que es un HTML
			return ViewUtil.render(request, model, Path.Template.AUDITORIA);
		}

		if (clientAcceptsJson(request)) {
			// actualiza la Vista, que es un JSON
			List<Document> logsDocumentos = new ArrayList<Document>();
			if (getQueryOperaciones(request) != null && getQueryTipoOperacion(request) != null) {
				if (getQueryTipoOperacion(request).equals("ALTA"))
					logsDocumentos = MapperMongoDB.getInstance().buscarLog(TipoOperacion.ALTA,
							getQueryOperaciones(request));
				if (getQueryTipoOperacion(request).equals("BAJA"))
					logsDocumentos = MapperMongoDB.getInstance().buscarLog(TipoOperacion.BAJA,
							getQueryOperaciones(request));
				if (getQueryTipoOperacion(request).equals("MODIFICACION"))
					logsDocumentos = MapperMongoDB.getInstance().buscarLog(TipoOperacion.MODIFICACION,
							getQueryOperaciones(request));
			}
			return dataToJson(logsDocumentos);
		}
		// actualiza la Vista con un mensaje de error
		return ViewUtil.notAcceptable.handle(request, response);
	};
}
