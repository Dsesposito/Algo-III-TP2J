package fiuba.algo3.tp2.view;

public class OwneableHelper {

    private Integer numHouses;
    private Integer numHotels;
    private Boolean isBuildable;
    private String cellName;

    public OwneableHelper(String cellName , Boolean isBuildable, Integer numHouses, Integer numHotels) {
        this.isBuildable = isBuildable;
        this.numHotels = numHotels;
        this.numHouses = numHouses;
        this.cellName = cellName;
    }

    public OwneableHelper(String cellName,Boolean isBuildable) {
        this.isBuildable = isBuildable;
        this.cellName = cellName;
    }

    public Integer getNumHouses() {
        return numHouses;
    }

    public Integer getNumHotels() {
        return numHotels;
    }

    public Boolean isBuildable() {
        return isBuildable;
    }

    public String getCellName(){
        return this.cellName;
    }

    @Override
    public String toString() {
        return this.cellName;
    }
}
