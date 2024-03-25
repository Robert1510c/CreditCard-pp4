package pl.rCieplinski.ecommerce.catalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductCatalogControler {
    ProductCatalog catalog;
    public ProductCatalogControler(ProductCatalog catalog) {
        this.catalog = catalog;
    }



    @GetMapping("/api/products")
    List<Product> getAllProducts() {
        return catalog.allProducts();
    }

    @GetMapping("/api/published-products")
    List<Product> getPublishedProducts(){
        return new ArrayList<>();
    }

}
