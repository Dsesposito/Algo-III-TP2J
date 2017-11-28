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

        Turn actualTurn = algoPoly.getActualTurn();

        this.mainView.setDiceResultTF(actualTurn.getDiceResult().toString());
        this.mainView.toggleStateThrowDiceButton();

        algoPoly.movePlayer();

        Player currentPlayer = algoPoly.getActualPlayer();
        Cell landedCell = currentPlayer.getCurrentCell();

        mainView.printLine("El jugador " + currentPlayer.getName() + " cayó en " + landedCell.getName());

        Boolean isOwneable = landedCell.isOwneable();
        if(isOwneable){
            Owneable owneableCell = (Owneable)landedCell;
            if(!owneableCell.hasOwner()) {
                this.mainView.toggleStateBuyLandButton();
            }
            else{
                mainView.printLine("La propiedad le pertenece a " + owneableCell.getOwner().getName());
            }
        }
    }
}
