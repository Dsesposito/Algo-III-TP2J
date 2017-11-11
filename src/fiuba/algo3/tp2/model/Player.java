package fiuba.algo3.tp2.model;

public class Player {

    private String name;

    private JudicialState judicialState;

    private Cell cell;

    public Player(String name){
        this.name = name;
        this.judicialState = new FreeState();
        Cell cell = new Cell("Start");
        this.cell = cell;
    }

    public void goToJail(){
        this.judicialState = new ImprisonedState();
        Cell cell = new Cell("Jail");
        this.cell = cell;
    }

    public void moveFoward(){
        judicialState.moveFoward(this);
    }

    public void doAction(){
        judicialState.doAction(this);
    }

    public String getName(){
        return this.name;
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
