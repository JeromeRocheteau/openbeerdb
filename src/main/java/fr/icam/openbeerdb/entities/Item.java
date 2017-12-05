package fr.icam.openbeerdb.entities;

public abstract class Item {
	
	private Integer id;
	
	private String user;

	private String name;
	
	public int getId() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public String getName() {
		return name;
	}

	protected Item(Integer id, String user, String name) {
		this.id = id;
		this.user = user;
		this.name = name;
	}

}
