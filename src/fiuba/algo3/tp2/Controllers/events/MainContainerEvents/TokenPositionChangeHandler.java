package fiuba.algo3.tp2.Controllers.events.MainContainerEvents;

import fiuba.algo3.tp2.model.Token;
import fiuba.algo3.tp2.view.MainContainer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class TokenPositionChangeHandler implements ChangeListener {

    private MainContainer mainView;
    private Token token;

    public TokenPositionChangeHandler(MainContainer mainContainer,Token token) {
        this.mainView = mainContainer;
        this.token = token;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        this.mainView.reDrawToken(token);
    }
}
