package com.project.delivery.courier.management.infrastructure.kafka;

import com.project.delivery.courier.management.domain.service.CourierDeliveryService;
import com.project.delivery.courier.management.infrastructure.event.DeliveryFulfilledIntegrationEvent;
import com.project.delivery.courier.management.infrastructure.event.DeliveryPlacedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = {
        "deliveries.v1.events"
}, groupId = "courier-management")
@Slf4j
@RequiredArgsConstructor
public class KafkaDeliveriesMessageHandler {

    private final CourierDeliveryService courierDeliveryService;

    @KafkaHandler(isDefault = true)
    public void defaultHandler(@Payload Object object) {
        log.info("Default Handler: {}", object);

    }

    @KafkaHandler
    public void handle(@Payload DeliveryPlacedIntegrationEvent event) {
        log.info("Recived: {}", event);
        courierDeliveryService.assign(event.getDeliveryId());
    }

    @KafkaHandler
    public void handle(@Payload DeliveryFulfilledIntegrationEvent event) {
        log.info("Recived: {}", event);
        courierDeliveryService.fulfill(event.getDeliveryId());
    }

}
