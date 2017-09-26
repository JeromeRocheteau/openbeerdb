package fr.icam.openbeerdb.entities;

public class Stats {

	private String label;
	
	private Integer count;
	
	private Float average;

	public String getLabel() {
		return label;
	}

	public Integer getCount() {
		return count;
	}

	public Float getAverage() {
		return average;
	}

	public Stats(String label, Integer count, Float average) {
		super();
		this.label = label;
		this.count = count;
		this.average = average;
	}
	
}
