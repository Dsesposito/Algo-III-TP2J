package fiuba.algo3.tp2.model.MotionAlgorithm;

import fiuba.algo3.tp2.model.Debt;
import fiuba.algo3.tp2.model.Exceptions.PlayerExceptions.PlayerInBankruptcyException;
import fiuba.algo3.tp2.model.Player;
import fiuba.algo3.tp2.model.Turn;

public class StoppedInBankruptcy implements MotionAlgorithm {

    Debt debt;

    public StoppedInBankruptcy(Debt debt){
        this.debt = debt;
    }

    @Override
    public void move(Player player, Turn turn) {

        if(player.hasEnoughMoney(debt.getDebtMoney())){
            player.decrementMoney(debt.getDebtMoney());
            debt.getCreditor().incrementMoney(debt.getDebtMoney());
            player.solveDebt();
        }
        else{
            throw new PlayerInBankruptcyException("Player can't move. First he must solves his debt.");
        }
    }
}
