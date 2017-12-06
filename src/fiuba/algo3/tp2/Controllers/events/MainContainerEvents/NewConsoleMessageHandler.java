package fiuba.algo3.tp2.Controllers.events.MainContainerEvents;

import fiuba.algo3.tp2.view.MainContainer;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class NewConsoleMessageHandler implements ListChangeListener {

    private MainContainer mainView;

    public NewConsoleMessageHandler(MainContainer mainView){
        this.mainView = mainView;
    }

    @Override
    public void onChanged(Change change) {
        if(change.next()){
            ObservableList list = change.getList();
            this.mainView.printLine((String)list.get(list.size() - 1));
        }
    }
}
