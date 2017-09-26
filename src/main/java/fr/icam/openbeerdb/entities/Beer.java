package fr.icam.openbeerdb.entities;

import java.util.List;

public class Beer {

	private Integer id;
	
	private Brewery brewery;
	
	private String name;
	
	private Float abv;
	
	private List<Style> styles;

	public Integer getId() {
		return id;
	}

	public Brewery getBrewery() {
		return brewery;
	}

	public String getName() {
		return name;
	}

	public Float getAbv() {
		return abv;
	}

	public List<Style> getStyles() {
		return styles;
	}

	public void setStyles(List<Style> styles) {
		this.styles = styles;
	}

	public Beer(Integer id, String name, Float abv, Brewery brewery) {
		super();
		this.id = id;
		this.brewery = brewery;
		this.name = name;
		this.abv = abv;
	}
	
}