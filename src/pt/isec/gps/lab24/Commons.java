package pt.isec.gps.lab24;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.gps.lab24.controllers.MainMenuController;
import pt.isec.gps.lab24.controllers.NovoJogoController;
import pt.isec.gps.lab24.controllers.TabaleClassificacaoController;
import pt.isec.gps.lab24.controllers.TabuleiroController;
import pt.isec.gps.lab24.modal.Jogador;
import pt.isec.gps.lab24.modal.tabuleiro.Tabuleiro;

import java.io.IOException;

public class Commons {

    public static final String VIEW_NOVO_JOGO = "./views/novo_jogo.fxml";
    public static final String VIEW_NOVO_JOGADOR = "./views/novo_jogador.fxml";
    public static final String VIEW_EDITAR_JOGADOR = "./views/editar_jogador.fxml";
    public static final String VIEW_MAIN_MENU = "./views/main_menu.fxml";
    public static final String VIEW_TABELA_CLASSIFICACAO = "./views/tabela_classificacao.fxml";
    public static final String VIEW_TABULEIRO = "./views/tabuleiro.fxml";

    public Commons() {
    }
    public void mudarEcra(String fxml, ActionEvent event, Jogador j){
        mudarEcra( fxml,  event,  j, null);
    }

    public void mudarEcra(String fxml, ActionEvent event, Jogador j, Tabuleiro tab){
        try {
            Parent parent = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            parent = loader.load();
            if(VIEW_MAIN_MENU.equals(fxml)) ((MainMenuController)loader.getController()).setJogador(j);
            if(VIEW_TABELA_CLASSIFICACAO.equals(fxml)) ((TabaleClassificacaoController)loader.getController()).setJogador(j);
            if(VIEW_NOVO_JOGO.equals(fxml)) ((NovoJogoController)loader.getController()).setJogador(j);
            if(VIEW_TABULEIRO.equals(fxml) ){
                ((TabuleiroController)loader.getController()).setJogador(j);
                ((TabuleiroController)loader.getController()).setTabuleiro(tab);
            }

            Scene scene = new Scene(parent);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
