package pt.isec.gps.lab24;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(Commons.VIEW_NOVO_JOGO));
        //Parent root = FXMLLoader.load(getClass().getResource(Commons.VIEW_TABELA_CLASSIFICACAO));

        primaryStage.setTitle("CovidNate");
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
