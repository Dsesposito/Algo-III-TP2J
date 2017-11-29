package fiuba.algo3.tp2.view.events.InitContainerEvents;


import fiuba.algo3.tp2.view.InitContainer;
import fiuba.algo3.tp2.view.MainContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AcceptButtonClickHandler implements EventHandler<ActionEvent> {

    MainContainer nextView;
    InitContainer currentView;

    public AcceptButtonClickHandler(InitContainer currentView, MainContainer nextView){
        this.nextView = nextView;
        this.currentView = currentView;
    }

    @Override
    public void handle(ActionEvent event) {
        nextView.showScene(currentView);
    }
}
