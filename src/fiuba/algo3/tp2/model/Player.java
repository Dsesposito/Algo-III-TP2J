package fiuba.algo3.tp2.model;

public class Player {

    private String name;

    private JudicialState judicialState;

    private Cell cell;

    private double money;

    public Player(String name){
        this.name = name;
        this.money = 100000;
        this.judicialState = new FreeState();
        Cell cell = new Cell("Start");
        this.cell = cell;
    }

    // getters
    public String getName(){
        return this.name;
    }

    public double getMoney(){
        return this.money;
    }

    // setters
    public void setMoney(double money){
        this.money = money;
    }

    public void goToJail(){
        this.judicialState = new ImprisonedState();
        Cell cell = new Cell("Jail");
        this.cell = cell;
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


    public Cell getCell(){
        return this.cell;
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
