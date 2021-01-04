package pt.isec.gps.lab24;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.gps.lab24.modal.Sistema;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Sistema covidNate = new Sistema();
        Parent root = FXMLLoader.load(getClass().getResource(Commons.VIEW_MAIN_MENU));

        primaryStage.setTitle("CovidNate");
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
