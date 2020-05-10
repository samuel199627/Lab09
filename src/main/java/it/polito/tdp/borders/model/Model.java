package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	BordersDAO dao;
	private Map<Integer, Country> idMapCountry;
	private List<Border> connessioni;
	private Map<Integer, Country> verticiConnessi;
	
	
	private Graph<Country, DefaultEdge> grafo;

	public Model() {
		dao=new BordersDAO();
	}
	
	public void caricaCountries() {
		idMapCountry=new HashMap<>();
		idMapCountry=dao.loadAllCountries();
		System.out.println("Dimensione paesi caricati: "+idMapCountry.size());
	}
	
	public void caricaConnessioni(int anno) {
		connessioni=new ArrayList<>();
		connessioni=dao.getCountryPairs(anno, idMapCountry);
		System.out.println("Dimensione connessioni caricate: "+connessioni.size());
		
		//mi creo la mappa di vertici che effettivamente sono connessi a qualcuno e che poi dobbiamo restituire
		//indietro al model per caricare il menu a tendina per poi fare il punto 2 dell'esercizio in cui 
		//bisogna trovare tutti i vicini di uno stato passato.
		//Non so se le connessioni sono sempre in tutti e due i versi, quindi provo ad inserire sia la partenza che
		//l'arrivo se non c'e' gia'.
		verticiConnessi=new HashMap<>();
		for(Border b: connessioni) {
			
			//se non sono gia' stati inseriti, inserisco
			if(!verticiConnessi.containsKey(b.getUno().getCode())) {
				verticiConnessi.put(b.getUno().getCode(), b.getUno());
			}
			if(!verticiConnessi.containsKey(b.getDue().getCode())) {
				verticiConnessi.put(b.getDue().getCode(), b.getDue());
			}
		}
		
		System.out.println("Dimensione vertici connessi: "+verticiConnessi.size());
		
	}
	
	public Graph<Country, DefaultEdge> creaGrafo(int anno){
		
		//il grafo ci serve non orientato, semplice e non pesato
		grafo=new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		
		this.caricaConnessioni(anno);
		
		//aggiungo tutti i vertici
		
		
		return grafo;
	}
	
	public Map<Integer,Country> estraiVerticiConnessi(){
		return verticiConnessi;
	}

	
}
