package com.rest.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rest.example.domain.MovieEntity;
import com.rest.example.domain.MovieList;

@Controller
public class RestProviderController {
	
	private static final Logger LOGGER = Logger.getLogger(RestProviderController.class);
	
	public List<MovieEntity> listMovies = new ArrayList<MovieEntity>();
	
	public RestProviderController() {
		listMovies = findAll();
	}
	
	@RequestMapping("/showMessage")
	public ModelAndView showMessage(HttpServletRequest servletRequest) {
		LOGGER.info("browse ...");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", "RESTful");
		
		mav.setViewName("showMessage");
		
		return mav;
	}
	
	@RequestMapping(value = "/movies", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	public @ResponseBody MovieList getAllMovies() {
		LOGGER.debug("Provider has received request to getAllMovies");

		MovieList result = new MovieList(listMovies);

		LOGGER.debug("return the results");
		return result;
	}
	
	private List<MovieEntity> findAll() {
		MovieEntity entity = new MovieEntity();
		entity.setId(1);
		entity.setTitle("Spider-Man");
		entity.setActorName("Tobey Maguire");
		entity.setActressName("Willem Dafoe");
		entity.setFlimDirector("Sam Raimi");
		entity.setLanguage("English");
		entity.setReleaseYear(2002);
		listMovies.add(entity);
		
		entity = new MovieEntity();
		entity.setId(2);
		entity.setTitle("Iron-Man");
		entity.setActorName("Robert Downey Jr.");
		entity.setActressName("Terrence Howard");
		entity.setFlimDirector("Jon Favreau");
		entity.setLanguage("English");
		entity.setReleaseYear(2008);
		listMovies.add(entity);
		
		return listMovies;
	}

}
