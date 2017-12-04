package fiuba.algo3.tp2.Controllers.events.MainContainerEvents;

import fiuba.algo3.tp2.Controllers.MainViewController;
import fiuba.algo3.tp2.view.OwneableHelper;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class PropertySelectedHandler implements ChangeListener {
    private final MainViewController controller;

    public PropertySelectedHandler(MainViewController controller) {
        this.controller = controller;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        OwneableHelper selectedOwneable = (OwneableHelper)newValue;
        this.controller.setNumHousesAndHotels(selectedOwneable);
    }
}
