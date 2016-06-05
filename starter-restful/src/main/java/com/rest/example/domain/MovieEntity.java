package com.rest.example.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Movie")
public class MovieEntity {

	private int id;
	private String title;
	private String actorName;
	private String actressName;
	private String flimDirector;
	private int releaseYear;
	private String language;
	
	public MovieEntity() {
		
	}
	
	public MovieEntity(int id, String title, String actorName, String actressName, String flimDirector, int releaseYear,
			String language) {
		super();
		this.id = id;
		this.title = title;
		this.actorName = actorName;
		this.actressName = actressName;
		this.flimDirector = flimDirector;
		this.releaseYear = releaseYear;
		this.language = language;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getActressName() {
		return actressName;
	}

	public void setActressName(String actressName) {
		this.actressName = actressName;
	}

	public String getFlimDirector() {
		return flimDirector;
	}

	public void setFlimDirector(String flimDirector) {
		this.flimDirector = flimDirector;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	@Override
	public String toString() {
		return "MovieEntity [id=" + id + ", title=" + title + ", actorName=" + actorName + ", actressName="
				+ actressName + ", flimDirector=" + flimDirector + ", releaseYear=" + releaseYear + ", language="
				+ language + "]";
	}

}
