package pl.rCieplinski.ecommerce.catalog.sales;

import org.junit.jupiter.api.Test;
import pl.rCieplinski.ecommerce.catalog.sales.cart.CartStorage;
import pl.rCieplinski.ecommerce.catalog.sales.offering.Offer;
import pl.rCieplinski.ecommerce.catalog.sales.offering.OfferCalculator;
import pl.rCieplinski.ecommerce.catalog.sales.reservation.ReservationDetails;
import pl.rCieplinski.ecommerce.catalog.sales.reservation.ReservationRepository;
import pl.rCieplinski.ecommerce.catalog.sales.reservation.SpyPaymentGateway;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class SalesTest {
    @Test
    void itShowsOffers(){
        SalesFacade sales = thereIsSales();
        String customerId = thereIsExampleCustomer("Robert");

        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(0, offer.getItemsCount());
        assertEquals(BigDecimal.ZERO, offer.getTotal());
    }

    private String thereIsExampleCustomer(String id) {
        return id;
    }
    private SalesFacade thereIsSales() {
        return new SalesFacade(
                new CartStorage(),
                new OfferCalculator(),
                new SpyPaymentGateway(),
                new ReservationRepository()
        );
    }
    @Test
    void itAllowsToAddProductToCard(){
        String productId = thereIsProduct("Example", BigDecimal.valueOf(10));
        String customerId = thereIsExampleCustomer("Robert");
        SalesFacade sales = thereIsSales();

        sales.addProduct(customerId, productId);
        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(1, offer.getItemsCount());
        assertEquals(BigDecimal.valueOf(10), offer.getTotal());

    }
    @Test
    void itAllowsToAddMultipleProductToCard(){
        String productA = thereIsProduct("Example", BigDecimal.valueOf(10));
        String productB = thereIsProduct("Example", BigDecimal.valueOf(20));
        String customerId = thereIsExampleCustomer("Robert");
        SalesFacade sales = thereIsSales();

        sales.addProduct(customerId, productA);
        sales.addProduct(customerId, productB);
        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(2, offer.getItemsCount());
        assertEquals(BigDecimal.valueOf(30), offer.getTotal());

    }
    @Test
    void itDistinguishCartsByCustomer(){
        String productA = thereIsProduct("Example", BigDecimal.valueOf(10));
        String productB = thereIsProduct("Example", BigDecimal.valueOf(20));
        String customerA = thereIsExampleCustomer("Robert");
        String customerB = thereIsExampleCustomer("Maciek");
        SalesFacade sales = thereIsSales();

        sales.addProduct(customerA, productA);
        sales.addProduct(customerB, productB);
        Offer offerA = sales.getCurrentOffer(customerA);
        Offer offerB = sales.getCurrentOffer(customerB);

        assertEquals(1, offerA.getItemsCount());
        assertEquals(BigDecimal.valueOf(10), offerA.getTotal());

        assertEquals(1, offerB.getItemsCount());
        assertEquals(BigDecimal.valueOf(20), offerB.getTotal());

    }

    private String thereIsProduct(String name, BigDecimal price) {
        return name;
    }

    @Test
    public void itAllowsAddToCard(){

    }
//    @Test
//    public void itAllowsToAcceptOffer(){
//        String productId = thereIsProduct("Example", BigDecimal.valueOf(10));
//        String customerId = thereIsExampleCustomer("Robert");
//        SalesFacade sales = thereIsSales();
//
//        sales.addProduct(customerId, productId);
//        ReservationDetails reservationDetails = sales.acceptOffer(customerId);
//
//        //tu czegoś brakuje
//      }

}
