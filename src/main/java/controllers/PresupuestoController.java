package controllers;

import static app.RequestUtil.getQueryComprobanteNumero;
import static app.RequestUtil.getQueryComprobanteTipo;
import static app.RequestUtil.getQueryDNICUITProveedor;
import static app.RequestUtil.getQueryMontoTotal;
import static app.RequestUtil.getQueryNombreProveedor;
import static app.RequestUtil.getQueryPresupuestosSeleccionados;
import static app.RequestUtil.getQueryProductoSeleccionado;
import static app.RequestUtil.getQuery_Moneda;
import static app.RequestUtil.getQuery_Pais;
import static com.API.Moneda.buscarMonedaPorID;
import static com.API.Pais.buscarPaisPorID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.API.Moneda;
import com.API.Pais;

import app.Path;
import app.ViewUtil;
import comprasPresupuestos.Presupuesto;
import comprasPresupuestos.PresupuestoDetallado;
import comprasPresupuestos.Producto;
import egresosIngresos.DocumentoComercial;
import egresosIngresos.OrganizacionProveedora;
import egresosIngresos.Persona;
import persistencia.MonedaMapperBD;
import persistencia.PaisMapperBD;
import spark.Request;
import spark.Response;
import spark.Route;

public class PresupuestoController {

	public static Route cargarPresupuesto = (Request request, Response response) -> {
		Map<String, Object> model = new HashMap<>();
		LoginController.ensureUserIsLoggedIn(request, response);
		Presupuesto presupuestoNuevo = new Presupuesto();
		List<PresupuestoDetallado> presupuestos = PresupuestoDetallado.obtenerTodosEnBD();
		model.put("presupuestos", presupuestos);
		List<Pais> paises = PaisMapperBD.getInstance().obtenerTodos();
		model.put("paises",paises);
		List<Moneda> monedas = MonedaMapperBD.getInstance().obtenerTodos();
		model.put("monedas",monedas);
		if (getQueryComprobanteTipo(request) != null && getQueryComprobanteNumero(request) != null
				&& getQueryDNICUITProveedor(request) != null && getQueryNombreProveedor(request) != null) {
			if (getQueryComprobanteTipo(request).trim().length() == 1) {
				int numeroComprobante;
				try {
					numeroComprobante = Integer.parseInt(getQueryComprobanteNumero(request).trim());
				} catch (NumberFormatException formatoIncorrecto) {
					model.put("FormatoNumeroComprobanteIncorrecto", true);
					return ViewUtil.render(request, model, Path.Template.PRESUPUESTO);
				}

				DocumentoComercial comprobante = new DocumentoComercial(numeroComprobante,
						getQueryComprobanteTipo(request).trim().charAt(0));
				comprobante.setMoneda(buscarMonedaPorID(getQuery_Moneda(request),monedas));
				comprobante.setPais(buscarPaisPorID(getQuery_Pais(request),paises));
				DocumentoComercial.insertarDocumentoEnBD(comprobante);
				presupuestoNuevo.getDocumentosComerciales().add(comprobante);
			} else {
				model.put("comprobanteUnSoloDigito", true);
				return ViewUtil.render(request, model, Path.Template.PRESUPUESTO);
			}

			if (getQueryDNICUITProveedor(request).trim().length() <= 8) {
				int dniPersona;
				try {
					dniPersona = Integer.parseInt(getQueryDNICUITProveedor(request).trim());
				} catch (NumberFormatException formatoDNIIncorrecto) {
					model.put("FormatoNumeroIncorrectoCUITODNI", true);
					return ViewUtil.render(request, model, Path.Template.PRESUPUESTO);
				}
				Persona proveedorEncontradoBD = Persona.buscarPersonaPorDNIEnBD(dniPersona);
				if (proveedorEncontradoBD != null)
					presupuestoNuevo.setProveedor(proveedorEncontradoBD);
				else {
					Persona nuevaPersona = new Persona();

					nuevaPersona.setDni(dniPersona);
					nuevaPersona.setNombreApellido(getQueryNombreProveedor(request).trim());
					Persona.insertarNuevaPersonaEnBD(nuevaPersona);
					presupuestoNuevo.setProveedor(nuevaPersona);
				}
			} else {
				int cuitProveedor;
				try {
					cuitProveedor = Integer.parseInt(getQueryDNICUITProveedor(request).trim());
				} catch (NumberFormatException formatoCUITIncorrecto) {
					model.put("FormatoNumeroIncorrectoCUITODNI", true);
					return ViewUtil.render(request, model, Path.Template.PRESUPUESTO);
				}

				OrganizacionProveedora proveedorEncontradoBD = OrganizacionProveedora
						.buscarProveedorPorCUITEnBD(cuitProveedor);
				if (proveedorEncontradoBD != null)
					presupuestoNuevo.setProveedor(proveedorEncontradoBD);
				else {
					OrganizacionProveedora nuevaOrgProveedora = new OrganizacionProveedora();
					nuevaOrgProveedora.setCuit(cuitProveedor);
					nuevaOrgProveedora.setRazonSocial(getQueryNombreProveedor(request).trim());
					OrganizacionProveedora.insertarNuevoProveedorEnBD(nuevaOrgProveedora);
					presupuestoNuevo.setProveedor(nuevaOrgProveedora);
				}
			}
			if (getQueryPresupuestosSeleccionados(request) != null) {
				String[] identificadoresPresupuestosSeleccionados = getQueryPresupuestosSeleccionados(request);
				List<PresupuestoDetallado> presupuestosSeleccionados = filtrarPresupuestosPorIdentificadores(
						presupuestos, identificadoresPresupuestosSeleccionados);
				presupuestoNuevo.setDetalles(presupuestosSeleccionados);
			} else {
				model.put("PresupuestosNoSeleccionados", true);
				return ViewUtil.render(request, model, Path.Template.PRESUPUESTO);
			}
			Presupuesto.insertarNuevoPresupuestoEnBD(presupuestoNuevo);
			model.put("cargaCompletada", true);
			model.put("idPresupuesto", presupuestoNuevo.getId());
		}

		return ViewUtil.render(request, model, Path.Template.PRESUPUESTO);
	};

	public static Route cargarPresupuestoDetallado = (Request request, Response response) -> {
		Map<String, Object> model = new HashMap<>();
		LoginController.ensureUserIsLoggedIn(request, response);
		List<Producto> productos = Producto.obtenerTodosEnBD();
		model.put("productos", productos);

		if (getQueryMontoTotal(request) != null && getQueryProductoSeleccionado(request) != null) {
			Float montoTotal;
			try {
				montoTotal = Float.parseFloat(getQueryMontoTotal(request).trim());
			} catch (NumberFormatException e1) {
				model.put("FormatoNumeroIncorrecto", true);
				return ViewUtil.render(request, model, Path.Template.PRESUPUESTO_DETALLADO);
			}
			if (montoTotal <= 0) {
				model.put("montoMenorACero", true);
				return ViewUtil.render(request, model, Path.Template.PRESUPUESTO_DETALLADO);
			}

			Long identificadorProductoElegido = Long.parseLong(getQueryProductoSeleccionado(request));
			Producto productoElegido = productos.stream()
					.filter(producto -> producto.getId() == identificadorProductoElegido).collect(Collectors.toList())
					.get(0);

			PresupuestoDetallado presupuestoDetalladoNuevo = new PresupuestoDetallado(montoTotal, productoElegido);
			PresupuestoDetallado.insertarNuevoPDEnBD(presupuestoDetalladoNuevo);
			model.put("cargaCorrecta", true);
			model.put("numeroPresupuesto", presupuestoDetalladoNuevo.getId());

		}

		return ViewUtil.render(request, model, Path.Template.PRESUPUESTO_DETALLADO);
	};

	private static List<PresupuestoDetallado> filtrarPresupuestosPorIdentificadores(
			List<PresupuestoDetallado> presupuestos, String[] identificadoresPresupuestosSeleccionados) {
		List<PresupuestoDetallado> presupuestosFiltrados = new ArrayList<PresupuestoDetallado>();
		for (PresupuestoDetallado presupuesto : presupuestos) {
			int index = 0;
			int longitud = identificadoresPresupuestosSeleccionados.length;
			while (index < longitud) {
				Long identificador = Long.parseLong(identificadoresPresupuestosSeleccionados[index]);
				if (presupuesto.getId() == identificador) {
					presupuestosFiltrados.add(presupuesto);
					break;
				}
				index++;
			}
		}
		return presupuestosFiltrados;
	}
}
