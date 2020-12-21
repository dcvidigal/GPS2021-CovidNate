package pt.isec.gps.lab24.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.isec.gps.lab24.modal.Jogador;

public class EditarJogadorController {
    private Jogador jogador;
    @FXML
    TextField tfNome;
    private Stage parentStage;


    public void setStage(Stage temp){
        parentStage = temp;
    }

    public String getNome(){
        return tfNome.getText();
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador (Jogador jogador){
        this.jogador = jogador;
        tfNome.setText(this.jogador.getNome());
    }
    public void confirmar(ActionEvent actionEvent) {
        //TODO validação de nome
        this.jogador.setNome( tfNome.getText()  );
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.close();//TODO Validar se é a melhor forma de sair
    }
}
