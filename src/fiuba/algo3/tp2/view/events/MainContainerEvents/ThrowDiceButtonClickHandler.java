package fiuba.algo3.tp2.view.events.MainContainerEvents;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Cells.Groupable;
import fiuba.algo3.tp2.model.Cells.Owneable;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;
import fiuba.algo3.tp2.view.MainContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ThrowDiceButtonClickHandler implements EventHandler<ActionEvent> {

    MainContainer mainView;

    public ThrowDiceButtonClickHandler(MainContainer mainView){
        this.mainView = mainView;
    }

    @Override
    public void handle(ActionEvent event) {

        AlgoPoly algoPoly = AlgoPoly.getInstance();
        algoPoly.throwDice();

        algoPoly.movePlayer();

        mainView.setPlayerPlayingState();

        Player currentPlayer = algoPoly.getActualPlayer();
        Cell landedCell = currentPlayer.getCurrentCell();

        Boolean isOwneable = landedCell.isOwneable();
        if(!isOwneable){
            algoPoly.nextTurn();
            this.mainView.setNewTurnState();
        }
        else{
            Owneable owneableCell = (Owneable)landedCell;
            if(owneableCell.hasOwner()) {
                algoPoly.nextTurn();
                this.mainView.setNewTurnState();
            }
        }

    }
}
