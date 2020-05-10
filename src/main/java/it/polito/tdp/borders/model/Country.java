package it.polito.tdp.borders.model;

public class Country {
	
	private int code;
	private String abb;
	private String ext;
	
	
	public Country(int code, String abb, String ext) {
		super();
		this.code = code;
		this.abb = abb;
		this.ext = ext;
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


	public String getExt() {
		return ext;
	}


	public void setExt(String ext) {
		this.ext = ext;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (code != other.code)
			return false;
		return true;
	}
	
	public String toString() {
		return ""+this.getCode()+" "+this.getAbb();
	}
	
	
	

}
