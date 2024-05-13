package pl.rCieplinski.ecommerce.catalog;

import java.util.List;

public interface ProductStorage {
    List<Product> getAllProducts();
    void setUpDatabase();
    void add(Product newProduct);

    Product getProductById(String id);
}
