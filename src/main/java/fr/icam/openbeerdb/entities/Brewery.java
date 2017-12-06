package fr.icam.openbeerdb.entities;

public class Brewery extends Item {

	private String address;
	
	private String city;
	
	private String country;

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public Brewery(Integer id, String user, String name, String address, String city, String country) {
		super(id, user, name);
		this.address = address;
		this.city = city;
		this.country = country;
	}
	
}
