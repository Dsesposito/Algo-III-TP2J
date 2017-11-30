package fiuba.algo3.tp2.view.events.MainContainerEvents;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Exceptions.InsufficientFundsException;
import fiuba.algo3.tp2.view.MainContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PayBailButtonClickHanlder implements EventHandler<ActionEvent> {

    private MainContainer mainView;

    public PayBailButtonClickHanlder(MainContainer mainContainer) {
        this.mainView = mainContainer;
    }

    @Override
    public void handle(ActionEvent event) {

        AlgoPoly algoPoly = AlgoPoly.getInstance();

        try{
            algoPoly.playerPayBail();
        }
        catch(InsufficientFundsException e){
            algoPoly.logEvent("El jugador " + algoPoly.getActualPlayer().getName() + " no posee los fondos suficientes para pagar la fianza.");
            algoPoly.nextTurn();
        }

        mainView.setNewTurnState();
    }
}
