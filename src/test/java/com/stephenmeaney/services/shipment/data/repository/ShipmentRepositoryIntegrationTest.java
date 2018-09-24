package com.stephenmeaney.services.shipment.data.repository;

import com.stephenmeaney.services.shipment.data.entity.Shipment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ShipmentRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Test
    public void testPersistData() {
        Shipment shipment = new Shipment();
        shipment.setAccountId(1L);
        shipment.setShippedDate(LocalDate.parse("2000-01-01"));
        shipment.setDeliveryDate(LocalDate.parse("2001-01-01"));
        shipment.setAccountId(1L);
        shipment.setShippingAddressId(1L);

        entityManager.persistAndFlush(shipment);

        Shipment foundShipment = shipmentRepository.findById(1L);

        assertThat(shipment.getShipmentId()).isEqualTo(1L);
        assertThat(shipment.getShippedDate()).isEqualTo("2000-01-01");
        assertThat(shipment.getDeliveryDate()).isEqualTo("2001-01-01");
        assertThat(shipment.getAccountId()).isEqualTo(1L);
        assertThat(shipment.getShippingAddressId()).isEqualTo(1L);
    }
}
