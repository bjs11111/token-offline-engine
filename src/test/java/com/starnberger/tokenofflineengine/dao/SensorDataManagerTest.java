package com.starnberger.tokenofflineengine.dao;

import java.util.Date;

import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import com.starnberger.tokenofflineengine.model.SensorData;

public class SensorDataManagerTest {

	private static final Logger logger = LogManager.getLogger("SensorDataManagerTest");
	private static EntityManagerFactory emf;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SensorDataManagerTest.emf = EMF.get();
		logger.debug("setup");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		emf.close();
		logger.debug("tear down");
	}

	@Test
	public void testAddNewRecord() {
		Double value = Double.valueOf(13.2);
		Date timeStamp = new Date();
		SensorData result = SensorDataManager.getInstance().addNewRecord("test", "1", timeStamp, value, null, null, false);
		assertNotNull(result);
		assertEquals(false, result.isAlarm());
		assertEquals(value, result.getValue1());
		assertEquals(timeStamp, result.getTimestamp());
		assertNull(result.getValue2());
		assertNull(result.getValue3());
		assertNull(result.getToken());
		assertNull(result.getSensorType());
		assertNotNull(result.getId());
	}
}
