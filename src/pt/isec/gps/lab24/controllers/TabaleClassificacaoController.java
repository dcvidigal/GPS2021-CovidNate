package pt.isec.gps.lab24.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
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
            TableColumn nomeCol = new TableColumn("Nome Jogador");
            nomeCol.setCellValueFactory(new PropertyValueFactory("NomeJogador"));
            TableColumn numberRecuperadosCol = new TableColumn("Numero de Recuperados");
            numberRecuperadosCol.setCellValueFactory(new PropertyValueFactory("NumberRecuperados"));
            TableColumn numberInfetadosCol = new TableColumn("Numero de Infetados");
            numberInfetadosCol.setCellValueFactory(new PropertyValueFactory("numberInfetados"));
            TableColumn turnoCol = new TableColumn("Turnos Usados");
            turnoCol.setCellValueFactory(new PropertyValueFactory("turno"));
            TableColumn tempJogoCol = new TableColumn("Tempo de Jogo");
            tempJogoCol.setCellValueFactory(new PropertyValueFactory("tempJogo"));
            TableColumn pontosCol = new TableColumn("Pontos");
            pontosCol.setCellValueFactory(new PropertyValueFactory("pontos"));
            if(tbView == null) tbView = new TableView();

            tbView.getColumns().addAll(nomeCol,numberRecuperadosCol, numberInfetadosCol,turnoCol,tempJogoCol,pontosCol);

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
                        Integer.parseInt(jObj.get("NumberRecuperados").toString()),
                        Integer.parseInt(jObj.get("NumberInfetados").toString()),
                        Integer.parseInt(jObj.get("Turno").toString()),
                        jObj.get("TempJogo").toString(),
                        Integer.parseInt(jObj.get("pontos").toString())
                ));
            }

            tbView.getItems().addAll(FXCollections.observableArrayList(result));
            tbView.refresh();
        });
    }


}
