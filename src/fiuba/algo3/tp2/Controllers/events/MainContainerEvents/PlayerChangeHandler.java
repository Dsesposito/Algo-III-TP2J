package fiuba.algo3.tp2.Controllers.events.MainContainerEvents;

import fiuba.algo3.tp2.Controllers.MainViewController;
import fiuba.algo3.tp2.model.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerChangeHandler implements PropertyChangeListener {

    private final MainViewController controller;

    public PlayerChangeHandler(MainViewController mainViewController) {
        this.controller = mainViewController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.controller.changePlayer((Player)evt.getNewValue());
    }
}
