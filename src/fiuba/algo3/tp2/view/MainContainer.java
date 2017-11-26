package fiuba.algo3.tp2.view;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    Stage stage;

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
        Label etiqueta = new Label();
        etiqueta.setText("consola...");
        etiqueta.setFont(Font.font("courier new", FontWeight.SEMI_BOLD, 14));
        etiqueta.setTextFill(Color.WHITE);

        VBox contenedorConsola = new VBox(etiqueta);
        contenedorConsola.setSpacing(10);
        contenedorConsola.setPadding(new Insets(15));
        contenedorConsola.setStyle("-fx-background-color: black;");

        this.setBottom(contenedorConsola);
    }

    private void setKeyPad(){

        VBox leftContainer = new VBox();

        leftContainer.setSpacing(10);
        leftContainer.setPadding(new Insets(15));

        Button rollDiceButton = new Button();
        rollDiceButton.setText("Arrojar dados");

        Button forwardButton = new Button();
        forwardButton.setText("Avanzar");

        String cssLayout = "-fx-border-color: #e2e0e0;-fx-border-insets: 5;-fx-border-width: 3;";
        leftContainer.setStyle(cssLayout);

        leftContainer.getChildren().addAll(rollDiceButton,forwardButton);

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

}
