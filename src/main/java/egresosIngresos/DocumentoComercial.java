package egresosIngresos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.API.Moneda;
import com.API.Pais;

import persistencia.DocumentoComercialMapperBD;

@Entity
public class DocumentoComercial {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int nroComercial;
	private char tipo;
	@ManyToOne
	private Pais pais;
	@ManyToOne
	private Moneda moneda;
	
	public DocumentoComercial(int nroComercial, char tipo) {
		super();
		this.nroComercial = nroComercial;
		this.tipo = tipo;
	}
	
	public DocumentoComercial() {

	}
	
	@Override
	public String toString() {
		return "DocumentoComercial [id=" + id + ", nroComercial=" + nroComercial + ", tipo=" + tipo + "]";
	}


	public long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getNroComercial() {
		return nroComercial;
	}


	public void setNroComercial(int nroComercial) {
		this.nroComercial = nroComercial;
	}


	public char getTipo() {
		return tipo;
	}


	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public static void insertarDocumentoEnBD(DocumentoComercial doc) {
		DocumentoComercialMapperBD.getInstance().insert(doc);
	}
	
}
