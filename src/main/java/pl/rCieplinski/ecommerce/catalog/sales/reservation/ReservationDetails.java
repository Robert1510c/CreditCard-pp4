package pl.rCieplinski.ecommerce.catalog.sales.reservation;

import java.math.BigDecimal;

public class ReservationDetails {

    private String reservationId;
    private String paymentUrl;


    public ReservationDetails(String reservationId, String paymentUrl) {
        this.reservationId = reservationId;
        this.paymentUrl = paymentUrl;
    }
    public ReservationDetails() {
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }
}
