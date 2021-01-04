package pt.isec.gps.lab24;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pt.isec.gps.lab24.controllers.MainMenuController;
import pt.isec.gps.lab24.controllers.NovoJogoController;
import pt.isec.gps.lab24.controllers.TabaleClassificacaoController;
import pt.isec.gps.lab24.controllers.TabuleiroController;
import pt.isec.gps.lab24.modal.Jogador;
import pt.isec.gps.lab24.modal.tabuleiro.Tabuleiro;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Commons {

    public static final String VIEW_NOVO_JOGO = "./views/novo_jogo.fxml";
    public static final String VIEW_NOVO_JOGADOR = "./views/novo_jogador.fxml";
    public static final String VIEW_EDITAR_JOGADOR = "./views/editar_jogador.fxml";
    public static final String VIEW_MAIN_MENU = "./views/main_menu.fxml";
    public static final String VIEW_TABELA_CLASSIFICACAO = "./views/tabela_classificacao.fxml";
    public static final String VIEW_TABULEIRO = "./views/tabuleiro.fxml";
    public static final String FIM_DE_JOGO_VITORIA = "FIM_DE_JOGO_VITORIA";
    public static final String FIM_DE_JOGO_PERDEU = "FIM_DE_JOGO_PERDEU";
    public static final int SECONDS_TO_WAIT = 120;


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

    public static void escreveParaFicheiroJSON(Jogador j, Tabuleiro t, String tempoDeJogo){

        //tenta ir buscar dados

        JSONObject map = new JSONObject();
        map.put("NomeJogador", j.getNome());
        map.put("NumberRecuperados",t.getNumRecuperados()+"");
        map.put("NumberInfetados",t.getNumInfetadosInicial()+"");
        map.put("Turno",j.getTurno()+"");
        map.put("TempJogo",tempoDeJogo);
        map.put("pontos",j.getPontos()+"");


        JSONObject jsonObject = (JSONObject) lerDeFicheiroJSON();
        JSONArray json = new JSONArray();
        if(jsonObject == null){
            jsonObject = new JSONObject();
        }else{
            json = (JSONArray) jsonObject.get("");
        }

        json.add(map);
        jsonObject.put("",json);



        try {
            Files.write(Paths.get("highscore.json"), jsonObject.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object lerDeFicheiroJSON(){
        JSONParser object = null;
        try {
            FileReader reader = new FileReader("highscore.json");
            JSONParser jsonParser = new JSONParser();
            return jsonParser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return object;
    }

}
