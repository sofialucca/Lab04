/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private TextArea txtRisultato;

    @FXML
    private Button bttnReset;
    
    @FXML
    private Label labelErrore;
    
    @FXML
    private Label labelErroriCorsi;
    

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	this.labelErroriCorsi.setText(null);
    	this.choiceCorsi.setValue(null);
    	txtRisultato.clear();
    	if(!this.isValid(txtMatricola.getText())) {
    		return;
    	}
    	int matricola=Integer.parseInt(txtMatricola.getText());

    	List<Corso> listaCorsi=model.getCorsiStudente(matricola);
    	this.labelErrore.setText(null);
    	if(listaCorsi.isEmpty()) {
    		this.txtRisultato.setText("Studente iscritto a 0 corsi");
    		return;
    	}
    	
    	for(Corso c:listaCorsi) {
    		this.txtRisultato.appendText(c.toString()+"\n");
    	}
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {

    	this.txtRisultato.clear();
    	this.labelErrore.setText(null);
    	if(!this.checkSelezioneCorsi()) {
    		return;
    	}
    	
    	List<Studente> studentiCorso=model.getStudentiIscrittiAlCorso(choiceCorsi.getValue());
    	this.labelErroriCorsi.setText(null);
    	if(studentiCorso.isEmpty()) {
    		txtRisultato.setText("ERRORE:studente non presente nel database");
    		return;
    	}
    	for(Studente s:studentiCorso) {
    		this.txtRisultato.appendText(s.toString()+"\n");
    	}
    	
    	
    }

    @FXML
    void doIscrizione(ActionEvent event) {
    	txtRisultato.clear();
    	this.labelErrore.setText(null);
    	this.labelErroriCorsi.setText(null);
    	boolean checkMatricola=this.isValid(txtMatricola.getText());
    	boolean checkCorsi=this.checkSelezioneCorsi();
    	if((!checkMatricola)||(!checkCorsi)) {
    		return;
    	}
    	int matricola=Integer.parseInt(txtMatricola.getText());
    	if(model.iscriviStudenteACorso(matricola, this.choiceCorsi.getValue())) {
    		this.txtRisultato.setText("Iscrizione avvenuta con successo");
    	}else {
    		this.txtRisultato.setText("Studente già iscritto al corso");
    	}
    }

    @FXML
    void getNomeCognome(ActionEvent event) {
    	this.labelErroriCorsi.setText(null);
    	if(!this.isValid(txtMatricola.getText())) {
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
    }

    @FXML
    void doReset(ActionEvent event) {
    	this.choiceCorsi.setValue(null);
    	this.txtMatricola.clear();
    	this.txtRisultato.clear();
    	this.labelErrore.setText(null);
    	this.labelErroriCorsi.setText(null);
    	this.txtCognome.clear();
    	this.txtNome.clear();
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
        assert txtRisultato != null : "fx:id=\"txtRislutato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bttnReset != null : "fx:id=\"bttnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelErrore != null : "fx:id=\"labelErrore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelErroriCorsi != null : "fx:id=\"labelErroriCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        ObservableList<String> corsi =FXCollections.observableArrayList(model.getTuttiICorsi());
        this.choiceCorsi.setItems(corsi);
        this.choiceCorsi.getItems().add(0,"--Nessun corso--");
    }
    
    public void setModel(Model model) {
    	this.model=model;
    }
    
    public boolean isValid(String matricola) {
    	if(txtMatricola.getText().isBlank()) {
    		this.labelErrore.setText("ERRORE: inserire matricola");
    		return false;
    	}
    	if(matricola.length()!=6) {
    		this.labelErrore.setText("ERRORE: matricola nel formato sbagliato");
    		return false;
    	}
    	int matricolaInt;
    	try {
    		matricolaInt=Integer.parseInt(matricola);
    	}catch(NumberFormatException nfe) {
    		this.labelErrore.setText("ERRORE: matricola nel formato sbagliato");
    		return false;
    	}
    	if(model.getStudente(matricolaInt)==null) {
    		this.labelErrore.setText("ERRORE:studente non presente nel database");
    		return false;
    	}
    	return true;
    }
    
    public boolean checkSelezioneCorsi() {
    	if(choiceCorsi.getValue()==null||choiceCorsi.getValue().equals("--Nessun corso--")) {
    		this.labelErroriCorsi.setText("ERRORE: nessun corso selezionato");
    		return false;
    	}
    	return true;
    }
}
