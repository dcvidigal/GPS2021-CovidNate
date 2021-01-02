package pt.isec.gps.lab24.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import pt.isec.gps.lab24.Commons;
import pt.isec.gps.lab24.modal.Jogador;
import pt.isec.gps.lab24.modal.Pessoa;
import pt.isec.gps.lab24.modal.recursos.Posicao;
import pt.isec.gps.lab24.modal.tabuleiro.Tabuleiro;
import pt.isec.gps.lab24.modal.tabuleiro.TabuleiroFacil;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            if(this.jogador == null) this.jogador = new Jogador("guess",0,0);
            if(tab == null) tab = new TabuleiroFacil(10,10,35);

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
                            jogador.setPontos(jogador.getPontos() + 5);
                            lblPontos.setText(jogador.getPontos()+"");
                            atualizaHistoricoJogadas("Pessoa na posição " + p.getPosicao().getX() +","+ p.getPosicao().getY() +" posta em quarentena!");
                        }else{
                            jogador.setPontos(jogador.getPontos() - 2);
                            proximoTurno();
                            atualizaHistoricoJogadas("FAZER MAIS MENSAGENS!!!");
                        }

                    }
                });
            }

        });
    }


    public void proximoTurno(ActionEvent event) {
       atualizaHistoricoJogadas("O jogador " + jogador.getNome() +" passou o turno");
       proximoTurno();
    }

    private void proximoTurno() {
        this.jogador.setTurno(this.jogador.getTurno() + 1 );
        tab.proximoTurno();
        redesenhar();
    }

    private void redesenhar() {
        if(this.jogador.getTurno() < 0 ) this.jogador.setTurno(0);
        lblTurno.setText(this.jogador.getTurno()+"");
        lblPontos.setText(this.jogador.getPontos()+"");
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
