package com.stephenmeaney.services.shipment.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stephenmeaney.services.shipment.data.entity.Shipment;
import com.stephenmeaney.services.shipment.service.ShipmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ShipmentController.class, secure = false)
public class ShipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipmentService shipmentService;

    @Autowired
    private ObjectMapper mapper;

    private Shipment createMockShipment(long num) {
        Shipment mockShipment = new Shipment();
        mockShipment.setShipmentId(num);
        mockShipment.setShippedDate(LocalDate.parse("2011-01-01"));
        mockShipment.setDeliveryDate(LocalDate.parse("2012-02-02"));
        mockShipment.setAccountId(num);
        mockShipment.setShippingAddressId(num);

        return mockShipment;
    }

    @Test
    public void testGetAll() throws Exception {

        List<Shipment> mockShipmentList = new ArrayList<>();
        mockShipmentList.add(createMockShipment(1L));

        when(shipmentService.getAll()).thenReturn(mockShipmentList);

        mockMvc.perform(get("/api/v1/shipments"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetById() throws Exception {

        Shipment mockShipment = createMockShipment(2);

        when(shipmentService.getById(2)).thenReturn(mockShipment);

        mockMvc.perform(get("/api/v1/shipments/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testInsert() throws Exception {

        Shipment mockShipment = createMockShipment(4);

        when(shipmentService.insert(any(Shipment.class))).thenReturn(mockShipment);

        mockMvc.perform(post("/api/v1/shipments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(mockShipment)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {

        Shipment mockShipment = createMockShipment(1);

        when(shipmentService.update(anyLong(), any(Shipment.class))).thenReturn(mockShipment);

        mockMvc.perform(put("/api/v1/shipments/2")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(mockShipment)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {

        mockMvc.perform(delete("/api/v1/shipments/3"))
                .andExpect(status().isNoContent());
    }

}