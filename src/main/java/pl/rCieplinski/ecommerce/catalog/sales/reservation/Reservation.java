package pl.rCieplinski.ecommerce.catalog.sales.reservation;

import pl.rCieplinski.ecommerce.catalog.sales.payment.PaymentDetails;
import pl.rCieplinski.ecommerce.catalog.sales.offering.Offer;

import java.math.BigDecimal;
import java.time.Instant;

public class Reservation {
    private String reservationId;
    private CustomerDetails customerDetails;
    private BigDecimal total;
    private Instant paidAt;

    public Reservation(String reservationId, CustomerDetails customerDetails, BigDecimal total) {
        this.reservationId = reservationId;
        this.customerDetails = customerDetails;
        this.total = total;
    }


    public static Reservation of(String reservationId, String customerId, AcceptOfferRequest acceptOfferRequest, Offer offer, PaymentDetails paymentDetails) {
        return new Reservation(
                reservationId,
                new CustomerDetails(customerId, acceptOfferRequest.getFirstname(), acceptOfferRequest.getLastname(), acceptOfferRequest.getEmail()),
                offer.getTotal()
        );
    }

    public boolean isPending(){
        return paidAt == null;
    };

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getId() {
        return null;
    }
}
