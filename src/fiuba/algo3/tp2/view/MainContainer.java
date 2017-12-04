package fiuba.algo3.tp2.view;

import fiuba.algo3.tp2.Controllers.MainViewController;
import fiuba.algo3.tp2.model.*;
import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Cells.Jail;
import fiuba.algo3.tp2.model.Cells.Neighborhood;
import fiuba.algo3.tp2.model.Cells.Owneable;
import fiuba.algo3.tp2.Controllers.events.Exceptions.BuildChoiceBoxEmptyException;
import fiuba.algo3.tp2.Controllers.events.MainContainerEvents.BuildButtonClickHandler;
import fiuba.algo3.tp2.Controllers.events.Exceptions.SellChoiceBoxEmptyException;
import fiuba.algo3.tp2.Controllers.events.MainContainerEvents.*;
import fiuba.algo3.tp2.Controllers.events.MainContainerEvents.PayBailButtonClickHanlder;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainContainer extends BorderPane {

    private Stage stage;
    private MainViewController controller;
    private TextField actualPlayerTF;
    private TextField diceResultTF;
    private Button throwDiceButton;
    private Button sellButton;
    private Button buyButton;
    private Button buildPropertyButton;
    private Button passButton;
    private TextArea consoleTextArea;
    private TextField actualPlayerMoneyTF;
    private ChoiceBox sellChoiceBox;
    private ChoiceBox buildChoiceBox;
    private Button payBailButton;
    private Canvas canvas;
    private ChoiceBox propertiesChoiceBox;
    private TextField numHousesTF;
    private TextField numHotelsTF;
    private ObservableList<OwneableHelper> playerProperties;
    private ObservableList<Neighborhood> playerBuildableNeighborhoods;
    private ObservableList<Owneable> playerSalableCells;

    public MainContainer(Stage stage,MainViewController controller){

        this.stage = stage;
        this.controller = controller;
        this.playerProperties = FXCollections.observableArrayList();
        this.playerSalableCells = FXCollections.observableArrayList();
        this.playerBuildableNeighborhoods = FXCollections.observableArrayList();

        this.setBoard();
        this.setConsole();
        this.setKeyPad();
        this.setDescriptionBox();
    }

    private void setBoard(){

        Image image = this.readBackGroundImage();

        this.canvas = new Canvas(image.getWidth(),image.getHeight());

        VBox centralContainer = new VBox(canvas);

        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        centralContainer.setBackground(new Background(backgroundImage));

        super.setCenter(centralContainer);
    }

    private void setConsole(){

        TextArea textArea = new TextArea();
        textArea.setPrefHeight(210);
        textArea.setFont(Font.font("courier new", FontWeight.SEMI_BOLD, 16));
        this.consoleTextArea = textArea;

        VBox consoleContainer = new VBox(textArea);
        consoleContainer.setStyle("-fx-background-color: black;");
        consoleContainer.setPrefHeight(250);
        consoleContainer.setMaxHeight(250);

        this.setBottom(consoleContainer);
    }

    private void setDescriptionBox(){
        VBox rightContainer = new VBox();

        rightContainer.setSpacing(10);
        rightContainer.setPadding(new Insets(15));
        rightContainer.setMaxWidth(165);

        String cssLayout = "-fx-border-color: #e2e0e0;-fx-border-insets: 5;-fx-border-width: 3;";
        rightContainer.setStyle(cssLayout);

        Label actualPlayerLabel = new Label("Jugador actual :");
        TextField actualPlayerTF = new TextField ();
        actualPlayerTF.setEditable(false);
        VBox vbActualPlayer = new VBox();
        vbActualPlayer.getChildren().addAll(actualPlayerLabel, actualPlayerTF);
        vbActualPlayer.setSpacing(10);
        this.actualPlayerTF = actualPlayerTF;

        Label actualPlayerMoneyLabel = new Label("Dinero :");
        TextField actualPlayerMoneyTF = new TextField ();
        actualPlayerMoneyTF.setEditable(false);
        VBox vbActualPlayerMoney = new VBox();
        vbActualPlayerMoney.getChildren().addAll(actualPlayerMoneyLabel, actualPlayerMoneyTF);
        vbActualPlayerMoney.setSpacing(10);
        this.actualPlayerMoneyTF = actualPlayerMoneyTF;

        ChoiceBox propertiesChoiceBox = new ChoiceBox();
        propertiesChoiceBox.setMaxWidth(Double.MAX_VALUE);
        propertiesChoiceBox.setItems(this.playerProperties);
        propertiesChoiceBox.getSelectionModel().selectedItemProperty().addListener(new PropertySelectedHandler(this.controller));
        this.propertiesChoiceBox = propertiesChoiceBox;

        Label propertyHousesLabel = new Label("Casas :");
        TextField numHousesTF = new TextField ();
        numHousesTF.setEditable(false);
        VBox vbPropertyHouses = new VBox();
        vbPropertyHouses.getChildren().addAll(propertyHousesLabel, numHousesTF);
        vbPropertyHouses.setSpacing(10);
        this.numHousesTF = numHousesTF;

        Label propertyHotelsLabel = new Label("Hoteles :");
        TextField numHotelsTF = new TextField ();
        numHotelsTF.setEditable(false);
        VBox vbPropertyHotels = new VBox();
        vbPropertyHotels.getChildren().addAll(propertyHotelsLabel, numHotelsTF);
        vbPropertyHotels.setSpacing(10);
        this.numHotelsTF = numHotelsTF;

        rightContainer.getChildren().addAll(vbActualPlayer,vbActualPlayerMoney,propertiesChoiceBox,vbPropertyHouses,vbPropertyHotels);

        this.setRight(rightContainer);
    }

    private void setKeyPad(){

        VBox leftContainer = new VBox();

        leftContainer.setSpacing(10);
        leftContainer.setPadding(new Insets(15));
        leftContainer.setMaxWidth(165);

        String cssLayout = "-fx-border-color: #e2e0e0;-fx-border-insets: 5;-fx-border-width: 3;";
        leftContainer.setStyle(cssLayout);

        Button rollDiceButton = new Button();
        rollDiceButton.setMaxWidth(Double.MAX_VALUE);
        rollDiceButton.setText("Arrojar dados");
        rollDiceButton.setOnAction(new ThrowDiceButtonClickHandler(this));
        rollDiceButton.setDisable(true);
        this.throwDiceButton = rollDiceButton;

        Label diceResultLabel = new Label("Dados :");
        TextField diceResultTF = new TextField ();
        diceResultTF.setEditable(false);
        VBox vbDiceResult = new VBox();
        vbDiceResult.getChildren().addAll(diceResultLabel, diceResultTF);
        vbDiceResult.setSpacing(10);
        this.diceResultTF = diceResultTF;

        Button passButton = new Button();
        passButton.setMaxWidth(Double.MAX_VALUE);
        passButton.setText("Pasar turno");
        passButton.setDisable(true);
        passButton.setOnAction(new PassButtonClickHandler(this));
        this.passButton = passButton;

        Button buyButton = new Button();
        buyButton.setMaxWidth(Double.MAX_VALUE);
        buyButton.setText("Comprar");
        buyButton.setDisable(true);
        buyButton.setOnAction(new BuyLandButtonClickHandler(this));
        this.buyButton = buyButton;

        ChoiceBox buildPropertyChoiceBox = new ChoiceBox();
        buildPropertyChoiceBox.setDisable(true);
        buildPropertyChoiceBox.setMaxWidth(Double.MAX_VALUE);
        buildPropertyChoiceBox.setItems(this.playerBuildableNeighborhoods);
        this.buildChoiceBox = buildPropertyChoiceBox;

        Button buildButton = new Button();
        buildButton.setMaxWidth(Double.MAX_VALUE);
        buildButton.setText("Construir");
        buildButton.setDisable(true);
        buildButton.setOnAction(new BuildButtonClickHandler(this.controller));
        this.buildPropertyButton = buildButton;

        ChoiceBox sellChoiceBox = new ChoiceBox();
        sellChoiceBox.setMaxWidth(Double.MAX_VALUE);
        sellChoiceBox.setDisable(true);
        sellChoiceBox.setItems(this.playerSalableCells);
        this.sellChoiceBox = sellChoiceBox;

        Button sellButton = new Button();
        sellButton.setMaxWidth(Double.MAX_VALUE);
        sellButton.setText("Vender");
        sellButton.setDisable(true);
        sellButton.setOnAction(new SellButtonClickHandler(this.controller));
        this.sellButton = sellButton;

        Button payBailButton = new Button();
        payBailButton.setMaxWidth(Double.MAX_VALUE);
        payBailButton.setText("Pagar fianza");
        payBailButton.setDisable(true);
        payBailButton.setOnAction(new PayBailButtonClickHanlder(this));
        this.payBailButton = payBailButton;

        leftContainer.getChildren().addAll(rollDiceButton,vbDiceResult,passButton,buyButton,buildButton,buildPropertyChoiceBox,sellButton,sellChoiceBox,payBailButton);

        this.setLeft(leftContainer);
    }

    private Image readBackGroundImage(){
        File boardBackGroundFile = Paths.get("resources").resolve("Board.png").toFile();

        FileInputStream inputstream;
        try {
            inputstream = new FileInputStream(boardBackGroundFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Board background image not found", e);
        }

        return new Image(inputstream);
    }



    public void printLine(String message){
        this.consoleTextArea.appendText(message + "\n");
    }

    public void updatePlayerInfo(){

        this.updateSaleProperties();
        this.updateBuildProperties();

    }

    private void updateSaleProperties(){

        AlgoPoly algoPoly = AlgoPoly.getInstance();
        Player currentPlayer = algoPoly.getActualPlayer();

        if(currentPlayer.hasOwneableCells()){
            this.sellButton.setDisable(false);
            this.sellChoiceBox.setDisable(false);
        }
        else{
            this.sellButton.setDisable(true);
            this.sellChoiceBox.setDisable(true);
        }
    }

    private void updateBuildProperties(){

        AlgoPoly algoPoly = AlgoPoly.getInstance();
        Player currentPlayer = algoPoly.getActualPlayer();

        List<String> cellsNameWhereIsAbleToBuild = currentPlayer.getOwneableCells().stream()
                .filter(ownedCell -> ( (Cell)ownedCell).getGroup().isOwnedBySamePlayer(currentPlayer) && ownedCell.isNeighborhood() )
                .map(ownedCell -> ((Cell)ownedCell).getName())
                .collect(Collectors.toList());
        if(!cellsNameWhereIsAbleToBuild.isEmpty()){
            this.buildPropertyButton.setDisable(false);
            this.buildChoiceBox.setDisable(false);
        }
        else{
            this.buyButton.setDisable(true);
            this.buildChoiceBox.setDisable(true);
        }
    }

    public void setNewTurnState(){

        this.throwDiceButton.setDisable(false);
        this.sellButton.setDisable(true);
        this.sellChoiceBox.setDisable(true);
        this.buyButton.setDisable(true);
        this.buildChoiceBox.setDisable(true);
        this.passButton.setDisable(true);
        this.buildPropertyButton.setDisable(true);
        this.payBailButton.setDisable(true);

        AlgoPoly algoPoly = AlgoPoly.getInstance();
        Player currentPlayer = algoPoly.getActualPlayer();
        Jail jail = algoPoly.getBoard().getJail();
        if(jail.isPrisoner(currentPlayer) && !jail.isFreeToGo(currentPlayer) && jail.isAbleToPayBail(currentPlayer)){
            this.payBailButton.setDisable(false);
            this.passButton.setDisable(false);
        }

        this.updatePlayerInfo();
    }

    public void setPlayerPlayingState(){

        AlgoPoly algoPoly = AlgoPoly.getInstance();


        Player currentPlayer = algoPoly.getActualPlayer();
        Cell landedCell = currentPlayer.getCurrentCell();
        Boolean isOwneable = landedCell.isOwneable();
        if(isOwneable){
            Owneable owneableCell = (Owneable)landedCell;
            if(!owneableCell.hasOwner()) {
                this.buyButton.setDisable(false);
            }
        }

        this.passButton.setDisable(false);
        this.throwDiceButton.setDisable(true);
        this.payBailButton.setDisable(true);
        this.sellButton.setDisable(true);
        this.sellChoiceBox.setDisable(true);
        this.buildPropertyButton.setDisable(true);
        this.buildChoiceBox.setDisable(true);
    }

    public void setPlayerInBankruptcyState() {
        this.updateSaleProperties();
    }

    public void setAllDisabledState() {
        this.throwDiceButton.setDisable(false);
        this.sellButton.setDisable(true);
        this.sellChoiceBox.setDisable(true);
        this.buyButton.setDisable(true);
        this.buildChoiceBox.setDisable(true);
        this.passButton.setDisable(true);
        this.buildPropertyButton.setDisable(true);
        this.payBailButton.setDisable(true);
    }

    public void reDrawToken(Token token) {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Limpio la posicion anterior
        gc.clearRect(token.getPreviousPosition().getXpos() - 15,token.getPreviousPosition().getYpos() - 15, 30, 30);

        //Limpio la celda actual
        gc.clearRect(token.getPlayer().getCurrentCell().getPosition().getXpos() - 75,token.getPlayer().getCurrentCell().getPosition().getYpos() - 75, 100, 100);

        //Dibujo nuevamente en la celda actual
        Map<Player,Position> newPlayersPosition = token.distributePlayersInCell();
        for(Map.Entry<Player,Position> entry : newPlayersPosition.entrySet()){
            gc.setFill(entry.getKey().getToken().getColor());
            gc.fillOval(entry.getValue().getXpos()-15,entry.getValue().getYpos()-15, 30, 30);
        }

    }


    public void setPlayerNameAndMoney(String playerName,String playerMoney) {
        this.actualPlayerTF.setText(playerName);
        this.actualPlayerMoneyTF.setText(playerMoney);
    }

    public void setDiceResult(String diceResult) {
        this.diceResultTF.setText(diceResult);
    }

    public void setPlayerProperties(List<OwneableHelper> playerProperties){
        this.playerProperties.clear();
        this.playerProperties.addAll(playerProperties);
        this.numHotelsTF.setText("");
        this.numHousesTF.setText("");
    }

    public void setNumHousesAndHotels(OwneableHelper selectedOwneable) {
        if(selectedOwneable != null && selectedOwneable.isBuildable()){
            this.numHotelsTF.setText(selectedOwneable.getNumHotels().toString());
            this.numHousesTF.setText(selectedOwneable.getNumHouses().toString());
        }
        else{
            this.numHotelsTF.setText("-");
            this.numHousesTF.setText("-");
        }
    }

    public void setPlayerBuildableNeighborhoods(List<Neighborhood> playerBuildableNeighborhoods) {
        this.playerBuildableNeighborhoods.clear();
        this.playerBuildableNeighborhoods.addAll(playerBuildableNeighborhoods);
        this.buildChoiceBox.getSelectionModel().clearSelection();
    }

    public void setPlayerSalableCells(List<Owneable> playerSalableCells) {
        this.playerSalableCells.clear();
        this.playerSalableCells.addAll(playerSalableCells);
        this.sellChoiceBox.getSelectionModel().clearSelection();
    }

    public Neighborhood getSelectedNeighborhoodToBuild() {
        return (Neighborhood) this.buildChoiceBox.getSelectionModel().getSelectedItem();
    }

    public Owneable getSelectedOwneableToSell() {
        return (Owneable) this.sellChoiceBox.getSelectionModel().getSelectedItem();
    }
}
