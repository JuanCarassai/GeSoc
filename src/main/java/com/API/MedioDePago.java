package com.API;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import persistencia.MedioDePagoMapperBD;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class MedioDePago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_medioDePago;
	private String id;
	private String name;
	private String payment_type_id;
	
	public MedioDePago() {}
	public MedioDePago(String id, String name, String payment_type_id) {
		super();
		this.id = id;
		this.name = name;
		this.payment_type_id = payment_type_id;
	}

	@Override
	public String toString() {
		return "MedioDePago [id=" + id + ", name=" + name + ", payment_type_id=" + payment_type_id + "]";
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPayment_type_id() {
		return payment_type_id;
	}

	public void setPayment_type_id(String payment_type_id) {
		this.payment_type_id = payment_type_id;
	}


	public Long getId_medioDePago() {
		return id_medioDePago;
	}

	public static void insertarNuevoMedioDePagoEnBD(MedioDePago mp) {
		MedioDePagoMapperBD.getInstance().insert(mp);
	}
	public static List<MedioDePago> obtenerTodosDeBD() {
		return MedioDePagoMapperBD.getInstance().obtenerTodos();
	}
	
	public static MedioDePago buscarMedioDePagoPorID(String id, List<MedioDePago> medios) {
		int index = 0;
		int size = medios.size();
		Long idCasteado = Long.parseLong(id);
		while(index<size) {
			if(medios.get(index).getId_medioDePago() == idCasteado) 
				return medios.get(index);
			index++;
		}
		return null; //si llega aca cagamos
		
	}

}