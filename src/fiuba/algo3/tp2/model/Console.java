package fiuba.algo3.tp2.model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Console {

    private List<String> messages;

    private ObservableList<String> observableMessages;

    public Console(){
        messages = new ArrayList<>();
        observableMessages = FXCollections.observableList(messages);

    }

    public void addMessageObserver(ListChangeListener listener){
        observableMessages.addListener(listener);
    }

    public void addMessage(String message){
        observableMessages.add(message + " \n");
    }

}
