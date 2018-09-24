package com.stephenmeaney.services.shipment.data.repository;

import com.stephenmeaney.services.shipment.data.entity.Shipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends CrudRepository<Shipment, Long> {

    List<Shipment> findAll();

    Shipment findById(long id);
}
