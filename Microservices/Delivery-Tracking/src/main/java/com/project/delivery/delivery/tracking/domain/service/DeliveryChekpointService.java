package com.project.delivery.delivery.tracking.domain.service;

import com.project.delivery.delivery.tracking.domain.exception.DomainException;
import com.project.delivery.delivery.tracking.domain.model.Delivery;
import com.project.delivery.delivery.tracking.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryChekpointService {

    private final DeliveryRepository deliveryRepository;

    public void place(UUID deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new DomainException());

        delivery.place();
        deliveryRepository.saveAndFlush(delivery);
    }

    public void pickup(UUID deliveryId, UUID courierId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new DomainException());

        delivery.pickUp(courierId);
        deliveryRepository.saveAndFlush(delivery);

    }

    public void complete(UUID deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new DomainException());

        delivery.markAsDelivered();
        deliveryRepository.saveAndFlush(delivery);
    }


}
