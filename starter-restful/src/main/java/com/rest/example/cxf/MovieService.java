package com.rest.example.cxf;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.rest.example.domain.MovieEntity;
import com.rest.example.domain.MovieList;

@Path("/")
@Produces({"application/xml", "application/json"})
public interface MovieService {

	 @GET
     @Path("/movies")
	 @Produces({"application/xml", "application/json"})
	 @Consumes({"application/xml", "application/json"})
     MovieList getAllMovies();
	 
	 @GET
	 @Path("/movies/{id}")	 
	 MovieEntity getMovie(@PathParam("id") Integer id);
	 
	 @POST
	 @Path("/movies")
	 @Produces({"application/xml", "application/json"})
	 Response addMovie(MovieEntity movie);
	 
	 @PUT
	 @Path("/movies")
	 @Produces({"application/xml", "application/json"})
	 Response updateMovie(MovieEntity movie);
	 
	 @DELETE
	 @Path("/movies")
	 @Produces({"application/xml", "application/json"})
	 Response deleteMovie(@QueryParam("id") Integer id);
	 
	 @GET
	 @Path("/movies/year/{year}")
	 Response findAllMoviesByYear(@PathParam("year") int year);
}
