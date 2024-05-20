package pl.rCieplinski.ecommerce.catalog.sales;

import pl.rCieplinski.ecommerce.catalog.sales.cart.Cart;
import pl.rCieplinski.ecommerce.catalog.sales.cart.InMemoryCartStorage;
import sun.awt.geom.AreaOp;

public class SalesFacade {
    private InMemoryCartStorage cartStorage;
    private OfferCalculator offerCalculator;

    public SalesFacade(InMemoryCartStorage cartStorage, OfferCalculator offerCalculator) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
    }


    public Offer getCurrentOffer(String customerId){

        Cart cart = loadCartForCustomer(customerId);
        return offerCalculator.calculate(cart.getLines());
    }

    public ReservationDetails acceptOffer(String customerId) {
        return new ReservationDetails();
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.addProduct(productId);
    }

    private Cart loadCartForCustomer(String customerId) {
        return cartStorage.findByCustomerId(customerId)
                .orElse(Cart.empty());
    }
}
