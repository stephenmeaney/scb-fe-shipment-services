package com.stephenmeaney.services.shipment.data.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.stephenmeaney.services.shipment.data.entity.Shipment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:test-datasets.xml")
public class ShipmentRepositoryTest {

    @Autowired
    ShipmentRepository shipmentRepository;

    @Test
    public void testRepositoryReturnsListOfShipments() {
        List<Shipment> shipmentList = shipmentRepository.findAll();
    }

    @Test
    public void testFindAll() {
        List<Shipment> shipmentList = shipmentRepository.findAll();

        assertThat(shipmentList.get(0).getShipmentId()).isEqualTo(1);
        assertThat(shipmentList.get(0).getShippedDate()).isEqualTo("2000-01-01");
        assertThat(shipmentList.get(0).getDeliveryDate()).isEqualTo("2001-01-01");
        assertThat(shipmentList.get(0).getAccountId()).isEqualTo(1);
        assertThat(shipmentList.get(0).getShippingAddressId()).isEqualTo(1);
    }

    @Test
    public void testFindById() {
        Shipment shipment = shipmentRepository.findById(1L);

        assertThat(shipment.getShipmentId()).isEqualTo(1);
        assertThat(shipment.getShippedDate()).isEqualTo("2000-01-01");
        assertThat(shipment.getDeliveryDate()).isEqualTo("2001-01-01");
        assertThat(shipment.getAccountId()).isEqualTo(1);
        assertThat(shipment.getShippingAddressId()).isEqualTo(1);
    }

    @Test
    public void testFindById_shipmentNotExist_returnsNull() {
        Shipment shipment = shipmentRepository.findById(9999);

        assertThat(shipment).isNull();
    }

}