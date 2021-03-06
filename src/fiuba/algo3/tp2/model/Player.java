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
    private Token token;

    private static Double initMoney = Global.config.getDouble("initPlayerMoney");
    private Debt debt;

    public Player(String name,Cell startCell,Token token){
        this.name = name;
        this.money = new Money(initMoney);
        this.token = token;
        this.token.setPlayer(this);
        startCell.addPlayerToCell(this);
        this.setCurrentCellAndLog(startCell);
        this.motionAlgorithm = new NormalForward();
        this.ownedCells = new ArrayList<>();
    }

    public Player(String name,Cell startCell){
        this.name = name;
        this.money = new Money(initMoney);
        this.token = new Token(startCell.getPosition());
        this.token.setPlayer(this);
        this.setCurrentCellAndLog(startCell);
        startCell.addPlayerToCell(this);
        this.motionAlgorithm = new NormalForward();
        this.ownedCells = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }

    public Money getMoney() {
        return this.money;
    }

    public void incrementMoney(Money money){
        this.money = this.money.add(money);
        AlgoPoly.getInstance().logEvent("El dinero del jugador " + this.name + " se incremento en " + money.getValue());
    }

    public void decrementMoney(Money money){
        this.money = this.money.subtract(money);
        AlgoPoly.getInstance().logEvent("El dinero del jugador " + this.name + " se redujo en " + money.getValue());
    }

    public Cell getCurrentCell(){
        return this.currentCell;
    }

    public Boolean isInCell(Cell cell){
        return cell.equals(this.currentCell);
    }

    public void move(Turn turn) {
        this.motionAlgorithm.move(this, turn);
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
        this.setCurrentCellAndLog(police);
        this.motionAlgorithm = new StoppedInJail(jail);
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
        this.token.updatePlayersOnCellPosition();
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

    public void solveDebt() {
        this.motionAlgorithm = new NormalForward();
        this.debt.solve();
        this.debt = null;
    }

    public boolean hasEnoughMoney(Money money) {
        return !this.money.isNegative(money);
    }

    public void createDebt(Debt debt) {
        this.motionAlgorithm = new StoppedInBankruptcy(debt);
        this.debt = debt;
    }

    public Boolean isStoppedByBankruptcy() {
        return (this.motionAlgorithm instanceof StoppedInBankruptcy);
    }


    public Boolean hasPropertiesToSell() {
        return this.getNumberOfProperties() > 0;
    }


    public void setDefeated() {
        this.motionAlgorithm = new Defeated();
    }

    public boolean isDefeated(){
        return (this.motionAlgorithm instanceof Defeated);
    }

    public Boolean sellingPropertiesHasEnoughMoney(Money rentalPrice) {

        Money saleValue = Money.withValue(0.0);

        for(Owneable owneable : this.ownedCells){
            saleValue = saleValue.add(owneable.getSaleValue());
        }

        return !saleValue.isNegative(rentalPrice);
    }

    public Boolean hasEnoughMoneyToSolveDebt() {
        return this.hasEnoughMoney(this.debt.getDebtMoney());
    }

    public Token getToken() {
        return token;
    }
}
