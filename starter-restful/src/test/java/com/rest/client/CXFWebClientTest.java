package com.rest.client;

import javax.ws.rs.core.MediaType;

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
	
	public String getUsernameAndPasswordAsEncoded(String username, String password) {
		return "Basic " + org.apache.cxf.common.util.Base64Utility.encode((username + ":" + password).getBytes());
	}

}
