package com.API;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import persistencia.MedioDePagoMapperBD;
import persistencia.MonedaMapperBD;
import persistencia.PaisMapperBD;

public class ListaAPI {

	private static final ListaAPI instance = new ListaAPI();

	private ListaAPI() {
	}

	public static ListaAPI getInstance() {
		return instance;
	}

	public List<MedioDePago> obtenerListaAPImedioPago() throws IOException {
		List<MedioDePago> list = null;
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			HttpGet get = new HttpGet("https://api.mercadopago.com/sites/MLA/payment_methods");
			HttpResponse responseGet = client.execute(get);
			HttpEntity responseEntity = responseGet.getEntity();
			ObjectMapper objectMapper = new ObjectMapper();
			list = objectMapper.readValue(responseEntity.getContent(), new TypeReference<List<MedioDePago>>() {
			});

		}
		return list;
	}

	public void agregarNuevosMediosDePago() {
		if (!MedioDePagoMapperBD.getInstance().mediosCargadosEnBD()) {
			List<MedioDePago> medios;
			try {
				medios = ListaAPI.getInstance().obtenerListaAPImedioPago();
				MedioDePagoMapperBD.getInstance().insert(medios);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	public void agregarNuevasMonedas() {
		if (!MonedaMapperBD.getInstance().monedasCargadasEnBD()) {
			List<Moneda> monedas;
			try {
				monedas = ListaAPI.getInstance().obtenerListaAPImoneda();
				MonedaMapperBD.getInstance().insert(monedas);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	public void agregarNuevosPaises() {
		if (!PaisMapperBD.getInstance().paisesCargadosEnBD()) {
			List<Pais> paises;
			try {
				paises = ListaAPI.getInstance().obtenerListaAPIPaisDetallado(obtenerListaAPIPais());
				PaisMapperBD.getInstance().insert(paises);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	public void agregarElementosAPI() {
		ListaAPI.getInstance().agregarNuevasMonedas();
		ListaAPI.getInstance().agregarNuevosMediosDePago();
		ListaAPI.getInstance().agregarNuevosPaises();
	}
	

	public List<Moneda> obtenerListaAPImoneda() throws IOException {
		List<Moneda> list = null;
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			HttpGet get = new HttpGet("https://api.mercadolibre.com/currencies/");
			HttpResponse responseGet = client.execute(get);
			HttpEntity responseEntity = responseGet.getEntity();
			ObjectMapper objectMapper = new ObjectMapper();
			list = objectMapper.readValue(responseEntity.getContent(), new TypeReference<List<Moneda>>() {
			});

		}
		return list;
	}

	public Pais obtenerPais(String id)
			throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {
		Pais pais = null;
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			HttpGet get = new HttpGet("https://api.mercadolibre.com/classified_locations/countries/" + id);
			HttpResponse responseGet = client.execute(get);
			HttpEntity responseEntity = responseGet.getEntity();
			ObjectMapper objectMapper = new ObjectMapper();
			pais = objectMapper.readValue(responseEntity.getContent(), Pais.class);
		}

		return pais;
	}

	public List<Pais> obtenerListaAPIPais()
			throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {
		List<Pais> list = null;
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			HttpGet get = new HttpGet("https://api.mercadolibre.com/classified_locations/countries");
			HttpResponse responseGet = client.execute(get);
			HttpEntity responseEntity = responseGet.getEntity();
			ObjectMapper objectMapper = new ObjectMapper();
			list = objectMapper.readValue(responseEntity.getContent(), new TypeReference<List<Pais>>() {
			});
		}
		return list;
	}

	public List<Pais> obtenerListaAPIPaisDetallado(List<Pais> paises)
			throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {
		List<Pais> paisesDetallados = new ArrayList<Pais>();
		int index = 0;
		int size = paises.size();
		while (index < size) {
			Pais paisDetallado = obtenerPais(paises.get(index).getId());
			paisesDetallados.add(paisDetallado);
			index++;
		}
		return paisesDetallados;
	}

	public Provincia obtenerProvincia(String id)
			throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {
		Provincia provincia = null;
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			HttpGet get = new HttpGet("https://api.mercadolibre.com/classified_locations/states/" + id);
			HttpResponse responseGet = client.execute(get);
			HttpEntity responseEntity = responseGet.getEntity();
			ObjectMapper objectMapper = new ObjectMapper();
			provincia = objectMapper.readValue(responseEntity.getContent(), Provincia.class);
		}

		return provincia;
	}

	public List<Provincia> obtenerListaAPIProvinciasDetalladas(List<Provincia> provincias)
			throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {
		if (provincias != null) {
			List<Provincia> provinciasDetalladas = new ArrayList<Provincia>();
			int index = 0;
			int size = provincias.size();

			while (index < size) {
				Provincia provinciaDetallada = obtenerProvincia(provincias.get(index).getId());
				provinciasDetalladas.add(provinciaDetallada);
				index++;
			}
			return provinciasDetalladas;
		} else
			return provincias;
	}

	public Ciudad obtenerCiudad(String id)
			throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {
		Ciudad ciudad = null;
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
			HttpGet get = new HttpGet("https://api.mercadolibre.com/classified_locations/cities/" + id);
			HttpResponse responseGet = client.execute(get);
			HttpEntity responseEntity = responseGet.getEntity();
			ObjectMapper objectMapper = new ObjectMapper();
			ciudad = objectMapper.readValue(responseEntity.getContent(), Ciudad.class);
		}
		return ciudad;
	}

	public List<Ciudad> obtenerListaAPICiudadesDetalladas(List<Ciudad> ciudades)
			throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {
		if (ciudades != null) {
			List<Ciudad> ciudadesDetalladas = new ArrayList<Ciudad>();
			int index = 0;
			int size = ciudades.size();

			while (index < size) {
				Ciudad ciudadDetallada = obtenerCiudad(ciudades.get(index).getId());
				ciudadesDetalladas.add(ciudadDetallada);
				index++;
			}
			return ciudadesDetalladas;
		} else
			return ciudades;
	}



}
