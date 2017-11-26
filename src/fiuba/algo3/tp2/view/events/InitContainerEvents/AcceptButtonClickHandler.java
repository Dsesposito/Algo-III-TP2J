package fiuba.algo3.tp2.view.events.InitContainerEvents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AcceptButtonClickHandler implements EventHandler<ActionEvent> {

    Stage stage;
    Scene nextScene;

    public AcceptButtonClickHandler(Stage stage, Scene nextScene){
        this.stage = stage;
        this.nextScene = nextScene;
    }

    @Override
    public void handle(ActionEvent event) {
        stage.setScene(nextScene);
        stage.setFullScreen(true);
    }
}
