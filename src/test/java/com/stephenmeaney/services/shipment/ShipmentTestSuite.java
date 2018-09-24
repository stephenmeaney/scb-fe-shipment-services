package com.stephenmeaney.services.shipment;

import com.stephenmeaney.services.shipment.api.ShipmentControllerTest;
import com.stephenmeaney.services.shipment.data.repository.ShipmentRepositoryIntegrationTest;
import com.stephenmeaney.services.shipment.data.repository.ShipmentRepositoryTest;
import com.stephenmeaney.services.shipment.service.ShipmentServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses ({ShipmentControllerTest.class, ShipmentRepositoryTest.class, ShipmentRepositoryIntegrationTest.class, ShipmentServiceTest.class})
public class ShipmentTestSuite {
}
