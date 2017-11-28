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
            this.mainView.toggleStateBuyLandButton();
            mainView.updatePlayerMoney(currentPlayer.getMoney().getValue().toString());
            mainView.printLine("El jugador " + currentPlayer.getName() + " compró " + currentPlayer.getCurrentCell().getName());
        }
        catch (InsufficientFundsException e){
            mainView.printLine("El jugador " + currentPlayer.getName() + " no posee los fondos suficientes para comprar.");
        }

        mainView.clearDiceResult();

        algoPoly.nextTurn();

        mainView.printLine("Turno del jugador " + algoPoly.getActualPlayer().getName());

        this.mainView.updatePlayerName(algoPoly.getActualPlayer().getName());
        this.mainView.updatePlayerMoney(algoPoly.getActualPlayer().getMoney().getValue().toString());
        this.mainView.toggleStateThrowDiceButton();
    }
}
