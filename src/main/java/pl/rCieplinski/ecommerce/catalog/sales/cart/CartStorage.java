package pl.rCieplinski.ecommerce.catalog.sales.cart;

import java.util.Optional;

public class CartStorage {
    public Optional<Cart> findByCustomerId(String customerId) {
        return Optional.empty();
    }

    public Optional<Cart> loadForCustomer(String customerId) {
        return Optional.empty();
    }
}
