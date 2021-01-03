package pt.isec.gps.lab24.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pt.isec.gps.lab24.Commons;
import pt.isec.gps.lab24.modal.Classificacao;
import pt.isec.gps.lab24.modal.Jogador;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            TableColumn nomeCol = new TableColumn("nome");
            nomeCol.setCellValueFactory(new PropertyValueFactory("NomeJogador"));
            TableColumn numberInfetadosCol = new TableColumn("numberInfetados");
            numberInfetadosCol.setCellValueFactory(new PropertyValueFactory("numberInfetados"));
            TableColumn turnoCol = new TableColumn("turno");
            turnoCol.setCellValueFactory(new PropertyValueFactory("turno"));
            TableColumn tempJogoCol = new TableColumn("tempJogo");
            tempJogoCol.setCellValueFactory(new PropertyValueFactory("tempJogo"));
            TableColumn pontosCol = new TableColumn("pontos");
            pontosCol.setCellValueFactory(new PropertyValueFactory("pontos"));
            if(tbView == null) tbView = new TableView();
            tbView.getColumns().setAll(nomeCol,numberInfetadosCol,turnoCol,tempJogoCol,pontosCol);

            JSONObject jsonObject = (JSONObject) Commons.lerDeFicheiroJSON();
            JSONArray json = new JSONArray();
            if(jsonObject == null){
                jsonObject = new JSONObject();
            }else{
                json = (JSONArray) jsonObject.get("");
            }

            List<Classificacao> result = new ArrayList<>();

            for (int i = 0; i < json.size(); i++) {
                JSONObject jObj = ((JSONObject) json.get(i));
                result.add(new Classificacao(jObj.get("NomeJogador").toString(),
                        Integer.parseInt(jObj.get("NumberInfetados").toString()),
                        Integer.parseInt(jObj.get("NumberInfetados").toString()),
                        jObj.get("Turno").toString(),
                                Integer.parseInt(jObj.get("TempJogo").toString())
                ));
            }

            tbView.getItems().addAll(FXCollections.observableArrayList(result));
            tbView.refresh();
        });
    }


}
