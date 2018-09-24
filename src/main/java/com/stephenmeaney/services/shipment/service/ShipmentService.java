package com.stephenmeaney.services.shipment.service;

import com.stephenmeaney.services.shipment.data.entity.Shipment;
import com.stephenmeaney.services.shipment.data.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    private ShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public Shipment getById(long id) {
        return shipmentRepository.findById(id);
    }

    public List<Shipment> getAll() {
        return shipmentRepository.findAll();
    }

    public Shipment insert(Shipment account) {
        return shipmentRepository.save(account);
    }

    public Shipment update(long id, Shipment newAccount) {
        // would normally check contract for "id not found" behavior and how to handle incomplete entity
        newAccount.setShipmentId(id);
        return shipmentRepository.save(newAccount);
    }

    public void delete(long id) {
        shipmentRepository.deleteById(id);
    }
}
