package fr.icam.openbeerdb.entities;

public class Brewery {

	private Integer id;
	
	private String name;
	
	private String address;
	
	private String city;
	
	private String country;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public Brewery(Integer id, String name, String address, String city, String country) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.country = country;
	}
	
}
