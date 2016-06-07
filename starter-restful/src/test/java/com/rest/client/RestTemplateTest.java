package com.rest.client;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rest.example.domain.MovieEntity;
import com.rest.example.domain.MovieList;

public class RestTemplateTest {
	
	@Test
	public void testSimpleRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		
	    MovieList message = restTemplate.getForObject("http://localhost:8080/starter-restful/cxf/service/movies", MovieList.class);
	    
		if (message != null) {
			for (MovieEntity movie : message.getListMovies()) {
				System.out.println("movie : " + movie);
			}
		}
	}
	
	@Test
	public void testRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
	    headers.setContentType(MediaType.APPLICATION_XML);
	    headers.set("Authorization", getUsernameAndPasswordAsEncoded("admin", "admin"));
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    
	    ResponseEntity<MovieList> response = restTemplate.exchange("http://localhost:8080/starter-restful/cxf/service/movies", HttpMethod.GET, entity, MovieList.class);
	    System.out.println("response : " + response);
	    
	    if (response != null) {
			for (MovieEntity movie : ((MovieList) response.getBody()).getListMovies()) {
				System.out.println("movie : " + movie);
			}
		}
	}
	
	public String getUsernameAndPasswordAsEncoded(String username, String password) {
		return "Basic " + org.apache.cxf.common.util.Base64Utility.encode((username + ":" + password).getBytes());
	}

}
