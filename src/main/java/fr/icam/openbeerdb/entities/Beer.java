package fr.icam.openbeerdb.entities;

public class Beer {

	private Integer id;
	
	private Brewery brewery;
	
	private String name;
	
	private Float abv;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Brewery getBrewery() {
		return brewery;
	}

	public void setBrewery(Brewery brewery) {
		this.brewery = brewery;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getAbv() {
		return abv;
	}

	public void setAbv(Float abv) {
		this.abv = abv;
	}

	public Beer(Integer id, Brewery brewery, String name, Float abv) {
		super();
		this.id = id;
		this.brewery = brewery;
		this.name = name;
		this.abv = abv;
	}
	
}