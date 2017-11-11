package fiuba.algo3.tp2.model;

/**
 * Created by lucas on 11/11/17.
 */
public class Player {
    private String name;
    private double money;

    public Player(String name){
        this.name = name;
        this.money = 100000;
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

}
