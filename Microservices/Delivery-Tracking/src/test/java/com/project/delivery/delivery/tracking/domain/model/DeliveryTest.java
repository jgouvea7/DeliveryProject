package com.project.delivery.delivery.tracking.domain.model;

import com.project.delivery.delivery.tracking.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    public void shouldChangeToPlaced() {
        Delivery delivery = Delivery.draft();

        delivery.editPreparationDetails(createValidPreparationDetails());

        delivery.place();

        assertEquals(DeliveryStatus.WAITING_FOR_COURIER, delivery.getStatus());
        assertNotNull(delivery.getPlacedAt());
    }

    @Test
    public void shouldNotPlace() {
        Delivery delivery = Delivery.draft();

        assertThrows(DomainException.class, () -> {
            delivery.place();
        });

        assertEquals(DeliveryStatus.DRAFT, delivery.getStatus());
        assertNull(delivery.getPlacedAt());
    }



    private Delivery.PreparationDetails createValidPreparationDetails() {
        ContactPoint sender = ContactPoint.builder()
                .zipCode("02190-000")
                .street("Rua Pedra nos Caminhos")
                .number("100")
                .complement("Mercado Extra")
                .name("Jonnathas")
                .phone("(11) 96352-8876")
                .build();

        ContactPoint recipient = ContactPoint.builder()
                .zipCode("15690-002")
                .street("Rua da Tarde")
                .number("300")
                .complement("Praça aurora")
                .name("Roberto")
                .phone("(11) 92461-7778")
                .build();

        return Delivery.PreparationDetails.builder()
                .sender(sender)
                .recipient(recipient)
                .distanceFee(new BigDecimal("15.00"))
                .courierPayout(new BigDecimal("6.00"))
                .expectedDeliveryTime(Duration.ofHours(1))
                .build();
    }
}