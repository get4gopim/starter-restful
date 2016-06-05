package com.rest.example.domain;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="MovieList")
public class MovieList {
	
	private Collection<MovieEntity> listMovies;

	@XmlElement(name="Movies")
	public Collection<MovieEntity> getListMovies() {
		return listMovies;
	}

	public void setListMovies(Collection<MovieEntity> listMovies) {
		this.listMovies = listMovies;
	}
	
	public MovieList() {
		
	}
	
	public MovieList(Collection<MovieEntity> movies) {
        this.listMovies = movies;
    }	
}
