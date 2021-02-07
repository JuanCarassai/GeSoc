package egresosIngresos;

import javax.persistence.Column;
import javax.persistence.Entity;

import persistencia.ProveedorMapperBD;

@Entity
public class OrganizacionProveedora extends Proveedor {
	int cuit;
	@Column(name = "RAZON_SOCIAL")
	String razonSocial;

	public int getCuit() {
		return cuit;
	}

	public void setCuit(int cuit) {
		this.cuit = cuit;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Override
	public String getNombre() {
		return razonSocial;
	}

	public static OrganizacionProveedora buscarProveedorPorCUITEnBD(int cuitProveedor) {
		return ProveedorMapperBD.getInstance().buscarProveedorPorCuit(cuitProveedor);
	}

	public static void insertarNuevoProveedorEnBD(OrganizacionProveedora nuevaOrgProveedora) {
		ProveedorMapperBD.getInstance().insert(nuevaOrgProveedora);
		
	}
}
