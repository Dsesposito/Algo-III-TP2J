package fiuba.algo3.tp2;

import fiuba.algo3.tp2.view.InitContainer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        stage.setTitle("AlgoPoly");

        InitContainer initContainer = new InitContainer(stage);
        Scene initScene = new Scene(initContainer);

        stage.setScene(initScene);
        stage.setMaximized(true);

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
