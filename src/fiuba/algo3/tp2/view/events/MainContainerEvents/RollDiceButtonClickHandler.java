package fiuba.algo3.tp2.view.events.MainContainerEvents;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.view.MainContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class RollDiceButtonClickHandler implements EventHandler<ActionEvent> {

    MainContainer mainView;

    public RollDiceButtonClickHandler(MainContainer mainView){
        this.mainView = mainView;
    }

    @Override
    public void handle(ActionEvent event) {

        AlgoPoly algoPoly = AlgoPoly.getInstance();
        algoPoly.throwDice();
        algoPoly.movePlayer();

        mainView.printLine("El jugador " + algoPoly.getActualPlayer().getName() + " cayó en " + algoPoly.getActualPlayer().getCurrentCell().getName());

        this.mainView.setDiceResultTF(algoPoly.getActualTurn().getDiceResult().toString());
        this.mainView.disableThrowDiceButton();
    }
}
