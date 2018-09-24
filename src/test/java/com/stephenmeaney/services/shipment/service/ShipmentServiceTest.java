package com.stephenmeaney.services.shipment.service;

import com.stephenmeaney.services.shipment.data.entity.Shipment;
import com.stephenmeaney.services.shipment.data.repository.ShipmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShipmentService.class})
public class ShipmentServiceTest {

    @MockBean
    private ShipmentRepository mockShipmentRepository;

    @Autowired
    private ShipmentService shipmentService;

    private Shipment createMockShipment(long num) {
        Shipment mockShipment = new Shipment();
        mockShipment.setShipmentId(num);
        mockShipment.setShippedDate(LocalDate.parse("2001-01-01"));
        mockShipment.setDeliveryDate(LocalDate.parse("2002-02-02"));
        mockShipment.setShipmentId(num);
        mockShipment.setShippingAddressId(num);

        return mockShipment;
    }

    @Test
    public void testGetById() {
        Shipment mockShipment = createMockShipment(1L);

        when(mockShipmentRepository.findById(anyLong())).thenReturn(mockShipment);

        Shipment returnedShipment = shipmentService.getById(1L);

        assertThat(returnedShipment.getShipmentId()).isEqualTo(1L);
        assertThat(returnedShipment.getShippedDate()).isEqualTo("2001-01-01");
        assertThat(returnedShipment.getDeliveryDate()).isEqualTo("2002-02-02");
        assertThat(returnedShipment.getShipmentId()).isEqualTo(1L);
        assertThat(returnedShipment.getShippingAddressId()).isEqualTo(1L);
    }

    @Test
    public void testGetAll() {
        Shipment mockShipment = createMockShipment(2);
        List<Shipment> mockShipmentList = new ArrayList<>();
        mockShipmentList.add(mockShipment);

        when(mockShipmentRepository.findAll()).thenReturn(mockShipmentList);

        List<Shipment> returnedShipmentList = shipmentService.getAll();

        assertThat(returnedShipmentList.get(0).getShipmentId()).isEqualTo(2L);
        assertThat(returnedShipmentList.get(0).getShippedDate()).isEqualTo("2001-01-01");
        assertThat(returnedShipmentList.get(0).getDeliveryDate()).isEqualTo("2002-02-02");
        assertThat(returnedShipmentList.get(0).getShipmentId()).isEqualTo(2L);
        assertThat(returnedShipmentList.get(0).getShippingAddressId()).isEqualTo(2L);

    }

    @Test
    public void testInsert() {
        Shipment mockShipment = createMockShipment(3);

        when(mockShipmentRepository.save(any(Shipment.class))).thenReturn(mockShipment);

        Shipment returnedShipment = shipmentService.insert(mockShipment);

        assertThat(returnedShipment.getShipmentId()).isEqualTo(3L);
        assertThat(returnedShipment.getShippedDate()).isEqualTo("2001-01-01");
        assertThat(returnedShipment.getDeliveryDate()).isEqualTo("2002-02-02");
        assertThat(returnedShipment.getShipmentId()).isEqualTo(3L);
        assertThat(returnedShipment.getShippingAddressId()).isEqualTo(3L);
    }

    @Test
    public void testUpdate() {
        Shipment mockNewShipment = new Shipment();
        mockNewShipment.setAccountId(4);
        mockNewShipment.setShippingAddressId(4);

        when(mockShipmentRepository.save(any(Shipment.class))).then(returnsFirstArg());

        Shipment updatedShipment = shipmentService.update(5L, mockNewShipment);

        assertThat(updatedShipment.getShipmentId()).isEqualTo(5L);
        assertThat(updatedShipment.getShippedDate()).isNull();
        assertThat(updatedShipment.getDeliveryDate()).isNull();
        assertThat(updatedShipment.getAccountId()).isEqualTo(4L);
        assertThat(updatedShipment.getShippingAddressId()).isEqualTo(4L);
    }

    @Test
    public void testDelete() {
        shipmentService.delete(1);
        verify(mockShipmentRepository, times(1)).deleteById(anyLong());
    }
}