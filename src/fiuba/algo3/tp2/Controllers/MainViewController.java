package fiuba.algo3.tp2.Controllers;

import fiuba.algo3.tp2.Controllers.events.MainContainerEvents.DiceThrownHandler;
import fiuba.algo3.tp2.Controllers.events.MainContainerEvents.NewConsoleMessageHandler;
import fiuba.algo3.tp2.Controllers.events.MainContainerEvents.PlayerChangeHandler;
import fiuba.algo3.tp2.Controllers.events.MainContainerEvents.TokenPositionChangeHandler;
import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Cells.Neighborhood;
import fiuba.algo3.tp2.model.Cells.Owneable;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Token;
import fiuba.algo3.tp2.view.MainContainer;
import fiuba.algo3.tp2.view.OwneableHelper;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainViewController {

    private MainContainer view;
    private Stage stage;

    public MainViewController(Stage stage,List<String> playersName) {
        this.stage=stage;
        this.view = new MainContainer(stage,this);
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

        algoPoly.addPropertyChangeListener(AlgoPoly.ListenerTurnProperty.PLAYER,new PlayerChangeHandler(this));
        algoPoly.addPropertyChangeListener(AlgoPoly.ListenerTurnProperty.DICE,new DiceThrownHandler(this));

        algoPoly.startGame();

        view.setNewTurnState();

        Scene playScene = new Scene(view,1280,740);
        stage.setScene(playScene)   ;
    }


    public void changePlayer(Player player) {
        //Establece nombre de jugador y dinero
        this.view.setPlayerNameAndMoney(player.getName(),player.getMoney().toString());

        //Establece propiedades del jugador
        List<OwneableHelper> owneableHelpers = new ArrayList<>();
        for(Owneable owneable : player.getOwneableCells()){
            if(owneable.isNeighborhood()){
                Neighborhood neighborhood = (Neighborhood)owneable;
                owneableHelpers.add(new OwneableHelper(neighborhood.getName(),true,neighborhood.getNumberOfBuiltHouses(),neighborhood.getNumberOfBuiltHotels()));
            }
            else{
                owneableHelpers.add(new OwneableHelper(((Cell)owneable).getName(),false));
            }
        }
        this.view.setPlayerProperties(owneableHelpers);

        //Establece propiedades donde el jugador puede construir
        List<Neighborhood> buildableNeighborhoods = player.getOwneableCells().stream()
                .filter(ownedCell -> ( (Cell)ownedCell).getGroup().isOwnedBySamePlayer(player) && ownedCell.isNeighborhood() )
                .map(ownedCell -> ((Neighborhood)ownedCell))
                .collect(Collectors.toList());
        this.view.setPlayerBuildableNeighborhoods(buildableNeighborhoods);

        //Establece propiedades que el jugador puede vender
        List<Owneable> ownedCells = player.getOwneableCells();
        this.view.setPlayerSalableCells(ownedCells);

    }

    public void setDiceResult(String diceResult) {
        this.view.setDiceResult(diceResult);
    }

    public void setNumHousesAndHotels(OwneableHelper numHousesAndHotels) {
        this.view.setNumHousesAndHotels(numHousesAndHotels);
    }

    public void setNewTurnState() {
        this.view.setNewTurnState();
    }

    public void updatePlayerInfo() {
        this.view.updatePlayerInfo();
    }

    public Neighborhood getSelectedNeighborhoodToBuild() {
        return this.view.getSelectedNeighborhoodToBuild();
    }

    public Owneable getSelectedOwneableToSell(){
        return this.view.getSelectedOwneableToSell();
    }
}
