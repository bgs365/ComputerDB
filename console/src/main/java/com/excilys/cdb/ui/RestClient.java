package com.excilys.cdb.ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.client.ClientProtocolException;

import com.excilys.cdb.model.Computer;

public class RestClient {
	public static final int PORT = 8085;
	public static final String DEFAULT_URL_CONTEXT = "http://localhost:8085/webservice/computer";
	public static final Client client = ClientBuilder.newClient();

	public static void main(final String[] arguments) throws IOException {
		//getComputer();
		// getAllComputers();
		// getComputerPage();
		saveComputer();
	}

	public static void getComputer() throws ClientProtocolException, IOException {
		long id = 58;

		Computer computer = client.target(DEFAULT_URL_CONTEXT + "/").path(String.valueOf(id))
		    .request(MediaType.APPLICATION_JSON).get(Computer.class);
		System.out.println(computer.toString());
	}

	public static void getAllComputers() throws ClientProtocolException, IOException {
		Response serviceResponse = client.target(DEFAULT_URL_CONTEXT + "/").path(String.valueOf(""))
		    .request(MediaType.APPLICATION_JSON).get();
		List<Computer> computers = serviceResponse.readEntity(new GenericType<List<Computer>>() {
		});
		System.out.println(computers);
	}

	public static void getComputerPage() throws ClientProtocolException, IOException {
		String search = "";
		long page = 0;
		long size = 10;
		String requete = "?search=" + search + "&page=" + page + "&size=" + size;
		Response serviceResponse = client.target(DEFAULT_URL_CONTEXT + requete).path(String.valueOf(""))
		    .request(MediaType.APPLICATION_JSON).get();
		List<Computer> computers = serviceResponse.readEntity(new GenericType<List<Computer>>() {
		});
		System.out.println(computers);
	}

	public static void saveComputer() {
		Computer computer = new Computer();
		computer.setName("Creee par web Service aaaaaaaa");
		//computer.setDiscontinued(LocalDate.now());
		Response response = client.target(DEFAULT_URL_CONTEXT + "/").request(MediaType.APPLICATION_JSON).post(Entity.json(computer));
		System.out.println(response.getStatus());
	}

}
