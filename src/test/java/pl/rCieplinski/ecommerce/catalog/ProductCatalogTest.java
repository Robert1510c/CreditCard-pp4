package pl.rCieplinski.ecommerce.catalog;

import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class ProductCatalogTest {
    @Test
    void isListAvailableProducts(){
        ProductCatalog catalog = new ProductCatalog();


        List<Product> products = catalog.allProducts();

        assert products.isEmpty();
    }
    @Test
    void itAllowsToAddProducts(){
        ProductCatalog catalog = new ProductCatalog();

        catalog.addProduct("Lego set 8663", "Nice one");
        List<Product> products = catalog.allProducts();

        assertThat(products).hasSize(1);
    }

    @Test
    void itLoadsSingleProductById(){
        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("Lego set 8003","Nice one");
        Product loaded = catalog.getProductBy(id);

        assertThat(id).isEqualTo(loaded.getId());
    }

    @Test
    void itAllowsToChangePrice(){
        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("Lego set 8003","Nice one");


        catalog.changePrice(id, BigDecimal.valueOf(10.10));
        Product loaded = catalog.getProductBy(id);


        assertThat(BigDecimal.valueOf(10.10)).isEqualTo(loaded.getPrice());

    }
}
