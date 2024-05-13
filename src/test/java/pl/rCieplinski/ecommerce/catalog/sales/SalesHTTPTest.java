package pl.rCieplinski.ecommerce.catalog.sales;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.rCieplinski.ecommerce.catalog.ProductCatalog;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesHTTPTest {
    @LocalServerPort
    int port;
    @Autowired
    TestRestTemplate http;
    @Autowired
    ProductCatalog catalog;

    @Test
    void itAcceptOfferHappyPath(){
        var productId = thereIsExampleProduct("Example product", BigDecimal.valueOf(10));
        //ACT
        //add to cart
        var uri = String.format("api/add-to-cart", productId);
        var addProductToCartUrl = String.format("http://localhost:%s/%s", port, uri);
        http.postForEntity(addProductToCartUrl, null, Object.class);

        //ACT 2
        //Accept offer
        AcceptOfferRequest acceptOfferRequest = new AcceptOfferRequest();
        acceptOfferRequest
                .setFirstname("Robert")
                .setLastname("Cieplinski")
                .setEmail("robert.cieplinski@example.com");

        var acceptOfferURL = String.format("http://localhost:%s/%s", port , "api/accept-offer");
        http.postForEntity(acceptOfferURL, acceptOfferRequest, ReservationDetails.class);
        ResponseEntity<ReservationDetails> reservationResponse= http.postForEntity(
                acceptOfferURL, acceptOfferRequest, ReservationDetails.class);

        assertEquals(HttpStatus.OK, reservationResponse.getStatusCode());
        assertEquals(BigDecimal.valueOf(10), reservationResponse.getBody().getTotal());
        assertNotNull(reservationResponse.getBody().getReservationById());
        assertNotNull(reservationResponse.getBody().getPaymentURL());
    }

    private Object thereIsExampleProduct(String name, BigDecimal price) {
        var prodId = catalog.addProduct(name, name);
        catalog.changePrice(prodId, price);
        return prodId;
    }
}
