package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.model.Exceptions.InsufficientFundsException;

public class Money {

    Double value;

    public Money(Double money){
        this.value = money;
    }

    public void subtract(Money money){
        Double diff = this.value - money.value;
        if(diff < 0){
            throw new InsufficientFundsException("Insufficient funds by " + diff);
        }
        this.value = diff;
    }

}
