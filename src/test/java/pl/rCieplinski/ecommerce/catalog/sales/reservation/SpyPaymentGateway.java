package pl.rCieplinski.ecommerce.catalog.sales.reservation;

import pl.rCieplinski.ecommerce.catalog.sales.payment.PaymentDetails;
import pl.rCieplinski.ecommerce.catalog.sales.payment.PaymentGateway;
import pl.rCieplinski.ecommerce.catalog.sales.payment.RegisterPaymentRequest;

public class SpyPaymentGateway implements PaymentGateway {
    Integer requestCount =0;
    public RegisterPaymentRequest lastRequest;

    public Integer getRequestCount() {
        return requestCount;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        this.requestCount++;
        lastRequest = registerPaymentRequest;
        return new PaymentDetails("http://spy-gateway");
    }
}
