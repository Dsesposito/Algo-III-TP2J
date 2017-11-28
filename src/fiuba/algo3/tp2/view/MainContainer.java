package fiuba.algo3.tp2.view;

import fiuba.algo3.tp2.model.AlgoPoly;
import fiuba.algo3.tp2.view.events.MainContainerEvents.BuyLandButtonClickHandler;
import fiuba.algo3.tp2.view.events.MainContainerEvents.ThrowDiceButtonClickHandler;
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

public class MainContainer extends BorderPane {

    private Stage stage;
    private TextField actualPlayerTF;
    private TextField diceResultTF;
    private Button throwDiceButton;
    private Button sellLandButton;
    private Button buyLandButton;
    private Button buildPropertyButton;
    private TextArea consoleTextArea;
    private TextField actualPlayerMoneyTF;

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
        textArea.setFont(Font.font("courier new", FontWeight.SEMI_BOLD, 20));
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

        Button buyButton = new Button();
        buyButton.setText("Comprar");
        buyButton.setDisable(true);
        buyButton.setOnAction(new BuyLandButtonClickHandler(this));
        this.buyLandButton = buyButton;

        Button buildButton = new Button();
        buildButton.setText("Construir");
        buildButton.setDisable(true);
        this.buildPropertyButton = buildButton;

        ChoiceBox buildPropertyChoiceBox = new ChoiceBox();
        buildPropertyChoiceBox.setDisable(true);

        Button sellButton = new Button();
        sellButton.setText("Vender");
        sellButton.setDisable(true);
        this.sellLandButton = sellButton;

        ChoiceBox sellLandChoiceBox = new ChoiceBox();
        sellLandChoiceBox.setDisable(true);

        String cssLayout = "-fx-border-color: #e2e0e0;-fx-border-insets: 5;-fx-border-width: 3;";
        leftContainer.setStyle(cssLayout);

        leftContainer.getChildren().addAll(rollDiceButton,vbDiceResult,vbActualPlayer,vbActualPlayerMoney,buyButton,buildButton,buildPropertyChoiceBox,sellButton,sellLandChoiceBox);

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

        for(String playerName : previousView.getPlayersName()){
            algoPoly.addPlayerToGame(playerName);
        }

        algoPoly.startGame();

        this.actualPlayerTF.setText(algoPoly.getActualPlayer().getName());
        this.actualPlayerMoneyTF.setText(algoPoly.getActualPlayer().getMoney().getValue().toString());

        Scene playScene = new Scene(this,1408,792);
        stage.setScene(playScene);
    }

    public void setDiceResultTF(String diceResult){
        this.diceResultTF.setText(diceResult);
    }

    public void updatePlayerMoney(String value){
        this.actualPlayerMoneyTF.setText(value);
    }

    public void updatePlayerName(String name){
        this.actualPlayerTF.setText(name);
    }

    public void toggleStateThrowDiceButton(){
        this.throwDiceButton.setDisable(!this.throwDiceButton.isDisabled());
    }

    public void toggleStateBuildPropertyButton(){
        this.buildPropertyButton.setDisable(!this.buildPropertyButton.isDisabled());
    }

    public void toggleStateBuyLandButton(){
        this.buyLandButton.setDisable(!this.buyLandButton.isDisabled());
    }

    public void toggleStateSellButton(){
        this.sellLandButton.setDisable(!this.sellLandButton.isDisabled());
    }

    public void printLine(String message){
        this.consoleTextArea.appendText(message + "\n");
    }

    public void clearDiceResult() {
        this.diceResultTF.setText("");
    }
}
