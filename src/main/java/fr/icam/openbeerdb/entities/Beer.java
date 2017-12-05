package fr.icam.openbeerdb.entities;

public class Beer extends Item {

	private Brewery brewery;
	
	private Brand brand;
	
	private Float abv;
	
	public Brewery getBrewery() {
		return brewery;
	}

	public Brand getBrand() {
		return brand;
	}

	public Float getAbv() {
		return abv;
	}

	public Beer(Integer id, String user, String name, Float abv, Brewery brewery, Brand brand) {
		super(id, user, name);
		this.brewery = brewery;
		this.abv = abv;
		this.brand = brand;
	}
	
}