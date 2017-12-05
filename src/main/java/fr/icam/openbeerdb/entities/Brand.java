package fr.icam.openbeerdb.entities;

public class Brand extends Item {

	private Brewery brewery;
	
	public Brewery getBrewery() {
		return brewery;
	}

	public Brand(Integer id, String user, String name, Brewery brewery) {
		super(id, user, name);
		this.brewery = brewery;
	}
	
}