package fiuba.algo3.tp2.model.Cells;

import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.*;
import fiuba.algo3.tp2.model.Exceptions.PlayerExceptions.PlayerNotAbleToBeReleasedException;
import fiuba.algo3.tp2.model.Exceptions.PlayerExceptions.PlayerNotAbleToPayBailException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Jail extends Cell {

    List<Prisoner> prisioners ;

    private static Double bailCost = Global.config.getDouble("bailCost");

    public Jail(String name, Board board, Position position){
        super(name, board, position );
        prisioners = new ArrayList<>();
    }

    @Override
    public void playerLandsOnCell(Player player, Turn actualTurn) {
        player.getCurrentCell().removePlayerFromCell(player);
        super.addPlayerToCell(player);
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

        player.decrementMoney(new Money(bailCost));

        prisioners.remove(prisoner);

        player.releasedFromJail();

    }

    public void addPrisoner(Player player){
        this.prisioners.add(new Prisoner(player));
    }

    public Boolean isPrisoner(Player player){
        return !(this.findPlayerInPrisoners(player) == null);
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

    public void releasePrisoner(Player player) {
        this.playerMustBeInJail(player);

        Prisoner prisoner = this.findPlayerInPrisoners(player);

        if(!prisoner.isFreeToGo()){
            throw new PlayerNotAbleToBeReleasedException("The player " + player.getName() + " is not able to be released from jail");
        }

        prisioners.remove(prisoner);

        player.releasedFromJail();
    }
}
