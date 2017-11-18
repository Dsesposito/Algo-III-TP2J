package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Cells.*;
import fiuba.algo3.tp2.model.MotionAlgorithm.*;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private Cell currentCell;
    private Money money;
    private MotionAlgorithm motionAlgorithm;
    private List<Neighborhood> neighborhoods;

    private static Double initMoney = Global.config.getDouble("initPlayerMoney");

    public Player(String name,Cell startCell){
        this.name = name;
        this.money = new Money(initMoney);
        this.currentCell = startCell;
        this.motionAlgorithm = new NormalForward();
        this.neighborhoods = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }

    public Money getMoney() {
        return this.money;
    }

    public void payToBank(Money money){
        this.money.subtract(money);
    }

    public void incrementMoney(Money money){
        this.money.add(money);
    }

    public Cell getCurrentCell(){
        return this.currentCell;
    }

    public Boolean isInCell(Cell cell){
        return cell.equals(this.currentCell);
    }

    public void move(Turn turn){
        this.motionAlgorithm.move(this,turn);
    }

    public void releasedFromJail(){
        this.motionAlgorithm = new NormalForward();
    }

    public void addNeighborhood(Neighborhood neighborhood){
        this.neighborhoods.add(neighborhood);
    }

    public Long getNumberOfProperties(){
        Long numHousesAndHotels = this.neighborhoods.stream().mapToLong(neighborhoodItem -> (neighborhoodItem.getNumberOfHotel() + neighborhoodItem.getNumberOfHouses())).sum();
        return (numHousesAndHotels + this.neighborhoods.size());
    }

    public void landsOnDynamicBackward(DynamicBackward dynamicBackward,Turn turn) {
        //Configuro el nuevo algoritmo de avance
        this.motionAlgorithm = DynamicBackwardAlgorithmFactory.getAlgorithm(turn.getDiceResult());
        this.currentCell = dynamicBackward;
        //Vuelvo a mover
        this.move(turn);
        //Restablezco algoritmo de avance
        this.motionAlgorithm = new NormalForward();
    }

    public void landsOnDynamicForward(DynamicForward dynamicForward, Turn turn){
        //Configuro el nuevo algoritmo de avance
        this.motionAlgorithm = DynamicForwardAlgorithmFactory.getAlgorithm(turn.getDiceResult());
        this.currentCell = dynamicForward;
        //Vuelvo a mover
        this.move(turn);
        //Restablezco algoritmo de avance
        this.motionAlgorithm = new NormalForward();
    }

    public void landsOnJail(Jail jail) {
        this.currentCell = jail;
    }

    public void landsOnLuxuryTax(LuxuryTax luxuryTax) {
        this.currentCell = luxuryTax;
    }

    public void landsOnNeighborhood(Neighborhood neighborhood) {
        this.currentCell = neighborhood;
    }

    public void landsOnPolice(Police police,Jail jail) {
        this.motionAlgorithm = new Stopped(jail);
        this.currentCell = jail;
    }

    public void landsOnQuini6(Quini6 quini6) {
        this.currentCell = quini6;
    }

    public void landsOnRailWay(Railway railway) {
        this.currentCell = railway;
    }

    public void landsOnService(Service service) {
        this.currentCell = service;
    }

    public void landsOnStartPoint(StartPoint startPoint) {
        this.currentCell = startPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return name != null ? name.equals(player.name) : player.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
