package fiuba.algo3.tp2.Controllers.events.InitContainerEvents;


import fiuba.algo3.tp2.Controllers.MainViewController;
import fiuba.algo3.tp2.view.InitContainer;
import fiuba.algo3.tp2.view.MainContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class AcceptButtonClickHandler implements EventHandler<ActionEvent> {

    Stage stage;
    InitContainer currentView;

    public AcceptButtonClickHandler(Stage stage,InitContainer currentView){
        this.currentView = currentView;
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent event) {
        new MainViewController(this.stage,currentView.getPlayersName());
    }
}
