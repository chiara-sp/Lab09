package it.polito.tdp.borders.model;

public class Country implements Comparable<Country> {

	private int code;
	private String abb;
	private String name;
	
	public Country(int code, String abb, String name) {
		super();
		this.code = code;
		this.abb = abb;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getAbb() {
		return abb;
	}

	public void setAbb(String abb) {
		this.abb = abb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Country o) {
		// TODO Auto-generated method stub
		return this.name.compareTo(o.name);
	}
	
	
}
