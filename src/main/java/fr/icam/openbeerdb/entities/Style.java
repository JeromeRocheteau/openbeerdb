package fr.icam.openbeerdb.entities;

public class Style {

	private Integer id;
	
	private String name;
	
	private Style category;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Style getCategory() {
		return category;
	}

	public Style(Integer id, String name, Style category) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
	}
	
}
