package controllers;

import static app.RequestUtil.getQueryCantidadPresupuestos;
import static app.RequestUtil.getQueryMontoLimiteSinPresupuesto;
import static app.RequestUtil.getQueryMontoTotal;
import static app.RequestUtil.getQueryNumeroOperacion;
import static app.RequestUtil.getQueryNumeroProyecto;
import static app.RequestUtil.getQueryOpcionOperacion;
import static app.RequestUtil.getQueryUsername;

import java.util.HashMap;
import java.util.Map;

import RendicionDeCuentas.ProyectoDeFinanciacion;
import app.Path;
import app.ViewUtil;
import comprasPresupuestos.Presupuesto;
import egresosIngresos.OperacionEgreso;
import egresosIngresos.OperacionIngreso;
import spark.Request;
import spark.Response;
import spark.Route;
import validadorDeCompras.Usuario;

public class ProyectoController {
	public static Route IndexProyecto = (Request request, Response response) -> {
		Map<String, Object> model = new HashMap<>();
		LoginController.ensureUserIsLoggedIn(request, response);
		return ViewUtil.render(request, model, Path.Template.PROYECTO);
	};
	public static Route cargarProyecto = (Request request, Response response) -> {
		Map<String, Object> model = new HashMap<>();
		LoginController.ensureUserIsLoggedIn(request, response);
		ProyectoDeFinanciacion proyectoNuevo = new ProyectoDeFinanciacion();
		if (getQueryCantidadPresupuestos(request) != null && getQueryMontoLimiteSinPresupuesto(request) != null
				&& getQueryMontoTotal(request) != null && getQueryUsername(request) != null) {
			Float montoTotal;
			try {
				montoTotal = Float.parseFloat(getQueryMontoTotal(request).trim());
			} catch (NumberFormatException e1) {
				model.put("FormatoNumeroIncorrecto", true);
				return ViewUtil.render(request, model, Path.Template.CARGAR_PROYECTO);
			}
			if (montoTotal <= 0) {
				model.put("montoMenorACero", true);
				return ViewUtil.render(request, model, Path.Template.CARGAR_PROYECTO);
			}

			proyectoNuevo.setMontoTotalAsignado(montoTotal);

			Float montoLimite;
			try {
				montoLimite = Float.parseFloat(getQueryMontoLimiteSinPresupuesto(request).trim());
			} catch (NumberFormatException e1) {
				model.put("FormatoMontoLimiteIncorrecto", true);
				return ViewUtil.render(request, model, Path.Template.CARGAR_PROYECTO);
			}
			if (montoLimite < 0) {
				model.put("montoLimiteMenorACero", true);
				return ViewUtil.render(request, model, Path.Template.CARGAR_PROYECTO);
			}

			if (montoLimite > montoTotal) {
				model.put("montoLimiteMayorAlTotal", true);
				return ViewUtil.render(request, model, Path.Template.CARGAR_PROYECTO);
			}

			proyectoNuevo.setMontoLimiteSinPresupuesto(montoLimite);

			Integer cantidadPresupuestos;
			try {
				cantidadPresupuestos = Integer.parseInt(getQueryCantidadPresupuestos(request).trim());
			} catch (NumberFormatException e1) {
				model.put("FormatoCantidadIncorrecto", true);
				return ViewUtil.render(request, model, Path.Template.CARGAR_PROYECTO);
			}
			if (cantidadPresupuestos < 0) {
				model.put("cantidadMenorACero", true);
				return ViewUtil.render(request, model, Path.Template.CARGAR_PROYECTO);
			}

			proyectoNuevo.setCantPresupuestosMinima(cantidadPresupuestos);

			Usuario usuarioEncontrado = Usuario.buscarUsuarioBD(getQueryUsername(request));

			if (usuarioEncontrado != null)
				proyectoNuevo.setDirectorResponsable(usuarioEncontrado);
			else {
				model.put("usuarioNoExiste", true);
				return ViewUtil.render(request, model, Path.Template.CARGAR_PROYECTO);
			}

			ProyectoDeFinanciacion.insertarNuevoProyectoEnBD(proyectoNuevo);
			model.put("proyectoCargadoCorrectamente", true);
			model.put("idProyectoNuevo", proyectoNuevo.getId());
		}

		return ViewUtil.render(request, model, Path.Template.CARGAR_PROYECTO);
	};
	public static Route vincularProyecto = (Request request, Response response) -> {
		Map<String, Object> model = new HashMap<>();
		LoginController.ensureUserIsLoggedIn(request, response);
		if (getQueryNumeroProyecto(request) != null && getQueryOpcionOperacion(request) != null
				&& getQueryNumeroOperacion(request) != null) {
			Long identificadorPresupuesto;
			try {
				identificadorPresupuesto = Long.parseLong(getQueryNumeroProyecto(request).trim());
			} catch (NumberFormatException e1) {
				model.put("FormatoNumeroIncorrecto", true);
				return ViewUtil.render(request, model, Path.Template.VINCULAR_PROYECTO);
			}
			ProyectoDeFinanciacion proyectoEncontrado = ProyectoDeFinanciacion
					.buscarProyectoPorIdentificadorEnBD(identificadorPresupuesto);

			if (proyectoEncontrado == null) {
				model.put("proyectoNoEncontrado", true);
				return ViewUtil.render(request, model, Path.Template.VINCULAR_PROYECTO);
			}

			Long identificadorOperacion;
			try {
				identificadorOperacion = Long.parseLong(getQueryNumeroOperacion(request).trim());
			} catch (NumberFormatException e1) {
				model.put("FormatoNumeroOperacionIncorrecto", true);
				return ViewUtil.render(request, model, Path.Template.VINCULAR_PROYECTO);
			}

			if (getQueryOpcionOperacion(request).equals("ingreso")) {
				OperacionIngreso operacionEncontrada = OperacionIngreso.buscarIngresoPorIdEnBD(identificadorOperacion);
				if (operacionEncontrada != null) {
					proyectoEncontrado.getIngresos().add(operacionEncontrada);
					ProyectoDeFinanciacion.ActualizarProyecto(proyectoEncontrado);
					model.put("vinculacionExitosaIngreso", true);

				} else {
					model.put("ingresoNoEncontrado", true);
					return ViewUtil.render(request, model, Path.Template.VINCULAR_PROYECTO);
				}
			} else if (getQueryOpcionOperacion(request).equals("egreso")) {
				OperacionEgreso operacionEncontrada = OperacionEgreso.buscarEgresoPorIdEnBD(identificadorOperacion);
				if (operacionEncontrada != null) {
					proyectoEncontrado.getEgresos().add(operacionEncontrada);
					model.put("vinculacionExitosaEgreso", true);
					ProyectoDeFinanciacion.ActualizarProyecto(proyectoEncontrado);
				} else {
					model.put("egresoNoEncontrado", true);
					return ViewUtil.render(request, model, Path.Template.VINCULAR_PROYECTO);
				}
			} else if (getQueryOpcionOperacion(request).equals("presupuesto")) {
				Presupuesto operacionEncontrada = Presupuesto.buscarPresupuestoPorIdEnBD(identificadorOperacion);
				if (operacionEncontrada != null) {
					proyectoEncontrado.getPresupuestos().add(operacionEncontrada);
					model.put("vinculacionExitosaPresupuesto", true);
					ProyectoDeFinanciacion.ActualizarProyecto(proyectoEncontrado);
				} else {
					model.put("presupuestoNoEncontrado", true);
					return ViewUtil.render(request, model, Path.Template.VINCULAR_PROYECTO);
				}
			}

		}

		return ViewUtil.render(request, model, Path.Template.VINCULAR_PROYECTO);
	};
}
