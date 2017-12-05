package fr.icam.openbeerdb.entities;

public class Style extends Item {
	
	private Style category;

	public Style getCategory() {
		return category;
	}

	public Style(Integer id, String user, String name, Style category) {
		super(id, user, name);
		this.category = category;
	}
	
}
