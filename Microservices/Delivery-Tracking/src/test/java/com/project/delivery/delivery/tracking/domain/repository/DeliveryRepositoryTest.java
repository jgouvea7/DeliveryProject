package com.project.delivery.delivery.tracking.domain.repository;

import com.project.delivery.delivery.tracking.domain.model.ContactPoint;
import com.project.delivery.delivery.tracking.domain.model.Delivery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeliveryRepositoryTest {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Test
    public void shouldPersist() {
        Delivery delivery = Delivery.draft();

        delivery.editPreparationDetails(createValidPreparationDetails());

        delivery.addItem("Computador", 2);
        delivery.addItem("Notebook", 2);

        deliveryRepository.saveAndFlush(delivery);

        Delivery persistedDelivery = deliveryRepository.findById(delivery.getId()).orElseThrow();

        assertEquals(2, persistedDelivery.getItems().size());
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