package fiuba.algo3.tp2.Controllers.events.MainContainerEvents;

import fiuba.algo3.tp2.Controllers.MainViewController;
import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Cells.Neighborhood;
import fiuba.algo3.tp2.model.Exceptions.InsufficientFundsException;
import fiuba.algo3.tp2.view.MainContainer;
import fiuba.algo3.tp2.Controllers.events.Exceptions.BuildChoiceBoxEmptyException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BuildButtonClickHandler implements EventHandler<ActionEvent> {

    private final MainViewController controller;

    public BuildButtonClickHandler(MainViewController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(ActionEvent event) {

        AlgoPoly algoPoly = AlgoPoly.getInstance();

        Neighborhood selectedNeighborhood = controller.getSelectedNeighborhoodToBuild();

        if(selectedNeighborhood == null){
            algoPoly.logEvent("Para poder construir primero debe seleccionar una propiedad.");
            return;
        }

        try{
            if(selectedNeighborhood.hasAllHousesBuilt()){
                if(!selectedNeighborhood.hasHotelBuilt()){
                    selectedNeighborhood.buyHotel();
                }
                else{
                    algoPoly.logEvent("El jugador " + algoPoly.getActualPlayer().getName() + " ya no puede construir mas en el barrio " + selectedNeighborhood.getName());
                }
            }
            else{
                selectedNeighborhood.buyHouse();
            }
        }
        catch(InsufficientFundsException e){
            algoPoly.logEvent("El jugador " + algoPoly.getActualPlayer().getName() + " no posee los fondos suficientes para construir en " + selectedNeighborhood.getName());
        }
    }
}
