package fr.icam.openbeerdb.entities;

public class Brand {

	private Integer id;
	
	private Brewery brewery;
	
	private String name;

	public Integer getId() {
		return id;
	}

	public Brewery getBrewery() {
		return brewery;
	}

	public String getName() {
		return name;
	}

	public Brand(Integer id, String name, Brewery brewery) {
		super();
		this.id = id;
		this.brewery = brewery;
		this.name = name;
	}
	
}