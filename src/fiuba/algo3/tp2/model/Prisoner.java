package fiuba.algo3.tp2.model;

public class Prisoner {

    private Player imprisonedPlayer;

    private Long numberOfTurns;

    public Prisoner (Player player){
        imprisonedPlayer = player;
        numberOfTurns = 0L;
    }

}
