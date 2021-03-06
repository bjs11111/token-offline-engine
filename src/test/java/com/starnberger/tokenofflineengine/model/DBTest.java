/**
 * 
 */
package com.starnberger.tokenofflineengine.model;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.starnberger.tokenofflineengine.common.ITokenConfiguration;
import com.starnberger.tokenofflineengine.common.ITokenModel;
import com.starnberger.tokenofflineengine.dao.EMF;

/**
 * @author protoss78
 *
 */
public class DBTest {
	private static final Logger logger = Logger.getLogger("DBTest");
	private static EntityManagerFactory emf;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DBTest.emf = EMF.get();
		logger.debug("setup");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logger.debug("tear down");
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
		ITokenModel token = new TokenModel();
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
	public void testGC() {
		EntityManager em = DBTest.emf.createEntityManager();
		GatewayConfiguration gatewayConfiguration = new GatewayConfiguration();
		em.persist(gatewayConfiguration);
		em.close();
	}

	@Test
	public void testGSC() {
		EntityManager em = DBTest.emf.createEntityManager();
		SensorConfiguration genericSensorConfiguration = new SensorConfiguration();
		em.persist(genericSensorConfiguration);
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
	public void testTokenConfiguration() {
		EntityManager em = DBTest.emf.createEntityManager();
		ITokenConfiguration tokenConfiguration = new TokenConfiguration();
		em.persist(tokenConfiguration);
		em.close();
	}

	/*@Test
	public void testJpaLog() {
		EntityManager em = DBTest.emf.createEntityManager();
		em.getTransaction().begin();
		JpaLogEntity jpaLogEntity = new JpaLogEntity();
		em.persist(jpaLogEntity);
		em.getTransaction().commit();
		em.close();
	}*/

	@Test
	public void testList() {
		EntityManager em = DBTest.emf.createEntityManager();
		em.getTransaction().begin();
		ITokenConfiguration tokenConfiguration = new TokenConfiguration();
		SensorConfiguration sensorConfiguration = new SensorConfiguration();
		em.persist(sensorConfiguration);
		em.flush();
		HashSet<Long> sensorConfigKeys = new HashSet<Long>();
		sensorConfigKeys.add(sensorConfiguration.getId());
		tokenConfiguration.setSensorConfigKeys(sensorConfigKeys);
		em.persist(tokenConfiguration);
		em.getTransaction().commit();

		TypedQuery<TokenConfiguration> findAllQuery = em.createQuery("SELECT s FROM TokenConfiguration s",
				TokenConfiguration.class);
		final List<TokenConfiguration> results = findAllQuery.getResultList();
		String message = "Count: " + results.size();
		System.out.println(message);
		logger.debug(message);
		em.close();
	}

}
