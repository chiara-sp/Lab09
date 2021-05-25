
package it.polito.tdp.borders;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	int anno;
    	try {
    	 anno= Integer.parseInt(txtAnno.getText());
    	}catch(NumberFormatException e) {
    		txtResult.setText("inserire l'anno in cifre");
    		return;
    	}
    	model.creaGrafo(anno);
    	Map<Country,Integer> stati= model.statiConfinanti();
    	txtResult.appendText("Grafo creato con "+model.numVertici()+ " vertici e "+model.numArchi()+ " archi \n");
    	txtResult.appendText("Elenco stati con numero stati confinanti: \n");
    	for(Country c: stati.keySet()) {
    		txtResult.appendText(c.getName()+ " "+ stati.get(c)+"\n");
    	}
    	txtResult.appendText("\n Numero di componenti connesse: "+model.numeroComponentiConnesse());
    	
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
