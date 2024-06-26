package pl.rCieplinski.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductCatalog {

    ProductStorage productStorage;

    public ProductCatalog() {
        this.productStorage = new ArrayListProductStorage();
    }

    public List<Product> allProducts(){
        return productStorage.getAllProducts();
    }

    public String addProduct(String name, String description) {
        UUID id = UUID.randomUUID();
        Product newProduct = new Product(id, name, description, BigDecimal.valueOf(0));

        productStorage.add(newProduct);

        return  newProduct.getId();
    }

    public Product getProductBy(String id) {
        return productStorage.getProductById(id);
    }

    public void changePrice(String id, BigDecimal newPrice) {
        Product loaded = this.getProductBy(id);
        loaded.changePrice(newPrice);
    }
}
