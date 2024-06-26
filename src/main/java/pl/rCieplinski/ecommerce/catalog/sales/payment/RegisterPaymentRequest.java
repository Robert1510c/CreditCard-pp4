package pl.rCieplinski.ecommerce.catalog.sales.payment;

import pl.rCieplinski.ecommerce.catalog.sales.reservation.AcceptOfferRequest;

import java.math.BigDecimal;

public class RegisterPaymentRequest {
    private final String reservationId;
    private final AcceptOfferRequest acceptOfferRequest;
    private final BigDecimal total;
    private final String firstname;
    private final String lastname;
    private final String email;


    public RegisterPaymentRequest(String reservationId, AcceptOfferRequest acceptOfferRequest, BigDecimal total) {

        this.reservationId = reservationId;
        this.acceptOfferRequest = acceptOfferRequest;
        this.total = total;
        this.firstname = acceptOfferRequest.getFirstname();
        this.lastname = acceptOfferRequest.getLastname();
        this.email = acceptOfferRequest.getEmail();
    }

    public String getReservationId() {
        return reservationId;
    }

    public AcceptOfferRequest getAcceptOfferRequest() {
        return acceptOfferRequest;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Integer getTotalAsPennies(){
        return total.multiply(new BigDecimal("100")).intValue();
    }

    public static RegisterPaymentRequest of(String reservationId, AcceptOfferRequest acceptOfferRequest, BigDecimal total) {
        return new RegisterPaymentRequest(reservationId, acceptOfferRequest, total);
    }
}
