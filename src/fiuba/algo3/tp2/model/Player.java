package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.Global;

public class Player {

    private String name;
    private JudicialState judicialState;
    private Cell cell;
    private Money money;

    private static Double initMoney = Global.config.getDouble("initPlayerMoney");

    public Player(String name){
        this.name = name;
        this.money = new Money(initMoney);
        this.judicialState = new FreeState();
        this.cell = new Cell("Start");
    }

    public String getName(){
        return this.name;
    }

    public void payToBank(Money money){
        this.money.subtract(money);
    }

    public Cell getCell(){
        return this.cell;
    }


    public void goToJail(Jail jail){
        this.judicialState = new ImprisonedState(jail);
        this.cell = new Cell("Jail");
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
