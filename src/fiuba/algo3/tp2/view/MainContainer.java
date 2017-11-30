package fiuba.algo3.tp2.view;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Cells.Owneable;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;
import fiuba.algo3.tp2.view.events.Exceptions.BuildChoiceBoxEmptyException;
import fiuba.algo3.tp2.view.events.MainContainerEvents.BuildButtonClickHandler;
import fiuba.algo3.tp2.view.events.Exceptions.SellChoiceBoxEmptyException;
import fiuba.algo3.tp2.view.events.MainContainerEvents.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainContainer extends BorderPane {

    private Stage stage;
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

    public MainContainer(Stage stage){

        this.stage = stage;

        this.setMenu();
        this.setBoard();
        this.setConsole();
        this.setKeyPad();
    }

    private void setMenu(){
        MenuBar menuBar = new MenuBar(stage);
        this.setTop(menuBar);
    }

    private void setBoard(){

        Image image = this.readBackGroundImage();

        Canvas canvas = new Canvas(image.getWidth(),image.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.DARKGREEN);
        gc.fillOval(990,530, 25, 25);

        gc.setFill(Color.BLUE);
        gc.fillOval(990,580, 25, 25);

        gc.setFill(Color.RED);
        gc.fillOval(1040,555, 25, 25);

        VBox centralContainer = new VBox(canvas);

        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        centralContainer.setBackground(new Background(backgroundImage));



        super.setCenter(centralContainer);
    }

    private void setConsole(){

        TextArea textArea = new TextArea();
        textArea.setPrefHeight(140);
        textArea.setFont(Font.font("courier new", FontWeight.SEMI_BOLD, 16));
        this.consoleTextArea = textArea;

        VBox consoleContainer = new VBox(textArea);
        consoleContainer.setStyle("-fx-background-color: black;");
        consoleContainer.setPrefHeight(200);
        consoleContainer.setMaxHeight(200);

        this.setBottom(consoleContainer);
    }

    private void setKeyPad(){

        VBox leftContainer = new VBox();

        leftContainer.setSpacing(10);
        leftContainer.setPadding(new Insets(15));

        Button rollDiceButton = new Button();
        rollDiceButton.setText("Arrojar dados");
        rollDiceButton.setOnAction(new ThrowDiceButtonClickHandler(this));
        rollDiceButton.setDisable(true);
        this.throwDiceButton = rollDiceButton;

        Label actualPlayerLabel = new Label("Jugador actual :");
        TextField actualPlayerTF = new TextField ();
        VBox vbActualPlayer = new VBox();
        vbActualPlayer.getChildren().addAll(actualPlayerLabel, actualPlayerTF);
        vbActualPlayer.setSpacing(10);
        this.actualPlayerTF = actualPlayerTF;

        Label actualPlayerMoneyLabel = new Label("Dinero :");
        TextField actualPlayerMoneyTF = new TextField ();
        VBox vbActualPlayerMoney = new VBox();
        vbActualPlayerMoney.getChildren().addAll(actualPlayerMoneyLabel, actualPlayerMoneyTF);
        vbActualPlayerMoney.setSpacing(10);
        this.actualPlayerMoneyTF = actualPlayerMoneyTF;

        Label diceResultLabel = new Label("Resultado de los dados :");
        TextField diceResultTF = new TextField ();
        VBox vbDiceResult = new VBox();
        vbDiceResult.getChildren().addAll(diceResultLabel, diceResultTF);
        vbDiceResult.setSpacing(10);
        this.diceResultTF = diceResultTF;

        Button passButton = new Button();
        passButton.setText("Pasar turno");
        passButton.setDisable(true);
        passButton.setOnAction(new PassButtonClickHandler(this));
        this.passButton = passButton;

        Button buyButton = new Button();
        buyButton.setText("Comprar");
        buyButton.setDisable(true);
        buyButton.setOnAction(new BuyLandButtonClickHandler(this));
        this.buyButton = buyButton;

        Button buildButton = new Button();
        buildButton.setText("Construir");
        buildButton.setDisable(true);
        buildButton.setOnAction(new BuildButtonClickHandler(this));
        this.buildPropertyButton = buildButton;

        ChoiceBox buildPropertyChoiceBox = new ChoiceBox();
        buildPropertyChoiceBox.setDisable(true);
        this.buildChoiceBox = buildPropertyChoiceBox;

        Button sellButton = new Button();
        sellButton.setText("Vender");
        sellButton.setDisable(true);
        sellButton.setOnAction(new SellButtonClickHandler(this));
        this.sellButton = sellButton;

        ChoiceBox sellLandChoiceBox = new ChoiceBox();
        sellLandChoiceBox.setDisable(true);
        this.sellChoiceBox = sellLandChoiceBox;

        String cssLayout = "-fx-border-color: #e2e0e0;-fx-border-insets: 5;-fx-border-width: 3;";
        leftContainer.setStyle(cssLayout);

        leftContainer.getChildren().addAll(rollDiceButton,vbDiceResult,vbActualPlayer,vbActualPlayerMoney,passButton,buyButton,buildButton,buildPropertyChoiceBox,sellButton,sellLandChoiceBox);

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

    public void showScene(InitContainer previousView){

        AlgoPoly algoPoly = AlgoPoly.getInstance();

        algoPoly.getConsole().addMessageObserver(new NewConsoleMessageHandler(this));

        algoPoly.logEvent("Bienvenido a AlgoPoly");

        for(String playerName : previousView.getPlayersName()){
            algoPoly.addPlayerToGame(playerName);
            algoPoly.logEvent("EL juegador " + playerName + " se ha unido a la partida");
        }

        algoPoly.startGame();

        this.setNewTurnState();

        Scene playScene = new Scene(this,1408,792);
        stage.setScene(playScene);
    }

    public void printLine(String message){
        this.consoleTextArea.appendText(message + "\n");
    }

    public void updatePlayerInfo(){

        AlgoPoly algoPoly = AlgoPoly.getInstance();
        Player currentPlayer = algoPoly.getActualPlayer();

        this.actualPlayerTF.setText(currentPlayer.getName());
        this.actualPlayerMoneyTF.setText(currentPlayer.getMoney().getValue().toString());

        this.updateSaleProperties();
        this.updateBuildProperties();

    }

    private void updateSaleProperties(){

        AlgoPoly algoPoly = AlgoPoly.getInstance();
        Player currentPlayer = algoPoly.getActualPlayer();

        if(currentPlayer.hasOwneableCells()){

            List<String> ownedCellsName = currentPlayer.getOwneableCells().stream()
                    .map(owneable -> ((Cell)owneable).getName())
                    .collect(Collectors.toList());

            this.sellButton.setDisable(false);
            this.sellChoiceBox.setDisable(false);
            this.sellChoiceBox.setItems(FXCollections.observableArrayList(ownedCellsName));

        }
        else{
            this.sellButton.setDisable(true);
            this.sellChoiceBox.setDisable(true);
            this.sellChoiceBox.setItems(FXCollections.observableArrayList(new ArrayList<>()));
        }
    }

    private void updateBuildProperties(){

        AlgoPoly algoPoly = AlgoPoly.getInstance();
        Player currentPlayer = algoPoly.getActualPlayer();

        List<String> cellsNameWhereIsAbleToBuild = currentPlayer.getOwneableCells().stream()
                .filter(ownedCell -> ((Cell)ownedCell).getGroup().isOwnedBySamePlayer(currentPlayer))
                .map(ownedCell -> ((Cell)ownedCell).getName())
                .collect(Collectors.toList());
        if(!cellsNameWhereIsAbleToBuild.isEmpty()){
            this.buildPropertyButton.setDisable(false);
            this.buildChoiceBox.setDisable(false);
            this.buildChoiceBox.setItems(FXCollections.observableArrayList(cellsNameWhereIsAbleToBuild));
        }
        else{
            this.buyButton.setDisable(true);
            this.buildChoiceBox.setDisable(true);
            this.buildChoiceBox.setItems(FXCollections.observableArrayList(new ArrayList<>()));
        }
    }

    public void setNewTurnState(){

        this.throwDiceButton.setDisable(false);
        this.sellButton.setDisable(true);
        this.sellChoiceBox.setDisable(true);
        this.sellChoiceBox.setItems(FXCollections.observableArrayList(new ArrayList<>()));
        this.buyButton.setDisable(true);
        this.buildChoiceBox.setDisable(true);
        this.buildChoiceBox.setItems(FXCollections.observableArrayList(new ArrayList<>()));
        this.passButton.setDisable(true);
        this.buildPropertyButton.setDisable(true);
        this.diceResultTF.setText("");
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

        Turn actualTurn = algoPoly.getActualTurn();

        this.diceResultTF.setText(actualTurn.getDiceResult().toString());
        this.passButton.setDisable(false);
        this.throwDiceButton.setDisable(true);
        this.sellButton.setDisable(true);
        this.sellChoiceBox.setDisable(true);

    }

    public String getSelectedSellOwneableCellName(){
        if(this.sellChoiceBox.getValue() == null){
            throw new SellChoiceBoxEmptyException("In order to sell, a property must be selected.");
        }
        return (String)this.sellChoiceBox.getValue();
    }

    public String getSelectedBuildNeighborhoodName(){
        if(this.buildChoiceBox.getValue() == null){
            throw new BuildChoiceBoxEmptyException("In order to build, a neighborhood must be selected.");
        }
        return (String)this.buildChoiceBox.getValue();
    }

    public void setPlayerInBankruptcyState() {
        this.updateSaleProperties();
    }
}
