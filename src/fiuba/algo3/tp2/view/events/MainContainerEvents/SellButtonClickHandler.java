package fiuba.algo3.tp2.view.events.MainContainerEvents;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Cells.Owneable;
import fiuba.algo3.tp2.view.MainContainer;
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
        Owneable owneableToSell = (Owneable) algoPoly.getBoard().getCellByName(mainView.getSelectedSellOwneableCellName());
        owneableToSell.sell();
        mainView.updatePlayerInfo();
    }
}
