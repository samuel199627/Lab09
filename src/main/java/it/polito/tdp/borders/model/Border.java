package it.polito.tdp.borders.model;

//classe che mi salva le connessioni tra due stati via terra secondo l'anno che abbiamo passato

public class Border {
	
	private Country uno;
	private Country due;
	private int anno;
	
	public Border(Country uno, Country due, int anno) {
		super();
		this.uno = uno;
		this.due = due;
		this.anno = anno;
	}

	public Country getUno() {
		return uno;
	}

	public void setUno(Country uno) {
		this.uno = uno;
	}

	public Country getDue() {
		return due;
	}

	public void setDue(Country due) {
		this.due = due;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}
	
	
	

}
