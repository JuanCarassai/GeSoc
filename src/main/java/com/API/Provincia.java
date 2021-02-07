package com.API;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity(name = "Provincia")
public class Provincia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_provincia;
	
	private String id;
	private String name;
	@ManyToOne
	
	private Pais country;
	@OneToOne(cascade = CascadeType.ALL)
	private Coordenada geo_information;
	private String time_zone;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "state_id_provincia")
	private List<Ciudad> cities;
	
	public Pais getCountry() {
		return country;
	}
	public void setCountry(Pais country) {
		this.country = country;
	}
	public Long getId_provincia() {
		return id_provincia;
	}
	public void setId_provincia(Long id_provincia) {
		this.id_provincia = id_provincia;
	}
	public Coordenada getGeo_information() {
		return geo_information;
	}
	public void setGeo_information(Coordenada geo_information) {
		this.geo_information = geo_information;
	}
	public String getTime_zone() {
		return time_zone;
	}
	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}
	public List<Ciudad> getCities() {
		return cities;
	}
	public void setCities(List<Ciudad> cities) {
		this.cities = cities;
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
	
	@Override
	public String toString() {
		return "Provincia [id=" + id + ", name=" + name + ", country=" + country + ", geo_information="
				+ geo_information + ", time_zone=" + time_zone + ", cities=" + cities + "]";
	}
	
}
