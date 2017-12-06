package fiuba.algo3.tp2.model;

import fiuba.algo3.tp2.model.Exceptions.InsufficientFundsException;

public class Money {

    Double value;

    public Money(Double money){
        this.value = money;
    }

    public static Money withValue(Double value){
        return new Money(value);
    }

    public Money subtract(Money money){
        Double diff = this.value - money.value;
        if(diff < 0){
            throw new InsufficientFundsException("Insufficient funds by " + diff);
        }
        return Money.withValue(diff);
    }

    public Money multiply(Double value){
        return Money.withValue(this.value*value);
    }

    public Money add(Money money){
        return Money.withValue(this.value + money.value);
    }

    public Double modulus(Long divisor){
        return (this.value % divisor);
    }

    public Double getValue(){
        return this.value;
    }

    public boolean isNegative(Money money) {
        Double diff = this.value - money.value;
        if(diff < 0){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "$ " + this.value;
    }
}
