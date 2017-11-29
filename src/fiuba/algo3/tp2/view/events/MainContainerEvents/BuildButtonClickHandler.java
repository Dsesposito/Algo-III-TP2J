package fiuba.algo3.tp2.view.events.MainContainerEvents;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Cells.Neighborhood;
import fiuba.algo3.tp2.model.Cells.Owneable;
import fiuba.algo3.tp2.model.Exceptions.InsufficientFundsException;
import fiuba.algo3.tp2.view.MainContainer;
import fiuba.algo3.tp2.view.events.Exceptions.BuildChoiceBoxEmptyException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BuildButtonClickHandler implements EventHandler<ActionEvent> {

    private final MainContainer mainView;

    public BuildButtonClickHandler(MainContainer mainContainer) {
        this.mainView = mainContainer;
    }

    @Override
    public void handle(ActionEvent event) {

        AlgoPoly algoPoly = AlgoPoly.getInstance();

        Neighborhood neighborhood;

        try{
            neighborhood = algoPoly.getBoard().getNeighborhoodByName(mainView.getSelectedBuildNeighborhoodName());
        }
        catch (BuildChoiceBoxEmptyException e){
            algoPoly.logEvent("Para poder construir primero debe seleccionar una propiedad.");
            return;
        }

        try{
            if(neighborhood.hasAllHousesBuilt()){
                if(!neighborhood.hasHotelBuilt()){
                    neighborhood.buyHotel();
                }
                else{
                    algoPoly.logEvent("El jugador " + algoPoly.getActualPlayer().getName() + " ya no puede construir mas en el barrio " + neighborhood.getName());
                }
            }
            else{
                neighborhood.buyHouse();
            }
        }
        catch(InsufficientFundsException e){
            algoPoly.logEvent("El jugador " + algoPoly.getActualPlayer().getName() + " no posee los fondos suficientes para construir en");
        }
    }
}
