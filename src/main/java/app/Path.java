package app;

import lombok.Getter;

public class Path {

    // The @Getter methods are needed in order to access
    // the variables from Velocity Templates
    public static class Web {
        @Getter public static final String INICIO = "/inicio";
		@Getter public static final String LOGIN = "/login";
        @Getter public static final String LOGOUT = "/logout";
        @Getter public static final String EGRESOS = "/egresos";
        @Getter public static final String INGRESOS = "/ingresos";
        @Getter public static final String MENSAJES = "/mensajes";
        @Getter public static final String PROYECTO = "/proyecto";
        @Getter public static final String VINCULAR_PROYECTO = "/vincular-proyecto";
        @Getter public static final String CARGAR_PROYECTO = "/cargar-proyectos";
        @Getter public static final String ASOCIACIONES = "/asociaciones";
        @Getter public static final String MIS_EGRESOS = "/mis-egresos";
        @Getter public static final String MIS_INGRESOS = "/mis-ingresos";
        @Getter public static final String AUDITORIA = "/auditoria";
        @Getter public static final String COMPRA = "/compras";
        @Getter public static final String PRODUCTOS = "/productos";
        @Getter public static final String PRESUPUESTO = "/presupuesto";
        @Getter public static final String PRESUPUESTO_DETALLADO = "/presupuesto-detallado";
        @Getter public static final String CATEGORIA = "/cargar-categoria";
        @Getter public static final String INSTANCIAR_EMPRESA = "/instanciar-empresa";
        
        
		public static String getLOGIN() {
			return LOGIN;
		}
		public static String getLOGOUT() {
			return LOGOUT;
		}

    }

    //ruta de los templates en la carpeta src/main/resources
    public static class Template {
        public final static String INICIO = "/velocity/pages/inicio.vm";
        public final static String LOGIN = "/velocity/pages/login.vm";
        public static final String NOT_FOUND = "/velocity/notFound.vm";
        public static final String EGRESOS = "/velocity/pages/cargaregresos.vm";
        public static final String INGRESOS = "/velocity/pages/cargaringresos.vm";
        public static final String MENSAJES = "/velocity/pages/mensajes.vm";
        public static final String PROYECTO = "/velocity/pages/proyecto.vm";
        public static final String VINCULAR_PROYECTO = "/velocity/pages/vincular-proyecto.vm";
        public static final String CARGAR_PROYECTO = "/velocity/pages/cargar-proyectos.vm";
        public static final String ASOCIACIONES = "/velocity/pages/asociaciones.vm";
        public static final String MIS_EGRESOS = "/velocity/pages/mis-egresos.vm";
        public static final String MIS_INGRESOS = "/velocity/pages/mis-ingresos.vm";
        public static final String AUDITORIA = "/velocity/pages/auditoria.vm";
        public static final String COMPRA = "/velocity/pages/cargar-compras.vm";
        public static final String PRODUCTOS = "/velocity/pages/productos.vm";
        public static final String PRESUPUESTO = "/velocity/pages/presupuesto.vm";
        public static final String PRESUPUESTO_DETALLADO = "/velocity/pages/presupuesto-detallado.vm";
        public static final String CATEGORIA = "/velocity/pages/categorias.vm";
        public static final String INSTANCIAR_EMPRESA = "/velocity/pages/instanciar_empresa.vm";
        
    }

}
