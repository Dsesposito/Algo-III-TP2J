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

    public Money copy(){
        return new Money(this.value);
    }

    public void subtract(Money money){
        Double diff = this.value - money.value;
        if(diff < 0){
            throw new InsufficientFundsException("Insufficient funds by " + diff);
        }
        this.value = diff;
    }

    public Money multiply(Double value){
        return Money.withValue(this.value*value);
    }

    public void subtract(Double value){
        this.subtract(new Money(value));
    }

    public void add(Money money){
        this.value = this.value + money.value;
    }

    public void add(Double value){
        this.add(new Money(value));
    }

    public Double modulus(Long divisor){
        return (this.value % divisor);
    }

    public Double getValue(){
        return this.value;
    }


}
