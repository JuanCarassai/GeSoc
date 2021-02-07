package entidades;



public class InstanciarEmpresa {
	// Matriz de Cantidad  [TipoEmpresa,Actividad]


	private static int matrizXpersonal [][]  = {{12,7,7,15,5},{45,30,35,60,10},{200,165,125,235,50},{590,535,345,655,215}};
	private static int matrizXventasAnuales [][] = {{15230000,8500000,29740000,26540000,12890000},{90310000,50950000,178860000,190410000,48480000},{503880000,425170000,1502750000,1190330000,345430000},{755740000,607210000,2146810000,1739590000,547890000}};
	
	public InstanciarEmpresa() {}

	public static int[][] getMatrizXpersonal() {
		return matrizXpersonal;
	}

	public static void setMatrizXpersonal(int[][] matrizXpersonal) {
		InstanciarEmpresa.matrizXpersonal = matrizXpersonal;
	}

	public static int[][] getMatrizXventasAnuales() {
		return matrizXventasAnuales;
	}

	public static void setMatrizXventasAnuales(int[][] matrizXventasAnuales) {
		InstanciarEmpresa.matrizXventasAnuales = matrizXventasAnuales;
	}

	public static Empresa definirEmpresa(Empresa empresa) {
	Empresa tipoEmpresa = null;
		switch(empresa.getActividad()) {
		case "CONSTRUCCION":
			tipoEmpresa = definirEmpresaXactividad(0, empresa);
			break;
		case "SERVICIOS":
			tipoEmpresa = definirEmpresaXactividad(1, empresa);
			break;
		case "COMERCIO":
			tipoEmpresa= definirEmpresaXactividad(2, empresa);
			break;
		case "INDUSTRIAyMINERIA":
			tipoEmpresa= definirEmpresaXactividad(3, empresa);
			break;
		case "AGROPECUARIO":
			tipoEmpresa= definirEmpresaXactividad(4, empresa);
			break;
		default:
			System.out.println("No es una actividad registrada");
		}
		return tipoEmpresa;
	}
	
	public static Empresa definirEmpresaXactividad(int actividad,Empresa empresa) {
	Empresa tipoEmpresa;
	if(empresa.getVtasAnuales() > 0) {
		tipoEmpresa = instanciar(buscarTipoEmpresaXventas(actividad,empresa));
	}else {
		tipoEmpresa = instanciar(buscarTipoEmpresaXpersonal(actividad,empresa));
	}
	return tipoEmpresa;
	}
	
	public static Empresa instanciar(int valor) {
	Empresa empresa = null;
	
		switch(valor) {
		case -1:
			System.out.println("no esta definido");
			break;
		case 0:
			empresa = new Micro();
			System.out.println("micro");
			break;
		case 1:
			empresa = new Pequenia();
			System.out.println("pequenia");
			break;
		case 2:
			empresa = new MedianaTramo1();
			System.out.println("medianaTramo1");
			break;
		case 3:
			empresa = new MedianaTramo2();
			System.out.println("medianaTramo2");
			break;
		}
		return empresa;
	}
	
	public static int buscarTipoEmpresaXpersonal(int n,Empresa empresa) {
		int j = 0;
		while(j<4) {
			if(empresa.getPersonal() >= matrizXpersonal[j][n])
				j++;
			else 
				return j;
		}
		return -1;
	}
	
	public static int buscarTipoEmpresaXventas(int n,Empresa empresa) {
		int j = 0;
		while(j<4) {
			if(empresa.getVtasAnuales() >= matrizXventasAnuales[j][n])
				j++;
			else 
				return j;
		}
		return -1;
	}
}
