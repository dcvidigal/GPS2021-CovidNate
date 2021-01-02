package pt.isec.gps.lab24.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import javafx.scene.control.RadioButton;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import pt.isec.gps.lab24.Commons;
import pt.isec.gps.lab24.modal.Jogador;

import javafx.event.ActionEvent;
import pt.isec.gps.lab24.modal.tabuleiro.Tabuleiro;
import pt.isec.gps.lab24.modal.tabuleiro.TabuleiroDificil;
import pt.isec.gps.lab24.modal.tabuleiro.TabuleiroFacil;
import pt.isec.gps.lab24.modal.tabuleiro.TabuleiroNormal;

import java.util.Optional;


public class TabuleiroController {

    @FXML
    private GridPane gpTabuleiro;
    @FXML

    private RadioButton facil;
    @FXML
    private RadioButton normal;
    @FXML
    private RadioButton dificil;

    private TextArea historico;


    Jogador jogador;
    private Tabuleiro tab;
    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }



    public void desistir (ActionEvent event){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Desistir da partida");
        a.setHeaderText("Pretende desistir da partida");
        a.setContentText("Ao escolher a opção Sim, desistirá da partida e o Vírus vencerá.");

        ButtonType buttonTypeOne = new ButtonType("Sim");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        a.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == buttonTypeOne){
            new Commons().mudarEcra(Commons.VIEW_MAIN_MENU, event, jogador);
        }// else {}
    }

    public void setTabuleiro(Tabuleiro tab) {
        this.tab=tab;
    }
    public void hisJoagadas (){
        historico.setText("ola ola");
    };

}
