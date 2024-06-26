package pl.rCieplinski.ecommerce.catalog.sales;

import pl.rCieplinski.ecommerce.catalog.sales.cart.Cart;
import pl.rCieplinski.ecommerce.catalog.sales.cart.CartStorage;
import pl.rCieplinski.ecommerce.catalog.sales.reservation.AcceptOfferRequest;
import pl.rCieplinski.ecommerce.catalog.sales.offering.Offer;
import pl.rCieplinski.ecommerce.catalog.sales.offering.OfferCalculator;
import pl.rCieplinski.ecommerce.catalog.sales.payment.PaymentDetails;
import pl.rCieplinski.ecommerce.catalog.sales.payment.PaymentGateway;
import pl.rCieplinski.ecommerce.catalog.sales.payment.RegisterPaymentRequest;
import pl.rCieplinski.ecommerce.catalog.sales.reservation.Reservation;
import pl.rCieplinski.ecommerce.catalog.sales.reservation.ReservationDetails;
import pl.rCieplinski.ecommerce.catalog.sales.reservation.ReservationRepository;

import java.util.UUID;

public class SalesFacade {
    private CartStorage cartStorage;
    private OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private ReservationRepository reservationRepository;

    public SalesFacade(CartStorage cartStorage, OfferCalculator offerCalculator, PaymentGateway paymentGateway, ReservationRepository reservationRepository) {
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservationRepository = reservationRepository;
    }


    public Offer getCurrentOffer(String customerId){

        Cart cart = loadCartForCustomer(customerId);
        return offerCalculator.calculate(cart.getLines());
    }

    public ReservationDetails acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                RegisterPaymentRequest.of(reservationId, acceptOfferRequest, offer.getTotal())
        );
        Reservation reservation = Reservation.of(
                reservationId,
                customerId,
                acceptOfferRequest,
                offer,
                paymentDetails);

        reservationRepository.add(reservation);

        return new ReservationDetails(reservationId, paymentDetails.getPaymentUrl());

    }

    public void addProduct(String customerId, String productId) {
        Cart cart = cartStorage.getForCustomer(customerId)
                .orElse(Cart.empty());

        cart.addProduct(productId);

        cartStorage.save(customerId, cart);
    }

    private Cart loadCartForCustomer(String customerId) {
        return cartStorage.getForCustomer(customerId)
                .orElse(Cart.empty());
    }
}
