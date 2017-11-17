package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Exceptions.PlayerNotAbleToPayBailException;
import fiuba.algo3.tp2.model.Money;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Prisoner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Jail extends Cell {

    List<Prisoner> prisioners ;

    private static Double bailCost = Global.config.getDouble("bailCost");

    public Jail(String name, Board board){
        super(name,board);
        prisioners = new ArrayList<>();
    }

    public Boolean isFreeToGo(Player player){

        this.playerMustBeInJail(player);

        return this.findPlayerInPrisoners(player).isFreeToGo();
    }

    public Boolean isAbleToPayBail(Player player){
        this.playerMustBeInJail(player);

        return this.findPlayerInPrisoners(player).isAbleToPayBail();
    }

    public void incrementTurn(Player player){

        this.playerMustBeInJail(player);

        this.findPlayerInPrisoners(player).incrementTurn();
    }

    public void playerPayBail(Player player){
        this.playerMustBeInJail(player);

        Prisoner prisoner = this.findPlayerInPrisoners(player);

        if(!prisoner.isAbleToPayBail()){
            throw new PlayerNotAbleToPayBailException("The player " + player.getName() + " is not able to pay bail, he must wait");
        }

        player.payToBank(new Money(bailCost));
        player.releasedFromJail();

    }

    public void addPrisoner(Player player){
        prisioners.add(new Prisoner(player));
        player.goToJail(this);
    }

    private Prisoner findPlayerInPrisoners(Player player){
        return prisioners.stream().filter(filterPrisoner -> filterPrisoner.isPlayer(player)).findFirst().orElse(null);
    }

    private void playerMustBeInJail(Player player){
        Prisoner prisoner = this.findPlayerInPrisoners(player);

        if(prisoner == null){
            throw new NoSuchElementException("The player " + player.getName() + " is not in jail");
        }
    }

}
