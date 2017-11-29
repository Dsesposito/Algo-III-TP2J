package fiuba.algo3.tp2.model;

public class Debt {

    Player creditor;
    Player debtor;
    Money debt;

    public Debt(Player debtor,Player creditor,Money debt){
        this.creditor = creditor;
        this.debtor = debtor;
        this.debt = debt;
    }

    public Money getDebtMoney() {
        return debt;
    }

    public Player getCreditor() {
        return creditor;
    }
}
