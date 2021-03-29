/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> choiceCorsi;

    @FXML
    private Button bttnIscrittiCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private CheckBox boxStudente;

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

    }

    @FXML
    void soReset(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert choiceCorsi != null : "fx:id=\"choiceCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bttnIscrittiCorso != null : "fx:id=\"bttnIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxStudente != null : "fx:id=\"boxStudente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bttnCercaCorsi != null : "fx:id=\"bttnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bttnIscrizione != null : "fx:id=\"bttnIscrizione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRislutato != null : "fx:id=\"txtRislutato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bttnReset != null : "fx:id=\"bttnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
