package pl.rCieplinski.ecommerce.catalog.sales.ui;

import org.springframework.web.bind.annotation.*;
import pl.rCieplinski.ecommerce.catalog.sales.SalesFacade;
import pl.rCieplinski.ecommerce.catalog.sales.reservation.AcceptOfferRequest;
import pl.rCieplinski.ecommerce.catalog.sales.offering.Offer;
import pl.rCieplinski.ecommerce.catalog.sales.reservation.ReservationDetails;

@RestController
public class SalesController {
    SalesFacade sales;

    public SalesController(SalesFacade sales) {
        this.sales = sales;
    }

    @GetMapping("/api/current-offer")
    Offer getCurrentOffer() {
        String customerId = getCurrentCustomerId();
        return sales.getCurrentOffer(customerId);
    }

    @PostMapping("/api/accept-offer")
    ReservationDetails acceptOffer(@RequestBody AcceptOfferRequest acceptOfferRequest ){
        var customerId = getCurrentCustomerId();
        return sales.acceptOffer(customerId, acceptOfferRequest);
    }
    @PostMapping("/api/add-product/{productId}")
    void addProduct(@PathVariable(name="productId") String productId){
        var customerId = getCurrentCustomerId();
        sales.addProduct(customerId, productId);
    }


    private String getCurrentCustomerId() {
        return "Robert";
    }



}
