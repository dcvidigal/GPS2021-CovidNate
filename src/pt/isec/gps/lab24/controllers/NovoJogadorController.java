package pt.isec.gps.lab24.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.isec.gps.lab24.modal.Jogador;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class NovoJogadorController {
    @FXML
    TextField tfNome;
    Button btnConfirm;

    private Jogador jogador;

    @FXML
    private void confirmAction(ActionEvent event) {

        this.jogador = new Jogador(tfNome.getText().trim(),0,0);

        closeStage(event);
    }

    private void closeStage(ActionEvent event) {
        Node  source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public Jogador getJogador(){
        return this.jogador;
    }

}
