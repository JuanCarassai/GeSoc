package controllers;


import static app.RequestUtil.getQuery_actividad_base_empresa;
import static app.RequestUtil.getQuery_actividad_jur_empresa;
import static app.RequestUtil.getQuery_codigo_inscripcion_jur;
import static app.RequestUtil.getQuery_codigo_postal_jur;
import static app.RequestUtil.getQuery_cuit_jur;
import static app.RequestUtil.getQuery_descripcion_base;
import static app.RequestUtil.getQuery_entidadesBaseSeleccionadas;
import static app.RequestUtil.getQuery_nombre_ficticio_base;
import static app.RequestUtil.getQuery_nombre_ficticio_jur;
import static app.RequestUtil.getQuery_personal_base_empresa;
import static app.RequestUtil.getQuery_personal_jur_empresa;
import static app.RequestUtil.getQuery_razon_social_jur;
import static app.RequestUtil.getQuery_seleccion_base_jur;
import static app.RequestUtil.getQuery_tipoEntidad_base_osc_empresa;
import static app.RequestUtil.getQuery_tipoEntidad_jur_osc_empresa;
import static app.RequestUtil.getQuery_ventasAnuales_base_empresa;
import static app.RequestUtil.getQuery_ventasAnuales_jur_empresa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Path;
import app.ViewUtil;
import entidades.Empresa;
import entidades.EntidadBase;
import entidades.EntidadJuridica;
import entidades.InstanciarEmpresa;
import entidades.OSC;
import spark.Request;
import spark.Response;
import spark.Route;

public class InstanciarEmpresaController {
	public static Route instanciarEmpresa = (Request request, Response response) -> {
		Map<String, Object> model = new HashMap<>();
		List<EntidadBase> entidadesBaseLista = EntidadBase.obtenerTodosSinAsignar();
		model.put("entidadesBaseLista", entidadesBaseLista);
		LoginController.ensureUserIsLoggedIn(request, response);
		if(getQuery_seleccion_base_jur(request)!=null) {
			if(getQuery_seleccion_base_jur(request).equals("0")) {
				model.put("seleccion_base_jur_incorrecta", true);
				return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
			}
			if(getQuery_seleccion_base_jur(request).equals("1")) {
				EntidadBase entidad = new EntidadBase();
				entidad.setNombreFicticio(getQuery_nombre_ficticio_base(request));
				entidad.setDescripcion(getQuery_descripcion_base(request));
				if(getQuery_tipoEntidad_base_osc_empresa(request).equals("0")) 
				{					
					model.put("seleccion_empresa_base_incorrecta", true);
					return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
				}
				else if(getQuery_tipoEntidad_base_osc_empresa(request).equals("1")) {
					OSC osc = new OSC();
					entidad.setTipoDeEntidad(osc);
					}
				
				else if(getQuery_tipoEntidad_base_osc_empresa(request).equals("2")) {					
					Empresa empresa = new Empresa();
					if(getQuery_actividad_base_empresa(request).equals("0")) {
						model.put("seleccion_actividad_base_incorrecta", true);
						return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
					}
					empresa.setActividad(getQuery_actividad_base_empresa(request));
					Integer cantidadPersonal;
					Integer cantidadVtasAnuales;
					try {
						cantidadPersonal = Integer.parseInt(getQuery_personal_base_empresa(request).trim());
					} catch (NumberFormatException e1) {
						model.put("FormatoPersonal_base_Incorrecto", true);
						return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
					}
					if (cantidadPersonal < 0) {
						model.put("cantidadPersonalBaseMenorACero", true);
						return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
					}
					try {
						cantidadVtasAnuales = Integer.parseInt(getQuery_ventasAnuales_base_empresa(request).trim());
					} catch (NumberFormatException e1) {
						model.put("FormatoVtasAnuales_base_Incorrecto", true);
						return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
					}
					if (cantidadVtasAnuales < 0) {
						model.put("cantidadVtasAnualesBaseMenorACero", true);
						return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
					}
					empresa.setPersonal(cantidadPersonal);
					empresa.setVtasAnuales(cantidadVtasAnuales);
					InstanciarEmpresa.definirEmpresa(empresa);
					entidad.setTipoDeEntidad(empresa);

				}
				EntidadBase.insertarNuevaBase(entidad);
				model.put("Carga_de_Base_exitosa", true);
			}
			if(getQuery_seleccion_base_jur(request).equals("2")) {
				EntidadJuridica entidad = new EntidadJuridica();
				entidad.setNombreFicticio(getQuery_nombre_ficticio_jur(request));
				Integer codigoInscripcion;
				try {
					codigoInscripcion = Integer.parseInt(getQuery_codigo_inscripcion_jur(request).trim());
				} catch (NumberFormatException e1) {
					model.put("codigoInsciripcion_jur_Incorrecto", true);
					return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
				}
				if (codigoInscripcion < 0) {
					model.put("codigoInscripcionJurMenorACero", true);
					return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
				}
				entidad.setCodigoInscripcion(codigoInscripcion);
				Integer codigoPostal;
				try {
					codigoPostal = Integer.parseInt(getQuery_codigo_postal_jur(request).trim());
				} catch (NumberFormatException e1) {
					model.put("codigoPostal_Jur_Incorrecto", true);
					return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
				}
				if (codigoPostal < 0) {
					model.put("codigoPostalJurMenorACero", true);
					return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
				}
				entidad.setCodigoPostal(codigoPostal);
				Integer nroCuit;
				try {
					nroCuit = Integer.parseInt(getQuery_cuit_jur(request).trim());
				} catch (NumberFormatException e1) {
					model.put("nroCuit_Jur_Incorrecto", true);
					return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
				}
				if (nroCuit < 0) {
					model.put("nroCuitJurMenorACero", true);
					return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
				}
				entidad.setCuit(nroCuit);
				entidad.setRazonSocial(getQuery_razon_social_jur(request));
				
				String[] entidadBaseSelec = getQuery_entidadesBaseSeleccionadas(request);
				List<EntidadBase> entidadesBase = EntidadBase.obtenerMisEntidades(entidadBaseSelec,entidadesBaseLista);
				entidad.setEntidades(entidadesBase);
				
				if(getQuery_tipoEntidad_jur_osc_empresa(request).equals("0")) {
					
					model.put("seleccion_empresa_jur_incorrecta", true);
					return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
					}
				else if(getQuery_tipoEntidad_jur_osc_empresa(request).equals("1")) {					
					OSC osc = new OSC();
					entidad.setTipoDeEntidad(osc);
				}
				else if(getQuery_tipoEntidad_jur_osc_empresa(request).equals("2")) {					
					Empresa empresa = new Empresa();
					if(getQuery_actividad_jur_empresa(request).equals("0")) {
						model.put("seleccion_actividad_jur_incorrecta", true);
						return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
					}
					empresa.setActividad(getQuery_actividad_jur_empresa(request));
					Integer cantidadPersonal;
					Integer cantidadVtasAnuales;
					try {
						cantidadPersonal = Integer.parseInt(getQuery_personal_jur_empresa(request).trim());
					} catch (NumberFormatException e1) {
						model.put("FormatoPersonal_jur_Incorrecto", true);
						return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
					}
					if (cantidadPersonal < 0) {
						model.put("cantidadPersonalJurMenorACero", true);
						return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
					}
					try {
						cantidadVtasAnuales = Integer.parseInt(getQuery_ventasAnuales_jur_empresa(request).trim());
					} catch (NumberFormatException e1) {
						model.put("FormatoVtasAnuales_jur_Incorrecto", true);
						return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
					}
					if (cantidadVtasAnuales < 0) {
						model.put("cantidadVtasAnualesJurMenorACero", true);
						return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
					}
					empresa.setPersonal(cantidadPersonal);
					empresa.setVtasAnuales(cantidadVtasAnuales);
					InstanciarEmpresa.definirEmpresa(empresa);
					entidad.setTipoDeEntidad(empresa);

				}
				EntidadJuridica.insertarNuevaJuridica(entidad);				
				model.put("Carga_de_Jur_exitosa", true);
				EntidadBase.asignarEntidadJuridica(entidad.getEntidades(), entidad);
			}

		}

		return ViewUtil.render(request, model, Path.Template.INSTANCIAR_EMPRESA);
	};

}
