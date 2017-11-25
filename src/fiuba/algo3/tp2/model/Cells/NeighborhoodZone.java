package fiuba.algo3.tp2.model.Cells;

import java.util.List;

public class NeighborhoodZone extends CellGroup {

    private NeighborhoodZone(String name, List<Groupable> groupables) {
        super(name, groupables);
    }

    public Boolean hasCompleteHouses(){
        for(Groupable groupable : super.groupables){
            if(!((Neighborhood) groupable).hasAllHousesBuilt()){
                return false;
            }
        }
        return true;
    }

    public static void group(String name, List<Groupable> groupables) {
        new NeighborhoodZone(name,groupables);
    }
}
