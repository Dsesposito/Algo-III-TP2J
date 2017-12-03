package fiuba.algo3.tp2.Controllers;

import fiuba.algo3.tp2.Controllers.events.MainContainerEvents.NewConsoleMessageHandler;
import fiuba.algo3.tp2.Controllers.events.MainContainerEvents.TokenPositionChangeHandler;
import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Token;
import fiuba.algo3.tp2.view.MainContainer;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class MainViewController {

    private MainContainer view;
    private Stage stage;

    public MainViewController(Stage stage,List<String> playersName) {
        this.stage=stage;
        this.view = new MainContainer(stage);
        this.showScene(playersName);
    }

    public void showScene(List<String> playersName){

        AlgoPoly algoPoly = AlgoPoly.getInstance();

        algoPoly.getConsole().addMessageObserver(new NewConsoleMessageHandler(this.view));

        algoPoly.logEvent("Bienvenido a AlgoPoly");

        for(String playerName : playersName){
            Token token = new Token();
            token.addPositionLisetener(new TokenPositionChangeHandler(this.view,token));
            algoPoly.addPlayerToGame(playerName,token);
            algoPoly.logEvent("El jugador " + playerName + " se ha unido a la partida");
        }

        algoPoly.startGame();

        view.setNewTurnState();

        Scene playScene = new Scene(view,1408,765);
        stage.setScene(playScene);
    }
}
