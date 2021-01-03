package pt.isec.gps.lab24.controllers;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.json.JSONObject;
import pt.isec.gps.lab24.Commons;
import pt.isec.gps.lab24.DialogModal;
import pt.isec.gps.lab24.modal.Jogador;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TabaleClassificacaoController implements Initializable {
    @FXML
    private TableView tbView;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            HashMap<String, String> map = new HashMap<>();
            map.put("nome","jogador1");
            map.put("numberInfetados","32");
            map.put("turno","10");
            map.put("tempJogo","01:25:00");
            map.put("pontos","25");

            JSONObject jsonObject = new JSONObject(map);

            tbView.setItems(FXCollections.observableArrayList(jsonObject));
        });
    }
}
