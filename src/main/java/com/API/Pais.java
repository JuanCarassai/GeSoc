package com.API;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity(name = "Pais")
public class Pais {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_pais;
	
	private String id;
	private String name;
	private String locale;
	private String currency_id;
	private String decimal_separator;
	private String thousands_separator;
	private String time_zone;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Coordenada geo_information;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "country_id_pais")
	private List<Provincia> states;
	
	@Override
	public String toString() {
		return "Pais [id=" + id + ", name=" + name + ", locale=" + locale + ", currency_id=" + currency_id
				+ ", decimal_separator=" + decimal_separator + ", thousands_separator=" + thousands_separator
				+ ", time_zone=" + time_zone + ", geo_information=" + geo_information + ", states=" + states + "]";
	}
	public Long getId_pais() {
		return id_pais;
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
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getCurrency_id() {
		return currency_id;
	}
	public void setCurrency_id(String currency_id) {
		this.currency_id = currency_id;
	}
	public String getDecimal_separator() {
		return decimal_separator;
	}
	public void setDecimal_separator(String decimal_separeator) {
		this.decimal_separator = decimal_separeator;
	}
	public String getThousands_separator() {
		return thousands_separator;
	}
	public void setThousands_separator(String thousands_separator) {
		this.thousands_separator = thousands_separator;
	}
	public String getTime_zone() {
		return time_zone;
	}
	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}
	public Coordenada getGeo_information() {
		return geo_information;
	}
	public void setGeo_information(Coordenada geo_information) {
		this.geo_information = geo_information;
	}
	public List<Provincia> getStates() {
		return states;
	}
	public void setStates(List<Provincia> states) {
		this.states = states;
	}
	
	public static Pais buscarPaisPorID(String id, List<Pais> paises) {
		int index = 0;
		int size = paises.size();
		Long idCasteado = Long.parseLong(id);
		while(index<size) {
			if(paises.get(index).getId_pais() == idCasteado) 
				return paises.get(index);
			index++;
		}
		return null; //si llega aca cagamos
		
	}
	
}
