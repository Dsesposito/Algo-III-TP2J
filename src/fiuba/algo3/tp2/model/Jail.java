package fiuba.algo3.tp2.model;

import java.util.ArrayList;
import java.util.List;

public class Jail {

    List<Prisoner> prisioners ;

    public Jail(){
        prisioners = new ArrayList<>();
    }


    public void addPrisoner(Player player){
        prisioners.add(new Prisoner(player));
    }

    public Boolean isInJail(Player player){
        return prisioners.contains(player);
    }

}
