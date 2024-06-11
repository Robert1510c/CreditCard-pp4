package pl.rCieplinski.ecommerce.Infrastructure;

import pl.rCieplinski.ecommerce.catalog.sales.payment.PaymentDetails;
import pl.rCieplinski.ecommerce.catalog.sales.payment.PaymentGateway;
import pl.rCieplinski.ecommerce.catalog.sales.payment.RegisterPaymentRequest;

public class PayUPaymentGw implements PaymentGateway {


    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest){
        return null;
    }
}
