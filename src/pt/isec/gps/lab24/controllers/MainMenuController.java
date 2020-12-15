package pt.isec.gps.lab24.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pt.isec.gps.lab24.DialogModal;
import pt.isec.gps.lab24.modal.Jogador;
import pt.isec.gps.lab24.Commons;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    private Jogador jogador = null;
    private Stage primaryStage;
    @FXML
    private GridPane grPane; //é so para se ir buscar o Scence onde se encontra
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //TODO Initialize the popup
        Platform.runLater(() -> {
            try {
                if(jogador == null){
                    jogador = new Jogador();
                    DialogModal modal = new DialogModal(Commons.VIEW_NOVO_JOGADOR, jogador);
                    NovoJogadorController controller = null;
                    controller = modal.showDialogModal(grPane.getScene());
                    jogador = controller.getJogador();
                    System.out.println(jogador.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void showClassificacao(ActionEvent event) throws IOException
    {
        new Commons().mudarEcra(Commons.VIEW_TABELA_CLASSIFICACAO, event, jogador);
    }

    public void sair(ActionEvent event){

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        appStage.close();//TODO Validar se é a melhor forma de sair
    }


    public void editarJogador( ActionEvent event ){

        DialogModal modal = new DialogModal(Commons.VIEW_EDITAR_JOGADOR, jogador);
        EditarJogadorController controller = null;
        try {
            controller = modal.showDialogModal(((Node)event.getSource()).getScene());
            jogador = controller.getJogador();
            System.out.println(jogador);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void novoJogo( ActionEvent event ){

        DialogModal modal = new DialogModal(Commons.VIEW_NOVO_JOGO, jogador);
        /*NovoJogo controller = null;
        try {
            controller = modal.showDialogModal(((Node)event.getSource()).getScene());
            jogador = controller.getJogador();
            System.out.println(jogador);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public void setStage(Stage temp){
        primaryStage = temp;
    }
}
