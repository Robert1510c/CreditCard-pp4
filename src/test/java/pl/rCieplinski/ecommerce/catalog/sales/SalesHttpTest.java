package pl.rCieplinski.ecommerce.catalog.sales;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.rCieplinski.ecommerce.catalog.ProductCatalog;
import pl.rCieplinski.ecommerce.catalog.sales.reservation.AcceptOfferRequest;
import pl.rCieplinski.ecommerce.catalog.sales.offering.Offer;
import pl.rCieplinski.ecommerce.catalog.sales.reservation.ReservationDetails;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesHttpTest {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate http;

    @Autowired
    ProductCatalog catalog;

    @Test
    void checkoutHappyPath() {
        String productId = thereIsProduct("Example", BigDecimal.valueOf(10.10));
        var addToCartUrl = asBaseURL(String.format("api/add-product/%s", productId));

        ResponseEntity<Object> addToCartResponse = http.postForEntity(addToCartUrl, null, null);

        var getCurrentOfferUrl = asBaseURL("api/current-offer");
        ResponseEntity<Offer> offerResponse = http.getForEntity(getCurrentOfferUrl, Offer.class);

        var acceptOfferUrl = asBaseURL("api/accept-offer");
        var acceptOfferRequest = new AcceptOfferRequest();
        acceptOfferRequest
                .setFirstname("john")
                .setLastname("doe")
                .setEmail("john.doe@example.com");

        ResponseEntity<ReservationDetails> reservationResponse = http.postForEntity(
                acceptOfferUrl, acceptOfferRequest, ReservationDetails.class);

        var reservationDetails =reservationResponse.getBody();

        assertThat(addToCartResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(offerResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(reservationResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(reservationDetails.getPaymentUrl()).isNotBlank();
        assertThat(reservationDetails.getReservationId()).isNotBlank();


    }

    private String asBaseURL(String addToCartUri) {
        return String.format("http://localhost:%s/%s", port, addToCartUri);
    }

    private String thereIsProduct(String name, BigDecimal price) {
        var id = catalog.addProduct(name, name);
        catalog.changePrice(id, price);

        return id;
    }
}