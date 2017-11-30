package fiuba.algo3.tp2.view.events.MainContainerEvents;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.view.MainContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PassButtonClickHandler implements EventHandler<ActionEvent> {

    private MainContainer mainView;

    public PassButtonClickHandler(MainContainer mainView){
        this.mainView = mainView;
    }

    @Override
    public void handle(ActionEvent event) {

        AlgoPoly algoPoly = AlgoPoly.getInstance();

        algoPoly.logEvent("El jugador " + algoPoly.getActualPlayer().getName() + " paso su turno.");

        algoPoly.nextTurn();

        mainView.setNewTurnState();
    }
}
