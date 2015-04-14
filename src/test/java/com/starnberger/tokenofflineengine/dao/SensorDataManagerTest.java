package com.starnberger.tokenofflineengine.dao;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class SensorDataManagerTest {

	private static final Logger logger = Logger.getLogger("SensorDataManagerTest");

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.debug("setup");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.debug("tear down");
	}

	//@Test
	/*public void testAddNewRecord() {
		Gateway gateway = new Gateway();
		gateway.setRemoteId(1L);
		Double value = Double.valueOf(13.2);
		Date timeStamp = new Date();
		SensorData result = SensorDataManager.getInstance().addNewRecord("test", "1", timeStamp, value, null, null, false, gateway);
		assertNotNull(result);
		assertEquals(false, result.isAlarm());
		assertEquals(value, result.getValue1());
		assertEquals(timeStamp, result.getTimestamp());
		assertNull(result.getValue2());
		assertNull(result.getValue3());
		assertNull(result.getToken());
		assertNull(result.getSensorType());
		assertNotNull(result.getId());
	}*/
}
