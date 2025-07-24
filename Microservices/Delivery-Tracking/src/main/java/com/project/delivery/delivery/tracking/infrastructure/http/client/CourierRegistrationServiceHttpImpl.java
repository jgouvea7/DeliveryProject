package com.project.delivery.delivery.tracking.infrastructure.http.client;

import com.project.delivery.delivery.tracking.domain.service.CourierPayoutCalculationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class CourierRegistrationServiceHttpImpl implements CourierPayoutCalculationService {

    private final CourierAPIClient courierAPIClient;

    @Override
    public BigDecimal calculatePayout(Double distanceInKm) {
        var courierPayoutResultModel = courierAPIClient.payoutCalculation(
                new CourierPayoutCalculationInput(distanceInKm));
        return courierPayoutResultModel.getPayoutFee();
    }
}
