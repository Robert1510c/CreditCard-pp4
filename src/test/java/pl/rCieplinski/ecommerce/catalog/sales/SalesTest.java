package pl.rCieplinski.ecommerce.catalog.sales;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class SalesTest {
    @Test
    void itShowsOffers(){
        SalesFacade sales = thereIsSaleFacade();
        String customerId = thereIsExampleCustomer("Robert");

        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(0, offer.getItemsCount());
        assertEquals(BigDecimal.ZERO, offer.getTotal());
    }
    private String thereIsExampleCustomer(String id) {
        return id;
    }
    private SalesFacade thereIsSaleFacade() {
        return new SalesFacade();
    }
    @Test
    void itAllowsToAddProductToCard(){

    }
    @Test
    public void itAllowsAddToCard(){

    }
    @Test
    public void itAllowsToAcceptOffer(){

    }
}
