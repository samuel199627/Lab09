
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtAnno;

    @FXML
    private ComboBox<?> countryBox;

    @FXML
    private Button ricercaBtn;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaConfini(ActionEvent event) {

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
		
	}
    
    
}

