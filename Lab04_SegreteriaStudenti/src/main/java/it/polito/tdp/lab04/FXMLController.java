/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model=new Model();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> choiceCorsi;

    @FXML
    private Button bttnIscrittiCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button bttnStudente;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button bttnCercaCorsi;

    @FXML
    private Button bttnIscrizione;

    @FXML
    private TextArea txtRislutato;

    @FXML
    private Button bttnReset;
    
    @FXML
    private Label labelErrore;

    @FXML
    void doCercaCorsi(ActionEvent event) {

    }

    @FXML
    void doCercaIscritti(ActionEvent event) {

    }

    @FXML
    void doIscrizione(ActionEvent event) {

    }

    @FXML
    void getNomeCognome(ActionEvent event) {

    	if(txtMatricola.getText().isBlank()) {
    		return;
    	}
    	if(!this.isValid(txtMatricola.getText())) {
    		this.labelErrore.setText("ERRORE: matricola nel formato sbagliato");
    		return;
    	}
    	int matricola=Integer.parseInt(txtMatricola.getText());
    	Studente studenteCercato;
    	if((studenteCercato=model.getStudente(matricola))!=null) {
    		txtNome.setText(studenteCercato.getNome());
    		txtCognome.setText(studenteCercato.getCognome());
    		labelErrore.setText(null);
    		return;
    	}
    	labelErrore.setText("Nessuno studente trovato con matricola scritta");
    }

    @FXML
    void doReset(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert choiceCorsi != null : "fx:id=\"choiceCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bttnIscrittiCorso != null : "fx:id=\"bttnIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bttnStudente != null : "fx:id=\"boxStudente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bttnCercaCorsi != null : "fx:id=\"bttnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bttnIscrizione != null : "fx:id=\"bttnIscrizione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRislutato != null : "fx:id=\"txtRislutato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bttnReset != null : "fx:id=\"bttnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelErrore != null : "fx:id=\"labelErrore\" was not injected: check your FXML file 'Scene.fxml'.";
        ObservableList<String> corsi =FXCollections.observableArrayList(model.getTuttiICorsi());
        this.choiceCorsi.setItems(corsi);
        this.choiceCorsi.getItems().add(0,"--Nessun corso--");
    }
    
    public void setModel(Model model) {
    	this.model=model;
    }
    
    public boolean isValid(String matricola) {
    	if(matricola.length()!=6) {
    		return false;
    	}
    	try {
    		Integer.parseInt(matricola);
    	}catch(NumberFormatException nfe) {
    		return false;
    	}
    	return true;
    }
}
