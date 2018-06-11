package com.excilys.cdb.ui;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.client.ClientProtocolException;

import com.excilys.cdb.model.Computer;

public class RestClientComputer {
	public static final int PORT = 8085;
	public static final String DEFAULT_URL_CONTEXT = "http://localhost:8085/webservice/computer";
	public static final Client client = ClientBuilder.newClient();
/*
	public static void main(final String[] arguments) throws IOException {
		RestClientComputer restClient = new RestClientComputer();
		System.out.println(restClient.getComputer(1));

		System.out.println(restClient.getAllComputers());

		System.out.println(restClient.getComputerPage("", 0, 10));

		Computer computer = new Computer();
		// computer.setName("Computer test aaaaaaaaaaaaaaa");
		// System.out.println(restClient.saveComputer(computer));
		computer.setName("Computer test aaaaaaaaaaaaaaa modif");
		computer.setIntroduced(LocalDate.now());
		computer.setId(611);
		// System.out.println(restClient.updateComputer(computer));
		// System.out.println(restClient.deleteComputer(computer));
	}
*/
	public Computer getComputer(long id) throws ClientProtocolException, IOException {
		Computer computer = client.target(DEFAULT_URL_CONTEXT + "/").path(String.valueOf(id))
		    .request(MediaType.APPLICATION_JSON).get(Computer.class);
		return computer;
	}

	public List<Computer> getAllComputers() throws ClientProtocolException, IOException {
		Response serviceResponse = client.target(DEFAULT_URL_CONTEXT + "/").path(String.valueOf(""))
		    .request(MediaType.APPLICATION_JSON).get();
		List<Computer> computers = serviceResponse.readEntity(new GenericType<List<Computer>>() {
		});
		return computers;
	}

	public List<Computer> getComputerPage(String search, long page, long size)
	    throws ClientProtocolException, IOException {
		String requete = "?search=" + search + "&page=" + page + "&size=" + size;
		Response serviceResponse = client.target(DEFAULT_URL_CONTEXT + requete).path(String.valueOf(""))
		    .request(MediaType.APPLICATION_JSON).get();
		List<Computer> computers = serviceResponse.readEntity(new GenericType<List<Computer>>() {
		});
		return computers;
	}

	public int saveComputer(Computer computer) {
		Response response = client.target(DEFAULT_URL_CONTEXT + "/").request(MediaType.APPLICATION_JSON)
		    .post(Entity.json(computer));
		return response.getStatus();
	}

	public int updateComputer(Computer computer) {
		Response response = client.target(DEFAULT_URL_CONTEXT + "/" + computer.getId()).request(MediaType.APPLICATION_JSON)
		    .put(Entity.json(computer));
		return response.getStatus();
	}

	public int deleteComputer(Computer computer) {
		Response response = client.target(DEFAULT_URL_CONTEXT + "/" + computer.getId()).request().delete();
		return response.getStatus();
	}

}
