package fiuba.algo3.tp2.model.MotionAlgorithm;

public class DynamicBackwardAlgorithmFactory {

    public static MotionAlgorithm getAlgorithm(Long diceResult){
        if(diceResult >= 2 && diceResult <= 6 ){
            return new DynamicBackwardProperties();
        }
        else if(diceResult >= 7 && diceResult <= 10){
            return new DynamicBackwardCash();
        }
        else if(diceResult == 11 || diceResult == 12){
            return new DynamicBackwardPlus2();
        }
        return new NormalForward();
    }

}
