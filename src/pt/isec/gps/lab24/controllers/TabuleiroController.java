package pt.isec.gps.lab24.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pt.isec.gps.lab24.Commons;
import pt.isec.gps.lab24.modal.Jogador;
import pt.isec.gps.lab24.modal.Pessoa;
import pt.isec.gps.lab24.modal.recursos.Posicao;
import pt.isec.gps.lab24.modal.tabuleiro.Tabuleiro;
import pt.isec.gps.lab24.modal.tabuleiro.TabuleiroFacil;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class TabuleiroController implements Initializable {

    @FXML
    public Label lblPontos;
    @FXML
    public Label lblTime;
    @FXML
    public Label lblRecuperados;
    @FXML
    public Label lblTurno;
    @FXML
    public Label lblTimeLimit;
    @FXML
    private GridPane gpTabuleiro;
    @FXML

    private RadioButton facil;
    @FXML
    private RadioButton normal;
    @FXML
    private RadioButton dificil;

    @FXML
    private TextArea historico;

    Timer timer;
    Timer timertotal;
    int secondsToWait = 10;
    int secondsjogo = 0;
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
        atualizaHistoricoJogadas("ola ola");
    }

    public void atualizaHistoricoJogadas (String msg) {
        String strHistorico = historico.getText();
        strHistorico += "\n" + msg;
        historico.setText(strHistorico);
    }

    public void atualizatimer(int i){
        lblTimeLimit.setText(i + "");
    }

    public void timerturno (){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                secondsToWait--;
                atualizatimer(secondsToWait);
                if (secondsToWait == 0) {
                    proximoTurno();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 1000, 1000);

    }

    public void setTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(secondsToWait > 0)
                {
                    Platform.runLater(() -> atualizatimer(secondsToWait));
                    System.out.println(secondsToWait);
                    secondsToWait--;
                }
                else {
                    Platform.runLater(() -> proximoTurno());
                    timer.cancel();
                }
            }
        }, 1000,1000);
    }

    public void atualizatimerjogo(int i){
        lblTime.setText(i + "");
    }

    public void setTimerJogo() {
        timertotal = new Timer();
        timertotal.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                    Platform.runLater(() -> atualizatimerjogo(secondsjogo));
                    System.out.println(secondsjogo);
                    secondsjogo++;
            }
        }, 1000,1000);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            if(this.jogador == null) this.jogador = new Jogador("guess",0,0);
            if(tab == null) tab = new TabuleiroFacil(10,10,35);

            setTimer();
            setTimerJogo();
            redesenhar();

            for (Node node : gpTabuleiro.getChildren()) {
                node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        Node source = (Node) e.getSource();
                        Integer colIndex = gpTabuleiro.getColumnIndex(source);
                        Integer rowIndex = gpTabuleiro.getRowIndex(source);
                        System.out.printf("Mouse entered cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
                        Pessoa p = tab.getPessoa(new Posicao(colIndex.intValue(), rowIndex.intValue()));
                        System.out.println(p.toString());
                        if(p.isInfetada()){//encontrou infetado
                            p.setQuarentena(true);
                            tab.getPessoa(new Posicao(colIndex.intValue(), rowIndex.intValue())).setQuarentena(true);
                            gpTabuleiro.getChildren().remove(p.getPanePessoa());
                            gpTabuleiro.add(p.getPanePessoa(), p.getPosicao().getX(), p.getPosicao().getY());

                            lblPontos.setText(jogador.incPontos(5) + "");
                            atualizaHistoricoJogadas("Pessoa na posição " + p.getPosicao().getX() +","+ p.getPosicao().getY() +" posta em quarentena!");
                            if(!tab.isFimJogo().equals("")) terminarJogo();
                        }else{
                            jogador.setPontos(jogador.decPontos(2));
                            proximoTurno();
                            atualizaHistoricoJogadas("FAZER MAIS MENSAGENS!!!");
                        }

                    }
                });
            }

        });
    }

    public void terminarJogo(ActionEvent event){
        terminarJogo();
    }
    private void terminarJogo() {
        if(timer!=null)timer.cancel();
        if(timer!=null)timertotal.cancel();
        String msg = tab.isFimJogo();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Jogo Terminado");

        if(msg.equals(Commons.FIM_DE_JOGO_VITORIA)){
            a.setHeaderText("Parabéns");
            a.setContentText("Curou Todas as Pessoas");
        }
        if(msg.equals(Commons.FIM_DE_JOGO_PERDEU)){
            a.setHeaderText("OH! Que Pena!");
            a.setContentText("A população está toda infetada");
        }

        Optional<ButtonType> result = a.showAndWait();
        if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE){
            try {
                Parent parent = null;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("."+Commons.VIEW_MAIN_MENU));
                parent = loader.load();
                ((MainMenuController)loader.getController()).setJogador(this.jogador);
                Scene scene = new Scene(parent);
                Stage appStage = (Stage) gpTabuleiro.getScene().getWindow();
                appStage.setScene(scene);
                appStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }// else {}
    }

    @FXML
    public void proximoTurno(ActionEvent event) {
       atualizaHistoricoJogadas("O jogador " + jogador.getNome() +" passou o turno");
       proximoTurno();
        if(!tab.isFimJogo().equals("")) terminarJogo();
    }

    private void proximoTurno() {
        this.jogador.setTurno(this.jogador.getTurno() + 1 );
        tab.proximoTurno();
        secondsToWait=10;
        if(timer!=null) {
            timer.cancel();
        }
        setTimer();
        redesenhar();
    }

    private void redesenhar() {
        if(this.jogador.getTurno() < 0 ) this.jogador.setTurno(0);
        lblTurno.setText(this.jogador.getTurno()+"");
        lblPontos.setText(this.jogador.getPontos()+"");
        lblRecuperados.setText(this.tab.getNumRecuperados()+"");
        int[][] tabAux = tab.getTabuleiro();
        for (int i = 0; i < tabAux.length ; i++) {
            for (int j = 0; j < tabAux.length ; j++) {
                if(tabAux[i][j] != -1){
                    Pessoa p = tab.getPessoa(new Posicao(i,j));
                    Pane pane = new Pane();
                    gpTabuleiro.getChildren().remove(p.getPanePessoa());
                    gpTabuleiro.add(p.getPanePessoa(), p.getPosicao().getX(), p.getPosicao().getY());
                }
            }
        }

    }
}
