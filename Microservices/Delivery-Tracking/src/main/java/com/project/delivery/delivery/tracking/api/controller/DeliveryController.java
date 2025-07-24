package com.project.delivery.delivery.tracking.api.controller;

import com.project.delivery.delivery.tracking.api.model.CourierIdInput;
import com.project.delivery.delivery.tracking.api.model.DeliveryInput;
import com.project.delivery.delivery.tracking.domain.model.Delivery;
import com.project.delivery.delivery.tracking.domain.repository.DeliveryRepository;
import com.project.delivery.delivery.tracking.domain.service.DeliveryChekpointService;
import com.project.delivery.delivery.tracking.domain.service.DeliveryPreparationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryPreparationService deliveryPreparationService;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryChekpointService deliveryChekpointService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery draft(@RequestBody @Valid DeliveryInput input) {
        return deliveryPreparationService.draft(input);
    }

    @PutMapping("/{deliveryId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery edit(@PathVariable UUID deliveryId,
            @RequestBody @Valid DeliveryInput input) {
        return deliveryPreparationService.edit(deliveryId, input);
    }

    @GetMapping
    public PagedModel<Delivery> findAll(@PageableDefault Pageable pageable) {
        return new PagedModel<>(deliveryRepository.findAll(pageable));
    }

    @GetMapping("/{deliveryId}")
    private Delivery findById(@PathVariable UUID deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{deliveryId}/placement")
    public void place(@PathVariable UUID deliveryId) {
        deliveryChekpointService.place(deliveryId);
    }

    @PostMapping("/{deliveryId}/pickups")
    public void pickup(@PathVariable UUID deliveryId,
                       @Valid @RequestBody CourierIdInput input) {
        deliveryChekpointService.pickup(deliveryId, input.getCourierId());
    }

    @PostMapping("/{deliveryId}/completion")
    public void complete(@PathVariable UUID deliveryId) {
        deliveryChekpointService.complete(deliveryId);
    }

}
