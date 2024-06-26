package pl.rCieplinski.ecommerce.catalog.sales.offering;

import pl.rCieplinski.ecommerce.catalog.sales.cart.CartLine;

import java.math.BigDecimal;
import java.util.List;

public class OfferCalculator {
    public Offer calculate(List<CartLine> lines) {
        // every Xth free
        //>100PLN -10PLN
        return new Offer(
                BigDecimal.valueOf(10).multiply(new BigDecimal(lines.size())),
                lines.size());
    }
}
