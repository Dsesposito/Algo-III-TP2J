package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Cells.*;
import fiuba.algo3.tp2.model.MotionAlgorithm.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player {

    private String name;
    private Cell currentCell;
    private Money money;
    private MotionAlgorithm motionAlgorithm;
    private List<Owneable> ownedCells;

    private static Double initMoney = Global.config.getDouble("initPlayerMoney");

    public Player(String name,Cell startCell){
        this.name = name;
        money = new Money(initMoney);
        currentCell = startCell;
        motionAlgorithm = new NormalForward();
        ownedCells = new ArrayList<>();
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
        AlgoPoly.getInstance().logEvent("El dinero del jugador " + this.name + " se incremento en " + money.getValue());
    }

    public void decrementMoney(Money money){
        this.money.subtract(money);
        AlgoPoly.getInstance().logEvent("El dinero del jugador " + this.name + " se redujo en " + money.getValue());
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

    public void addOwneable(Owneable owneable){
        this.ownedCells.add(owneable);
    }

    public void dropOwneable(Owneable owneable){
        this.ownedCells.remove(owneable);
    }


    public Long getNumberOfProperties(){
        List<Owneable> neighborhoods = this.ownedCells.stream()
                .filter(owneable -> owneable.isNeighborhood()).collect(Collectors.toList());
        Long numHousesAndHotels = neighborhoods.stream()
                .mapToLong(neighborhoodItem -> ((Neighborhood)neighborhoodItem).getNumberOfHotelAndHouses())
                .sum();
        return (numHousesAndHotels + neighborhoods.size());
    }

    public Boolean hasOwneableCells(){
        return !this.ownedCells.isEmpty();
    }

    public void landsOnDynamicBackward(DynamicBackward dynamicBackward,Turn turn) {
        //Configuro el nuevo algoritmo de avance
        this.motionAlgorithm = DynamicBackwardAlgorithmFactory.getAlgorithm(turn.getDiceResult());
        this.setCurrentCellAndLog(dynamicBackward);
        //Vuelvo a mover
        this.move(turn);
        //Restablezco algoritmo de avance
        this.motionAlgorithm = new NormalForward();
    }

    public void landsOnDynamicForward(DynamicForward dynamicForward, Turn turn){
        //Configuro el nuevo algoritmo de avance
        this.motionAlgorithm = DynamicForwardAlgorithmFactory.getAlgorithm(turn.getDiceResult());
        this.setCurrentCellAndLog(dynamicForward);
        //Vuelvo a mover
        this.move(turn);
        //Restablezco algoritmo de avance
        this.motionAlgorithm = new NormalForward();
    }

    public void landsOnJail(Jail jail) {
        this.setCurrentCellAndLog(jail);
    }

    public void landsOnLuxuryTax(LuxuryTax luxuryTax) {
        this.setCurrentCellAndLog(luxuryTax);
    }

    public void landsOnNeighborhood(Neighborhood neighborhood) {
        this.setCurrentCellAndLog(neighborhood);
    }

    public void landsOnPolice(Police police,Jail jail) {
        this.motionAlgorithm = new Stopped(jail);
        this.setCurrentCellAndLog(jail);
    }

    public void landsOnQuini6(Quini6 quini6) {
        this.setCurrentCellAndLog(quini6);
    }

    public void landsOnRailWay(Railway railway) {
        this.setCurrentCellAndLog(railway);
    }

    public void landsOnService(Service service) {
        this.setCurrentCellAndLog(service);
    }

    public void landsOnStartPoint(StartPoint startPoint) {
        this.setCurrentCellAndLog(startPoint);
    }

    private void setCurrentCellAndLog(Cell currentCell){
        this.currentCell = currentCell;
        AlgoPoly.getInstance().logEvent("El jugador " + this.name + " cayó en " + currentCell.getName());
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

    public List<Owneable> getOwneableCells() {
        return this.ownedCells;
    }

}
