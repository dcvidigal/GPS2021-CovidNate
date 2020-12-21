package pt.isec.gps.lab24.controllers;

import javafx.event.ActionEvent;
import pt.isec.gps.lab24.Commons;
import pt.isec.gps.lab24.modal.Jogador;

public class NovoJogoController {
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

    public void jogar(ActionEvent event) {new Commons().mudarEcra(Commons.VIEW_TABULEIRO,event,jogador);}
}
