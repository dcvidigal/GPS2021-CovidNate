package pt.isec.gps.lab24.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import pt.isec.gps.lab24.Commons;
import pt.isec.gps.lab24.modal.Jogador;

import javax.swing.*;

public class NovoJogoController {
    @FXML
    private RadioButton facil;
    @FXML
    private RadioButton normal;
    @FXML
    private RadioButton dificil;

    private Jogador jogador;

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public void voltar(ActionEvent event) {
            new Commons().mudarEcra(Commons.VIEW_MAIN_MENU, event, jogador);
        }


    public void jogar(ActionEvent event) {
        if(check()== true) {
        new Commons().mudarEcra(Commons.VIEW_TABULEIRO,event,jogador);}
    }

    public boolean check(){
        if(facil.isSelected() == false && normal.isSelected() == false && dificil.isSelected() == false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nada Selecionado");
            alert.setHeaderText(null);
            alert.setContentText("Precisa de selecionar uma dificuldade");
            alert.showAndWait();
            return false;}
        return true;

    }

}
