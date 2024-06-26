package pl.rCieplinski.ecommerce.Infrastructure;

import pl.rCieplinski.ecommerce.catalog.payu.Buyer;
import pl.rCieplinski.ecommerce.catalog.payu.OrderCreateRequest;
import pl.rCieplinski.ecommerce.catalog.payu.PayU;
import pl.rCieplinski.ecommerce.catalog.sales.payment.PaymentDetails;
import pl.rCieplinski.ecommerce.catalog.sales.payment.PaymentGateway;
import pl.rCieplinski.ecommerce.catalog.sales.payment.RegisterPaymentRequest;

import java.util.UUID;

public class PayUPaymentGw implements PaymentGateway {

    PayU payU;

    public PayUPaymentGw(PayU payU) {
        this.payU = payU;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest){
        var request = new OrderCreateRequest();
        request
                .setNotifyUrl("http://localhost:8080/catalog/notify")
                .setCustomerIp("127.0.0.1")
                .setMerchantPosId("300746")
                .setDescription("My digital product")
                .setCurrencyCode("PLN")
                .setTotalAmount(registerPaymentRequest.getTotalAsPennies())
                .setExtOrderId(UUID.randomUUID().toString())
                .setBuyer(new Buyer()
                        .setEmail(registerPaymentRequest.getEmail())
                        .setFirstName(registerPaymentRequest.getFirstname())
                );
        return null;
    }
}
