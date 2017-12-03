package fiuba.algo3.tp2.Controllers.events.MainContainerEvents;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Cells.Owneable;
import fiuba.algo3.tp2.view.MainContainer;
import fiuba.algo3.tp2.Controllers.events.Exceptions.SellChoiceBoxEmptyException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SellButtonClickHandler implements EventHandler<ActionEvent> {
    private final MainContainer mainView;

    public SellButtonClickHandler(MainContainer mainContainer) {
        this.mainView = mainContainer;
    }

    @Override
    public void handle(ActionEvent event) {

        AlgoPoly algoPoly = AlgoPoly.getInstance();
        Boolean wasStoppedByBankruptcy = algoPoly.getActualPlayer().isStoppedByBankruptcy();
        Owneable owneableToSell;
        try{
            owneableToSell = (Owneable) algoPoly.getBoard().getCellByName(mainView.getSelectedSellOwneableCellName());
        }
        catch (SellChoiceBoxEmptyException e){
            algoPoly.logEvent("Para poder vender primero debe seleccionar una propiedad.");
            return;
        }

        owneableToSell.sell();
        // Si el jugador estaba en bancarrota y ya posee dinero para saldar la deuda, entonces
        // la salda y se pasa de turno.
        if(wasStoppedByBankruptcy && algoPoly.getActualPlayer().hasEnoughMoneyToSolveDebt()){
            algoPoly.getActualPlayer().solveDebt();
            algoPoly.nextTurn();
            mainView.setNewTurnState();
        }
        // De lo contrario debe seguir vendiendo.
        else{
            mainView.updatePlayerInfo();
        }


    }
}
