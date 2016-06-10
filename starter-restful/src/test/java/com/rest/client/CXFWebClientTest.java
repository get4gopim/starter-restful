package com.rest.client;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import com.rest.example.domain.MovieEntity;
import com.rest.example.domain.MovieList;

public class CXFWebClientTest {
	
	@Test
	public void test() {
		WebClient webclient = WebClient
                .create("http://localhost:8080/starter-restful/cxf/service/")
                .path("movies").accept(MediaType.APPLICATION_XML_TYPE);
		
		//webclient.header("Authorization", getUsernameAndPasswordAsEncoded("admin", "admin"));
		
		MovieList message = webclient.get(MovieList.class);
		
		if (message != null) {
			for (MovieEntity movie : message.getListMovies()) {
				System.out.println("movie : " + movie);
			}
		}
		
	}
	
	@Test
	public void testPost() {
		WebClient webclient = WebClient
                .create("http://localhost:8080/starter-restful/cxf/service/")
                .path("movies").accept(MediaType.APPLICATION_XML_TYPE);
		
		//webclient.header("Authorization", getUsernameAndPasswordAsEncoded("admin", "admin"));
		MovieEntity entity = new MovieEntity(3, "Welcome", "Welcome", "Welcome", "Welcome", 2016, "English");
		Response response = webclient.post(entity, Response.class);
		
		if (response != null) {
			System.out.println("POST response : " + response);
		}
		
	}
	
	@Test
	public void testDelete() {
		WebClient webclient = WebClient
                .create("http://localhost:8080/starter-restful/cxf/service/")
                .path("movies").accept(MediaType.APPLICATION_XML_TYPE);
		
		//webclient.header("Authorization", getUsernameAndPasswordAsEncoded("admin", "admin"));
		Response response = webclient.query("id", new Integer(3)).delete();
		
		if (response != null) {
			System.out.println("POST response : " + response.getStatus());
		}
		
	}
	
	@Test
	public void testGetEntity() {
		WebClient webclient = WebClient
                .create("http://localhost:8080/starter-restful/cxf/service/")
                .path("movies").accept(MediaType.APPLICATION_JSON);
		
		//webclient.header("Authorization", getUsernameAndPasswordAsEncoded("admin", "admin"));
		MovieEntity entity = webclient.path(new Integer(2)).get(MovieEntity.class);
		
		if (entity != null) {
			System.out.println("POST response : " + entity);
		}
		
	}
	
	public String getUsernameAndPasswordAsEncoded(String username, String password) {
		return "Basic " + org.apache.cxf.common.util.Base64Utility.encode((username + ":" + password).getBytes());
	}

}
