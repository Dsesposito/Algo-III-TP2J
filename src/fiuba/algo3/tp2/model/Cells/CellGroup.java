package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.model.Player;

import java.util.List;

public class CellGroup {

    protected List<Groupable> groupables;

    private String name;

    protected CellGroup(String name, List<Groupable> groupables){
        this.groupables = groupables;
        this.name = name;
        for(Groupable groupable : this.groupables){
            groupable.groupBy(this);
        }
    }

    public Boolean isOwnedBySamePlayer(Player player){
        for(Groupable groupables : this.groupables){
            if(!groupables.isOwnedBy(player)){
                return false;
            }
        }
        return true;
    }

    public static void group(String name, List<Groupable> groupables) {
        new CellGroup(name,groupables);
    }

    public List<Groupable> getGroupables(){
        return this.groupables;
    }
}
