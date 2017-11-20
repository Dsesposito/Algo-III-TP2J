package fiuba.algo3.tp2.model.MotionAlgorithm;

public class DynamicForwardAlgorithmFactory {

    public static MotionAlgorithm getAlgorithm(Long diceResult){
        if(diceResult >= 2 && diceResult <= 6 ){
            return new DynamicForwardPlus2();
        }
        else if(diceResult >= 7 && diceResult <= 10){
            return new DynamicForwardCash();
        }
        else if(diceResult == 11 || diceResult == 12){
            return new DynamicForwardProperties();
        }
        return new NormalForward();
    }

}
