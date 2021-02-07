package com.API;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Moneda")
public class Moneda {
	@Override
	public String toString() {
		return "Moneda [id=" + id + ", symbol=" + symbol + ", description=" + description + ", decimal_places="
				+ decimal_places + "]";
	}
	public Moneda() {}
	public Moneda(String id, String symbol, String description, int decimal_places) {
		super();
		this.id = id;
		this.symbol = symbol;
		this.description = description;
		this.decimal_places = decimal_places;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_moneda;
	private String id;
	private String symbol;
	private String description;
	private int decimal_places;
	public String getId() {
		return id;
	}
	public Long getId_moneda() {
		return id_moneda;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDecimal_places() {
		return decimal_places;
	}
	public void setDecimal_places(int decimal_places) {
		this.decimal_places = decimal_places;
	}
			
	public static Moneda buscarMonedaPorID(String id, List<Moneda> monedas) {
		int index = 0;
		int size = monedas.size();
		Long idCasteado = Long.parseLong(id);
		while(index<size) {
			if(monedas.get(index).getId_moneda() == idCasteado) 
				return monedas.get(index);
			index++;
		}
		return null; //si llega aca cagamos
		
	}
}
