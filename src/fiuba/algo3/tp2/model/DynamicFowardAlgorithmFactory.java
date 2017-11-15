package fiuba.algo3.tp2.model;

public class DynamicFowardAlgorithmFactory {

    public static MotionAlgorithm getAlgorithm(Long diceResult){
        if(diceResult >= 2 && diceResult <= 6 ){
            return new DynamicFowardPlus2();
        }
        else if(diceResult >= 7 && diceResult <= 10){
            return new DynamicFowardCash();
        }
        else if(diceResult == 11 || diceResult == 12){
            return new DynamicFowardProperties();
        }
        return new NormalFoward();
    }

}
