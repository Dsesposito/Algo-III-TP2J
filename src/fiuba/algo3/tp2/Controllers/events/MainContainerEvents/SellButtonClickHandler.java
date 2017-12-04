package fiuba.algo3.tp2.Controllers.events.MainContainerEvents;

import fiuba.algo3.tp2.Controllers.MainViewController;
import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Cells.Owneable;
import fiuba.algo3.tp2.view.MainContainer;
import fiuba.algo3.tp2.Controllers.events.Exceptions.SellChoiceBoxEmptyException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SellButtonClickHandler implements EventHandler<ActionEvent> {

    private final MainViewController controller;

    public SellButtonClickHandler(MainViewController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(ActionEvent event) {

        AlgoPoly algoPoly = AlgoPoly.getInstance();
        Boolean wasStoppedByBankruptcy = algoPoly.getActualPlayer().isStoppedByBankruptcy();

        Owneable selectedOwneable = controller.getSelectedOwneableToSell();

        if(selectedOwneable == null || !selectedOwneable.hasOwner()){
            algoPoly.logEvent("Para poder vender primero debe seleccionar una propiedad.");
            return;
        }

        selectedOwneable.sell();
        // Si el jugador estaba en bancarrota y ya posee dinero para saldar la deuda, entonces
        // la salda y se pasa de turno.
        if(wasStoppedByBankruptcy && algoPoly.getActualPlayer().hasEnoughMoneyToSolveDebt()){
            algoPoly.getActualPlayer().solveDebt();
            algoPoly.nextTurn();
            controller.setNewTurnState();
        }
        // De lo contrario debe seguir vendiendo.
        else{
            controller.updatePlayerInfo();
        }


    }
}
