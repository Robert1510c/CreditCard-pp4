package pl.rCieplinski.ecommerce.catalog.sales;

public interface PaymentGateway {
    PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest);
}
