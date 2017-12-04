package fiuba.algo3.tp2.Controllers.events.MainContainerEvents;

import fiuba.algo3.tp2.Controllers.MainViewController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DiceThrownHandler implements PropertyChangeListener {

    private final MainViewController controller;

    public DiceThrownHandler(MainViewController mainViewController) {
        this.controller = mainViewController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.setDiceResult((String)evt.getNewValue());
    }
}
