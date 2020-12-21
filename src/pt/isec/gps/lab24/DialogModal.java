package pt.isec.gps.lab24;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.gps.lab24.Commons;
import pt.isec.gps.lab24.controllers.EditarJogadorController;
import pt.isec.gps.lab24.modal.Jogador;

import java.io.IOException;

public class DialogModal
{
    private String fxmlURL;
    private int width;
    private int height;
    private final Jogador j;
    public DialogModal( String fxmlURL, Jogador j )
    {
        this.fxmlURL = fxmlURL;
        this.j = j;
    }

    public <T> T showDialogModal(Scene rootScene) throws IOException
    {
        Stage modalDialog = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource( fxmlURL ));
        Parent modalDialogRoot = loader.load();
        T controller = loader.getController(); // Retrieve the controller
        if(Commons.VIEW_EDITAR_JOGADOR.equals(fxmlURL)) ((EditarJogadorController)controller).setJogador(j);
        //if(Commons.NOVO_JOGO.equals(fxmlURL)) ((NovoJogo)loader.getController()).setJogador(j);

        Scene modalScene = new Scene( modalDialogRoot);
        modalDialog.initOwner(rootScene.getWindow());
        modalDialog.setScene(modalScene);
        modalDialog.setResizable(false);

        // You need Platform.runLater() so that this method doesn't get blocked
        /*Platform.runLater(() -> modalDialog.showAndWait());*/
        modalDialog.showAndWait();
        return controller; // Return the controller back to caller
    }
}