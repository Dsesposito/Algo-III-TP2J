package fiuba.algo3.tp2.model.Cells;
import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Board;
import fiuba.algo3.tp2.model.Money;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by lucas on 11/11/17.
 */
public class Quini6 extends Cell {

    private Map<String, Integer> winners;

    private static Double firstQuini6Increment = Global.config.getDouble("firstQuini6Increment");
    private static Double secondQuini6Increment = Global.config.getDouble("secondQuini6Increment");

    public Quini6(String name,Board board){
        super(name,board);
        this.winners = new HashMap<>();
    }

    @Override
    public void playerLandsOnCell(Player player, Turn actualTurn) {
        newWinner(player);
        player.landsOnQuini6(this);
    }

    public Integer getVictories(Player player){
        Integer victories = this.winners.get(player.getName());
        return ((victories == null) ? 0 : victories);
    }

    private void newWinner(Player player){
        Integer victories = getVictories(player);
        this.winners.put(player.getName(), victories + 1);

        switch (victories){
            case 0:
               player.incrementMoney(new Money(firstQuini6Increment));
               break;
            case 1:
                player.incrementMoney(new Money(secondQuini6Increment));
                break;
        }
    }

}
