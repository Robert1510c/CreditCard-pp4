package pl.rCieplinski.ecommerce.catalog;

import java.util.List;
import java.util.HashMap;

public class HashMapProductStorage implements ProductStorage {
    HashMap<String, Product> products;

    @Override
    public List<Product> allProducts() {
        return products.values().stream().toList();
    }

    @Override
    public void add(Product newProduct) {
        products.put(product.getId(), product);
    }

    @Override
    public Product getProductBy(String id) {
        return products.get(id);
    }
}
