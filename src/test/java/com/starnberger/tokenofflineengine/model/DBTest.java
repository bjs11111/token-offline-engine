/**
 * 
 */
package com.starnberger.tokenofflineengine.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author protoss78
 *
 */
public class DBTest {
	private static EntityManagerFactory emf;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DBTest.emf = EMF.get();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@Test
	public void testToken() {
		EntityManager em = DBTest.emf.createEntityManager();
		Token token = new Token();
		em.persist(token);
		em.close();
	}

	@Test
	public void testTokenModel() {
		EntityManager em = DBTest.emf.createEntityManager();
		TokenModel token = new TokenModel();
		em.persist(token);
		em.close();
	}

	@Test
	public void testGateway() {
		EntityManager em = DBTest.emf.createEntityManager();
		Gateway gateway = new Gateway();
		em.persist(gateway);
		em.close();
	}

	@Test
	public void testAC() {
		EntityManager em = DBTest.emf.createEntityManager();
		AccelerationConfiguration accelerationConfiguration = new AccelerationConfiguration();
		em.persist(accelerationConfiguration);
		em.close();
	}

	@Test
	public void testGC() {
		EntityManager em = DBTest.emf.createEntityManager();
		GatewayConfiguration gatewayConfiguration = new GatewayConfiguration();
		em.persist(gatewayConfiguration);
		em.close();
	}

	@Test
	public void testGSC() {
		EntityManager em = DBTest.emf.createEntityManager();
		GenericSensorConfiguration genericSensorConfiguration = new GenericSensorConfiguration();
		em.persist(genericSensorConfiguration);
		em.close();
	}

	@Test
	public void testHSC() {
		EntityManager em = DBTest.emf.createEntityManager();
		HumidityConfiguration humidityConfiguration = new HumidityConfiguration();
		em.persist(humidityConfiguration);
		em.close();
	}

	@Test
	public void testPIR() {
		EntityManager em = DBTest.emf.createEntityManager();
		PIRConfiguration pirConfiguration = new PIRConfiguration();
		em.persist(pirConfiguration);
		em.close();
	}

	@Test
	public void testSensorData() {
		EntityManager em = DBTest.emf.createEntityManager();
		SensorData sensorData = new SensorData();
		em.persist(sensorData);
		em.close();
	}

	@Test
	public void testSensorType() {
		EntityManager em = DBTest.emf.createEntityManager();
		SensorType sensorType = new SensorType();
		em.persist(sensorType);
		em.close();
	}

	@Test
	public void testTC() {
		EntityManager em = DBTest.emf.createEntityManager();
		TemperatureConfiguration temperatureConfiguration = new TemperatureConfiguration();
		em.persist(temperatureConfiguration);
		em.close();
	}

	@Test
	public void testTokenConfiguration() {
		EntityManager em = DBTest.emf.createEntityManager();
		TokenConfiguration tokenConfiguration = new TokenConfiguration();
		em.persist(tokenConfiguration);
		em.close();
	}

}
