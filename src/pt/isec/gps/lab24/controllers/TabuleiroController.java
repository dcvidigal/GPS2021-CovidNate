package pt.isec.gps.lab24.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import pt.isec.gps.lab24.modal.Jogador;

public class TabuleiroController {

    @FXML
    private GridPane gpTabuleiro;

    Jogador jogador;

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
}
