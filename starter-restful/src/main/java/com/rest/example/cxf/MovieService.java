package com.rest.example.cxf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.rest.example.domain.MovieEntity;
import com.rest.example.domain.MovieList;

@Path("/")
@Produces("application/xml")
public interface MovieService {

	 @GET
     @Path("/movies")
	 @Produces({"application/xml", "application/json"})
     MovieList getAllMovies();
	 
	 @GET
	 @Path("/movies/{id}")
	 @Produces("application/json")
	 MovieEntity getMovie(@PathParam("id") Integer id);
}
