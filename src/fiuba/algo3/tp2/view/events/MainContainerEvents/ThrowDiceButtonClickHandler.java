package fiuba.algo3.tp2.view.events.MainContainerEvents;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Cells.Groupable;
import fiuba.algo3.tp2.model.Cells.Owneable;
import fiuba.algo3.tp2.model.Exceptions.InsufficientFundsException;
import fiuba.algo3.tp2.model.Money;
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

        Player currentPlayer = algoPoly.getActualPlayer();

        algoPoly.throwDice();

        algoPoly.movePlayer();


        //Configuracion de botones correspondientes a acciones hacibles cuando un jugador esta jugando
        mainView.setPlayerPlayingState();

        //Comprueba que el jugador no este en quiebra.
        if(currentPlayer.isStoppedByBankruptcy()){
            // Si lo esta y puede vender se habilita la posibilidad de vender
            if(currentPlayer.hasPropertiesToSell() && !currentPlayer.isDefeted()){
                mainView.setPlayerInBankruptcyState();
                return;
            }
            // Si no puede vender o no le alcanza vendiendo se lo considera derrotado.
            else{
                algoPoly.playerHasBeenDefeated();
                // Si todos los jugadores fueron derrotados termina la partida. De lo contrario
                // juega el proximo jugador
                if(algoPoly.allOponentsHasBeenDefeated()){
                    mainView.setNewTurnState();
                    return;
                }
                else{
                    mainView.setAllDisabledState();
                    return;
                }
            }

        }

        //Chequeo de propiedad del barrio.
        Cell landedCell = currentPlayer.getCurrentCell();

        //Si no es comprable , se pasa al siguiente turno
        Boolean isOwneable = landedCell.isOwneable();
        if(!isOwneable){
            algoPoly.nextTurn();
            this.mainView.setNewTurnState();
        }
        //Si es comprable y tiene due;o se pasa al siguiente turno
        else{
            Owneable owneableCell = (Owneable)landedCell;
            if(owneableCell.hasOwner()) {
                algoPoly.nextTurn();
                this.mainView.setNewTurnState();
            }
        }

    }
}
