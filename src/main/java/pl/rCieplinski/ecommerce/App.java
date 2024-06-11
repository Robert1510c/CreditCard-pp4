package pl.rCieplinski.ecommerce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.rCieplinski.ecommerce.catalog.ProductCatalog;
import pl.rCieplinski.ecommerce.catalog.SqlProductStorage;
import pl.rCieplinski.ecommerce.Infrastructure.PayUPaymentGw;
import pl.rCieplinski.ecommerce.catalog.sales.offering.OfferCalculator;
import pl.rCieplinski.ecommerce.catalog.sales.SalesFacade;
import pl.rCieplinski.ecommerce.catalog.sales.cart.CartStorage;
import pl.rCieplinski.ecommerce.catalog.sales.reservation.ReservationRepository;

@SpringBootApplication
public class App {
    @Autowired
    SqlProductStorage sqlProductStorage;
    public static void main(String[] args){
        System.out.println("Here we go!!");
        SpringApplication.run(App.class, args);
    }
    @Bean
    ProductCatalog createMyProductCatalog() {
        var catalog = new ProductCatalog();
        catalog.addProduct("Lego set 8083","nice one");
        catalog.addProduct("Cobi blocks", "Nice one");

        return catalog;
    }
    @Bean
    SalesFacade createSales(){
        return new SalesFacade(new CartStorage(), new OfferCalculator(), new PayUPaymentGw(), new ReservationRepository());
    }
}
