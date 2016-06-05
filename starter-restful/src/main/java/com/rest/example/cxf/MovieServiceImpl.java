package com.rest.example.cxf;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

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

}
