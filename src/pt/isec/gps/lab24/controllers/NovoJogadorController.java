package pt.isec.gps.lab24.controllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.isec.gps.lab24.Commons;
import pt.isec.gps.lab24.DialogModal;
import pt.isec.gps.lab24.modal.Jogador;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.function.BinaryOperator;

public class NovoJogadorController {
    @FXML
    TextField tfNome;
    Button btnConfirm;
    private Jogador jogador;

    @FXML
    private void confirmAction(ActionEvent event) {
        if(validar() == true){
            this.jogador = new Jogador(tfNome.getText().trim(), 0, 0);
            closeStage(event);
        }
    }

    private void closeStage(ActionEvent event) {
        Node  source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    public Boolean validar(){
        if(tfNome.getText().isEmpty()) tfNome.setText("Guess");
        if(tfNome.getText().length() < 4 || tfNome.getText().length() > 16){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nome invalido");
            alert.setHeaderText(null);
            alert.setContentText("Nome precisa de ter no minimo 4 letras e menos de 16!");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    public void sair(ActionEvent event){
        this.jogador = new Jogador();
        Platform.exit();

    }
    public Jogador getJogador(){
        return this.jogador;
    }

}
