package fiuba.algo3.tp2.view;

import fiuba.algo3.tp2.Controllers.events.InitContainerEvents.AcceptButtonClickHandler;
import fiuba.algo3.tp2.Controllers.events.InitContainerEvents.CancelButtonClickHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InitContainer extends VBox {

    private Stage stage;
    private List<TextField> playersName;

    public InitContainer(Stage stage){

        super();

        this.stage = stage;
        this.playersName = new ArrayList<>();

        this.setSpacing(20);
        this.setPadding(new Insets(25));

        this.createLabels();

        this.createPlayersOptions();

        this.createButtons();
    }

    private void createLabels(){

        Label welcomLabel = new Label();
        welcomLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
        welcomLabel.setText("Bienvenidos a AlgoPoly");
        welcomLabel.setTextFill(Color.web("#66A7C5"));

        Label optionsLabel = new Label();
        optionsLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
        optionsLabel.setText("Complete las siguientes opciones para continuar");
        optionsLabel.setTextFill(Color.web("#66A7C5"));

        this.getChildren().addAll(welcomLabel,optionsLabel);
    }

    private void createPlayersOptions(){

        Label player1 = new Label("Jugador 1 :");
        TextField textFieldP1 = new TextField ();
        HBox hbP1 = new HBox();
        hbP1.getChildren().addAll(player1, textFieldP1);
        hbP1.setSpacing(10);
        this.playersName.add(textFieldP1);

        Label player2 = new Label("Jugador 2 :");
        TextField textFieldP2 = new TextField ();
        HBox hbP2 = new HBox();
        hbP2.getChildren().addAll(player2, textFieldP2);
        hbP2.setSpacing(10);
        this.playersName.add(textFieldP2);

        Label player3 = new Label("Jugador 3 :");
        TextField textFieldP3 = new TextField ();
        HBox hbP3 = new HBox();
        hbP3.getChildren().addAll(player3, textFieldP3);
        hbP3.setSpacing(10);
        this.playersName.add(textFieldP3);

        VBox vbPlayersName = new VBox();
        vbPlayersName.setSpacing(20);
        vbPlayersName.getChildren().addAll(hbP1,hbP2,hbP3);

        this.getChildren().add(vbPlayersName);

    }

    private void createButtons(){

        Button acceptButton = new Button();
        acceptButton.setText("Continuar");
        acceptButton.setPrefWidth(200);
        AcceptButtonClickHandler acceptHandler = new AcceptButtonClickHandler(this.stage,this);
        acceptButton.setOnAction(acceptHandler);

        Button cancelButton = new Button();
        cancelButton.setText("Cancelar");
        cancelButton.setPrefWidth(200);
        CancelButtonClickHandler cancelHandler = new CancelButtonClickHandler(stage);
        cancelButton.setOnAction(cancelHandler);

        HBox buttonsBox = new HBox();
        buttonsBox.getChildren().addAll(acceptButton,cancelButton);
        buttonsBox.setSpacing(100);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox.setMargin(buttonsBox,new Insets(50, 0, 0, 0));


        this.getChildren().add(buttonsBox);
    }

    public List<String> getPlayersName(){
        return this.playersName.stream().filter(textField -> !textField.getText().isEmpty())
                .map(textField -> textField.getText()).collect(Collectors.toList());
    }
}
