package pl.rCieplinski.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final String id;

    public String getName() {
        return name;
    }

    private final String name;

    public String getDescription() {
        return description;
    }

    private final String description;
    private BigDecimal price;

    public Product(UUID id, String name, String description, BigDecimal bigDecimal) {
        this.id = id.toString();
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void changePrice(BigDecimal newPrice) {

        this.price = newPrice;
    }
}
