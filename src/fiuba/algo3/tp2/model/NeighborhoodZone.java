package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.model.Cells.Neighborhood;

import java.util.List;

public class NeighborhoodZone {

    private List<Neighborhood> neighborhoods;

    private String name;

    public NeighborhoodZone(String name , List<Neighborhood> neighborhoods){
        this.name = name;
        this.neighborhoods = neighborhoods;
        for(Neighborhood neighborhood : this.neighborhoods){
            neighborhood.setZone(this);
        }
    }

    public Boolean hasSameOwner(Player owner){
        for(Neighborhood neighborhood : this.neighborhoods){
            if(!neighborhood.isOwnedBy(owner)){
                return false;
            }
        }
        return true;
    }

}
