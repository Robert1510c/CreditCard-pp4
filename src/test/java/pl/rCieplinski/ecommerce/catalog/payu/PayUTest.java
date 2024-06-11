package pl.rCieplinski.ecommerce.catalog.payu;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;
import pl.rCieplinski.ecommerce.catalog.Product;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class PayUTest {
    @Test
    void creatingNewPayment() {
        PayU payu = thereIsPayU();
        OrderCreateRequest orderCreateRequest = createExampleOrderCreateRequest();

        OrderCreateResponse response = payu.handle(orderCreateRequest);

        assertNotNull(response.getRedirectUri());
        assertNotNull(response.getOrderId());
    }

    private OrderCreateRequest createExampleOrderCreateRequest() {
        var createRequest = new OrderCreateRequest();
        createRequest
                .setNotifyUrl("http://my.example.shop.rcie.pl/api/order")
                .setCustomerIp("127.0.0.1")
                .setMerchantPosId("300746")
                .setDescription("My ebook")
                .setCurrencyCode("PLN")
                .setTotalAmount(21099)
                .setExtOrderId(UUID.randomUUID().toString())
                .setBuyer((new Buyer())
                        .setEmail("robert.doe@example.com")
                        .setFirstName("Robert")
                        .setLastName("Doe")
                        .setLanguage("pl")
                )
                .setProducts(Arrays.asList(
                        new PayUProduct()
                                .setName("Product X")
                                .setQuantity(1)
                                .setUnitPrice(21000)
                ));

        return createRequest;
    }

    private PayU thereIsPayU() {
        return new PayU(
                new RestTemplate(),
                PayUCredentials.sandbox(
                        "300746",
                        "2ee86a66e5d97e3fadc400c9f19b065d"
                )
        );
    }
}
