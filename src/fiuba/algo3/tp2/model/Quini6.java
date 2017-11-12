package fiuba.algo3.tp2.model;
import fiuba.algo3.tp2.Global;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by lucas on 11/11/17.
 */
public class Quini6 {

    private Map<String, Integer> winners;

    private static Double firstQuini6Increment = Global.config.getDouble("firstQuini6Increment");
    private static Double secondQuini6Increment = Global.config.getDouble("secondQuini6Increment");

    public Quini6(){
        this.winners = new HashMap<>();
    }

    public Integer getVictories(Player player){
        Integer victories = this.winners.get(player.getName());
        return ((victories == null) ? 0 : victories);
    }

    public void newWinner(Player player){
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
