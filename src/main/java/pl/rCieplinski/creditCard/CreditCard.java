package pl.rCieplinski.creditCard;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal creditlimit;

    public void assignCredit(BigDecimal creditlimit) {
        this.creditlimit = creditlimit;
    }

    public BigDecimal getBalance() {
        return creditlimit;
    }
}
