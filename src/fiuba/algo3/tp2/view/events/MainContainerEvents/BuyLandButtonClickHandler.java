package fiuba.algo3.tp2.view.events.MainContainerEvents;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Cells.Owneable;
import fiuba.algo3.tp2.model.Exceptions.InsufficientFundsException;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.view.MainContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BuyLandButtonClickHandler implements EventHandler<ActionEvent> {

    MainContainer mainView;

    public BuyLandButtonClickHandler(MainContainer mainView){
        this.mainView = mainView;
    }

    @Override
    public void handle(ActionEvent event) {

        AlgoPoly algoPoly = AlgoPoly.getInstance();

        Player currentPlayer = algoPoly.getActualPlayer();

        Owneable owneableCell = (Owneable)currentPlayer.getCurrentCell();

        try{
            owneableCell.buy(currentPlayer);
            this.mainView.updatePlayerInfo();
        }
        catch (InsufficientFundsException e){
            algoPoly.logEvent("El jugador " + currentPlayer.getName() + " no posee los fondos suficientes para comprar.");
        }

        algoPoly.nextTurn();
        this.mainView.setNewTurnState();
    }
}
