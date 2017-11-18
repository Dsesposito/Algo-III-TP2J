package fiuba.algo3.tp2.model;

public class Die {
    private Integer lastFace;
    private static Integer minimum = 1;
    private static Integer maximum = 6;

    public Die(){};

    public Integer throwDie(){
        lastFace = minimum + (int)(Math.random() * (maximum - minimum));
        return lastFace;
    }

    public Integer getLastFace(){
        return lastFace;
    }

}
