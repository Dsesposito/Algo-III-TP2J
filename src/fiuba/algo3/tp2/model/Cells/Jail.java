package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.*;
import fiuba.algo3.tp2.model.Exceptions.PlayerExceptions.PlayerNotAbleToPayBailException;

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

    @Override
    public void playerLandsOnCell(Player player, Turn actualTurn) {
        player.landsOnJail(this);
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

        prisioners.remove(prisoner);

        player.releasedFromJail();

    }

    public void addPrisioner(Player player){
        this.prisioners.add(new Prisoner(player));
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
