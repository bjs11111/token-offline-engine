/**
 * 
 */
package com.starnberger.tokenofflineengine.model.configConversion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.codec.binary.Hex;
import org.junit.BeforeClass;
import org.junit.Test;

import com.starnberger.EMF;
import com.starnberger.tokenofflineengine.dao.TokenConfigurationManager;
import com.starnberger.tokenofflineengine.dao.TokenManager;
import com.starnberger.tokenofflineengine.model.SensorConfigValue;
import com.starnberger.tokenofflineengine.model.Token;
import com.starnberger.tokenofflineengine.model.TokenConfiguration;

/**
 * @author Roman Kaufmann
 *
 */
public class TokenConfigStructureTest {
	public static EntityManagerFactory emf = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emf = EMF.get();
	}


	/**
	 * Test method for
	 * {@link com.starnberger.tokenofflineengine.model.configConversion.TokenConfigStructure#TokenConfigStructure(com.starnberger.tokenofflineengine.model.TokenConfiguration, java.util.Map)}
	 * .
	 */
	@Test
	public void testTokenConfigStructure() {
		assertNotNull(new TokenConfigStructure(new TokenConfiguration(),
				new HashMap<String, Map<String, SensorConfigValue>>()));
	}

	/**
	 * Test method for
	 * {@link com.starnberger.tokenofflineengine.model.configConversion.TokenConfigStructure#toByteArray(boolean)}
	 * .
	 */
	@Test
	public void testToByteArray() {
		Token token = TokenManager.getInstance().findByMac("CA:FF:E0:0C:AF:FE");
		TokenConfiguration config = TokenConfigurationManager.getInstance().findByRemoteId(5040161301725184L);
		byte[] byteArray =TokenConfigurationManager.getInstance().generateByteArrayFromConfig(config, token);
		System.out.println(Hex.encodeHexString(byteArray));
		assertEquals(180, byteArray.length);
	}

}
