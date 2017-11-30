package fiuba.algo3.tp2.view.events.MainContainerEvents;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Cells.Owneable;
import fiuba.algo3.tp2.view.MainContainer;
import fiuba.algo3.tp2.view.events.Exceptions.SellChoiceBoxEmptyException;
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
        try{
            Owneable owneableToSell = (Owneable) algoPoly.getBoard().getCellByName(mainView.getSelectedSellOwneableCellName());
            owneableToSell.sell();

            if(wasStoppedByBankruptcy && algoPoly.getActualPlayer().hasEnoughMoneyToSolveDebt()){
                algoPoly.getActualPlayer().solveDebt();
                algoPoly.nextTurn();
                mainView.setNewTurnState();
            }
            else{
                mainView.updatePlayerInfo();
            }
        }
        catch (SellChoiceBoxEmptyException e){
            algoPoly.logEvent("Para poder vender primero debe seleccionar una propiedad.");
        }

    }
}
