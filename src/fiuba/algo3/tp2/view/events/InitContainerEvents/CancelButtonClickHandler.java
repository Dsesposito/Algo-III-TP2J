package fiuba.algo3.tp2.view.events.InitContainerEvents;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class CancelButtonClickHandler implements EventHandler<ActionEvent> {

    Stage stage;

    public CancelButtonClickHandler(Stage stage){
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent event) {
        stage.close();
    }
}
