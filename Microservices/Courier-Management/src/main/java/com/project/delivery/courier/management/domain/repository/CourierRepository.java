package com.project.delivery.courier.management.domain.repository;

import com.project.delivery.courier.management.domain.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CourierRepository extends JpaRepository<Courier, UUID> {
    Optional<Courier> findTop1ByOrderByLastFulfilledDeliveryAtAsc();

    Optional<Courier> findByPendingDeliveries_id(UUID deliveryId);
}
