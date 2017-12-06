package fiuba.algo3.tp2;

import fiuba.algo3.tp2.view.InitContainer;
import fiuba.algo3.tp2.view.MainContainer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        stage.setTitle("AlgoPoly");

        InitContainer initContainer = new InitContainer(stage);
        Scene initScene = new Scene(initContainer,960,400);

        stage.setScene(initScene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
