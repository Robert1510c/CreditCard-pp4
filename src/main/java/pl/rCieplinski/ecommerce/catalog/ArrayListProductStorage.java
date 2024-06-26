package pl.rCieplinski.ecommerce.catalog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListProductStorage implements ProductStorage {
    private ArrayList<Product> products;

    public ArrayListProductStorage() {
        products = new ArrayList<>();
    }
    @Override
    public List<Product> getAllProducts(){
        return Collections.unmodifiableList(products);
    }

    @Override
    public void setUpDatabase() {

    }

    @Override
    public void add(Product newProduct) {
        products.add(newProduct);
    }
    @Override
    public Product getProductById(String id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .get();
    }
}
