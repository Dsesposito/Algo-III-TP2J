package fiuba.algo3.tp2.model;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by lucas on 11/11/17.
 */
public class Quini6 {
    private Map<String, Integer> winners;

    public Quini6(){
        this.winners = new HashMap<>();
    }

    public Integer getVictories(Player player){
        Integer victories = this.winners.get(player.getName());
        return ((victories == null) ? 0 : victories);
    }
    public void newWinner(Player player){
        Integer victories = getVictories(player);
        //System.out.println(victories);
        this.winners.put(player.getName(), victories + 1);
        double money = player.getMoney();
        switch (victories){
            case 0:
               player.setMoney(money + 50000);
               break;
            case 1:
                player.setMoney(money + 30000);
                break;
        }
    }



}
