package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.Global;

public class Player {

    private String name;
    private JudicialState judicialState;
    private Cell currentCell;
    private Money money;
    private MotionAlgorithm motionAlgorithm;

    private static Double initMoney = Global.config.getDouble("initPlayerMoney");

    public Player(String name){
        this.name = name;
        this.money = new Money(initMoney);
        this.judicialState = new FreeState();
        this.currentCell = new Cell("Start");
        this.motionAlgorithm = new NormalFoward();
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
        this.motionAlgorithm.move(this,diceResult);
    }


    public void goToJail(Jail jail){
        this.judicialState = new ImprisonedState(jail);
        this.currentCell = new Cell("Jail");
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
