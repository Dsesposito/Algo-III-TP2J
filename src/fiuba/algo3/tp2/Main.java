package fiuba.algo3.tp2;

import fiuba.algo3.tp2.view.InitContainer;
import fiuba.algo3.tp2.view.MainContainer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        stage.setTitle("AlgoPoly");

        MainContainer mainContainer = new MainContainer(stage);
        Scene playScene = new Scene(mainContainer,1280,720);

        InitContainer initContainer = new InitContainer(stage,playScene);
        Scene initScene = new Scene(initContainer,960,400);

        stage.setScene(playScene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
