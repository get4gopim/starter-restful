package com.rest.example.cxf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.rest.example.domain.MovieEntity;
import com.rest.example.domain.MovieList;

public class MovieServiceImpl implements MovieService {
	
	/*@Context
	UriInfo uriInfo;
	
	@Context
	Request request;*/
	
	private static Map<Integer, MovieEntity> movies = new HashMap<Integer, MovieEntity>();

    static {
    	movies.put(1, new MovieEntity(1, "Spider-Man 2", "Tobey Maguire", "Willem Dafoe", "Sam Raimi", 2006, "English"));
    	movies.put(2, new MovieEntity(2, "Iron-Man 2", "Robert Downey Jr.", "Terrence Howard", "Jon Favreau", 2010, "English"));
    }

	@Override
	public MovieList getAllMovies() {
		return new MovieList(movies.values());
	}

	@Override
	public MovieEntity getMovie(Integer id) {
		return movies.get(id);
	}

	@Override
	public Response addMovie(MovieEntity movie) {
		if (movies.containsKey(movie.getId())) {
			return Response.status(Status.CONFLICT).build();
		}
		
		movies.put(movie.getId(), movie);
		return Response.status(Status.CREATED).build();
	}
	
	@Override
	public Response updateMovie(MovieEntity movie) {
		if (!movies.containsKey(movie.getId())) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		movies.put(movie.getId(), movie);
		return Response.status(Status.OK).build();
	}

	@Override
	public Response deleteMovie(Integer id) {
		if (!movies.containsKey(id)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		movies.remove(id);
		return Response.status(Status.OK).build();
	}
	
	@Override
	public Response findAllMoviesByYear(int year) {
		MovieList movies = new MovieList(findByYear(year));
		
		if (movies.getListMovies().isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(movies).build();
	}
	
	
	private Collection<MovieEntity> findByYear(int year) {
		Collection<MovieEntity> listMovies = new ArrayList<MovieEntity>();
		
		for (MovieEntity movie : movies.values()) {
			if (movie.getReleaseYear() == year) {
				listMovies.add(movie);
			}
		}
		
		return listMovies;
	}
}
