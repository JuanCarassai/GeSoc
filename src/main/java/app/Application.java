package app;


import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import static spark.debug.DebugScreen.enableDebugScreen;

import javax.persistence.EntityManager;

import com.API.ListaAPI;

import controllers.AsociacionesController;
import controllers.AuditoriaController;
import controllers.CompraController;
import controllers.EgresoController;
import controllers.IngresoController;
import controllers.InicioController;
import controllers.InstanciarEmpresaController;
import controllers.LoginController;
import controllers.MensajesController;
import controllers.PresupuestoController;
import controllers.ProductoController;
import controllers.ProyectoController;
import persistencia.BDUtils;

public class Application {

    public static void main(String[] args) {
    	int port = 5020;
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);
        ListaAPI.getInstance().agregarElementosAPI();
        
//        try {
//			QuartzSchedulerCronTriggerExample.getInstance().iniciar();
//		} catch (SchedulerException e) {
//			e.printStackTrace();
//		}
        
        // Configuracion Spark
        //port(getHerokuAssignedPort());
        port(port);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);
        enableDebugScreen(); //ver una pantalla con detalle en caso de error

        // Filtro aplicado antes de get y post
        before("*",             Filters.handleLocaleChange);

        // Rutas (path, controller)
        get(Path.Web.INICIO,     InicioController.servePaginaInicio);
        get(Path.Web.LOGIN,     LoginController.serveLoginPage);
        get(Path.Web.EGRESOS,  EgresoController.cargarEgreso);
        get(Path.Web.INGRESOS,  IngresoController.cargarIngreso);
        post(Path.Web.LOGIN,    LoginController.handleLoginPost);
        get(Path.Web.LOGOUT,   LoginController.handleLogoutPost);
        get(Path.Web.MENSAJES,   MensajesController.mensajesView);
        get(Path.Web.PROYECTO,   ProyectoController.IndexProyecto);
        get(Path.Web.VINCULAR_PROYECTO,   ProyectoController.vincularProyecto);
        get(Path.Web.CARGAR_PROYECTO,   ProyectoController.cargarProyecto);
        get(Path.Web.ASOCIACIONES,   AsociacionesController.handleAsociaciones);
        get(Path.Web.MIS_INGRESOS,  IngresoController.mis_ingresos);
        get(Path.Web.MIS_EGRESOS,  EgresoController.mis_egresos);
        get(Path.Web.AUDITORIA,  AuditoriaController.LogsView);
        get(Path.Web.COMPRA,  CompraController.cargarCompra);
        get(Path.Web.PRODUCTOS, ProductoController.cargarProducto);
        get(Path.Web.PRESUPUESTO, PresupuestoController.cargarPresupuesto);
        get(Path.Web.PRESUPUESTO_DETALLADO, PresupuestoController.cargarPresupuestoDetallado);
        get(Path.Web.CATEGORIA, ProductoController.cargarCategoria);
        get(Path.Web.INSTANCIAR_EMPRESA, InstanciarEmpresaController.instanciarEmpresa);
        get("*",                ViewUtil.notFound);

        // Filtro aplicado despues de get y post
        after("*",              Filters.addGzipHeader);
        
        System.out.println("SERVER IS RUNNING IN "+port);
    }
    
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 5020; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
