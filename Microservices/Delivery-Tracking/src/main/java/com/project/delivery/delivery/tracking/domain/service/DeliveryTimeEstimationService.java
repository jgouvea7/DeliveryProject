package com.project.delivery.delivery.tracking.domain.service;

import com.project.delivery.delivery.tracking.domain.model.ContactPoint;

public interface DeliveryTimeEstimationService {
    DeliveryEstimate estimate(ContactPoint sender, ContactPoint receiver);
}
