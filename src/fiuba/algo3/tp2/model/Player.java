package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Cells.Cell;
import fiuba.algo3.tp2.model.Cells.Jail;
import fiuba.algo3.tp2.model.Cells.Neighborhood;
import fiuba.algo3.tp2.model.JudicialState.FreeState;
import fiuba.algo3.tp2.model.JudicialState.ImprisonedState;
import fiuba.algo3.tp2.model.JudicialState.JudicialState;
import fiuba.algo3.tp2.model.MotionAlgorithm.DynamicBackwardAlgorithmFactory;
import fiuba.algo3.tp2.model.MotionAlgorithm.DynamicForwardAlgorithmFactory;
import fiuba.algo3.tp2.model.MotionAlgorithm.MotionAlgorithm;
import fiuba.algo3.tp2.model.MotionAlgorithm.NormalForward;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private JudicialState judicialState;
    private Cell currentCell;
    private Money money;
    private MotionAlgorithm motionAlgorithm;
    private List<Neighborhood> neighborhoods;

    private static Double initMoney = Global.config.getDouble("initPlayerMoney");

    public Player(String name,Cell startCell){
        this.name = name;
        this.money = new Money(initMoney);
        this.judicialState = new FreeState();
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

    public void goToCell(Cell cell){
        this.currentCell = cell;
    }

    public void move(Long diceResult){

        //TODO: Aplicar Double Dispatch para sacar el if. Hay un bug aca. Si caes en avance dinámico
        //o retroceso dinamico habiendo sacado uno, en la proxima jugada cuando te muevas se va a mover
        //segun el algoritmo correspondiente a los dados sacados y esto no deberia ser asi, sino que
        //deberia avanzar con el algoritmo normal foward.
        if(currentCell.isCell("Avance Dinámico")){
            this.motionAlgorithm = DynamicForwardAlgorithmFactory.getAlgorithm(diceResult);
        }
        else if(currentCell.isCell("Retroceso Dinámico")){
            this.motionAlgorithm = DynamicBackwardAlgorithmFactory.getAlgorithm(diceResult);
        }

        this.motionAlgorithm.move(this,diceResult);
    }


    public void goToJail(Jail jail){
        this.judicialState = new ImprisonedState(jail);
    }

    public void releasedFromJail(){
        this.judicialState = new FreeState();
    }

    public Boolean isInJail(){
        return judicialState.isInJail(this);
    }

    public void moveFoward(){
        judicialState.moveFoward(this);
    }

    public void doAction(){
        judicialState.doAction(this);
    }

    public void nextTurn(){
        judicialState.nextTurn(this);
    }

    public void addNeighborhood(Neighborhood neighborhood){
        this.neighborhoods.add(neighborhood);
    }

    public Long getNumberOfProperties(){
        Long numHousesAndHotels = this.neighborhoods.stream().mapToLong(neighborhoodItem -> (neighborhoodItem.getNumberOfHotel() + neighborhoodItem.getNumberOfHouses())).sum();
        return (numHousesAndHotels + this.neighborhoods.size());
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
