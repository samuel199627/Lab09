
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class FXMLController {
	
	Model model;
	Graph<Country, DefaultEdge> grafo;
	List<Set<Country>> componentiConnesse;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtAnno;

    @FXML
    private ComboBox<Country> countryBox;

    @FXML
    private Button ricercaBtn;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	//controlliamo per prima cosa nell'importazione che abbiamo effettivamente inserito un numero
    	int anno ;
    	try {
    		anno = Integer.parseInt(txtAnno.getText());
    	} catch (Throwable t){
    		txtResult.appendText("Errore nell'input!");
    		return;
    	}
    	
    	//controlliamo che l'anno sia nell'intervallo specificato
    	if(anno<1816 || anno>2016) {
    		System.out.println("ANNO FUORI RANGE!");
    		return;
    	}
    	
    	System.out.println("COSTRUIAMO IL GRAFO!");
    	grafo=new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
    	grafo=model.creaGrafo(anno);
    	
    	System.out.println("\nINFORMAZIONI SUL GRAFO: \n");
    	System.out.println("\nNumero di vertici: "+grafo.vertexSet().size());
    	System.out.println("\nNumero di archi: "+grafo.edgeSet().size());
    	
    	for(Country c:grafo.vertexSet()) {
    		txtResult.appendText(""+c.toString()+" - grado "+grafo.degreeOf(c)+"\n");
    	}
    	
    	//componenti connesse
    	ConnectivityInspector ispezione=new ConnectivityInspector(grafo);
    	componentiConnesse=new ArrayList<Set<Country>>();
    	componentiConnesse=ispezione.connectedSets();
    	txtResult.appendText("Componenti connesse: "+componentiConnesse.size());
    	System.out.println("\n\nCOMPONENTI CONNESSE.\n");
    	int conta=0;
    	for(Set<Country> s:componentiConnesse) {
    		conta++;
    		System.out.println("\n\nCOMPONENTE "+conta+":\n\n");
    		for(Country c:s) {
    			System.out.println(""+c.toString());
    		}
    	}
    	
    	Map<Integer, Country> verticiConnessi=new HashMap<Integer, Country>();
    	verticiConnessi=model.estraiVerticiConnessi();
    	countryBox.getItems().clear();
    	countryBox.getItems().addAll(verticiConnessi.values());
    	
    	

    }

    @FXML
    void doRicerca(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert countryBox != null : "fx:id=\"countryBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert ricercaBtn != null : "fx:id=\"ricercaBtn\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model=model;
		model.caricaCountries();
	}
    
    
}

