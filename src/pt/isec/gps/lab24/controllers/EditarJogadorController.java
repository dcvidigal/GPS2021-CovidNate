package pt.isec.gps.lab24.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.isec.gps.lab24.Commons;
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
        if(validar() == true) {
            this.jogador.setNome(tfNome.getText());
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.close();//TODO Validar se é a melhor forma de sair
        }
        }

    public Boolean validar(){
        if(tfNome.getText().length() < 4 || tfNome.getText().length() > 16){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nome invalido");
            alert.setHeaderText(null);
            alert.setContentText("Nome precisa de ter no minimo 4 letraas e menos de 16!");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void voltar(ActionEvent event) {
        closeStage(event);
    }

    private void closeStage(ActionEvent event) {
        Node  source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
