package fr.icam.openbeerdb.entities;

public class Beer {

	private int id;
	
	private Brewery brewery;
	
	private String name;
	
	private float abv;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public float getAbv() {
		return abv;
	}

	public void setAbv(float abv) {
		this.abv = abv;
	}
	
	public String toJson() {
		return "{id:" + id + ",brewery:" + brewery.toJson() + ",name:" + name + ",abv:" + abv + "}";
	}
	
}