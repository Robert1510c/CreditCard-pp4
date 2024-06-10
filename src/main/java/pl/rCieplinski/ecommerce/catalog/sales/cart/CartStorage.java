package pl.rCieplinski.ecommerce.catalog.sales.cart;

import java.util.Optional;

public class InMemoryCartStorage {
    public Optional<Cart> findByCustomerId(String customerId) {
        return null;
    }

    public Optional<Cart> loadForCustomer(String customerId) {
        return Optional.empty();
    }
}
