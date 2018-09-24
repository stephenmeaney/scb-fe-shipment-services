package com.stephenmeaney.services.shipment.api;

import com.stephenmeaney.services.shipment.data.entity.Shipment;
import com.stephenmeaney.services.shipment.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    private ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping("")
    public List<Shipment> getAll() {
        return shipmentService.getAll();
    }

    @GetMapping("/{id}")
    public Shipment getById(@PathVariable long id) {
        return shipmentService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<Shipment> insert(@RequestBody Shipment shipment) {
        return new ResponseEntity<>(shipmentService.insert(shipment), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Shipment update(@PathVariable long id, @RequestBody Shipment shipment) {
        return shipmentService.update(id, shipment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Shipment> delete(@PathVariable long id) {
        shipmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
