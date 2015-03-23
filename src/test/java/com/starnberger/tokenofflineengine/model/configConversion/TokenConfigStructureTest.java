/**
 * 
 */
package com.starnberger.tokenofflineengine.model.configConversion;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.fest.assertions.AssertExtension;
import org.junit.Test;

import com.starnberger.tokenofflineengine.model.SensorConfigValue;
import com.starnberger.tokenofflineengine.model.TokenConfiguration;

/**
 * @author Roman Kaufmann
 *
 */
public class TokenConfigStructureTest {

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
		TokenConfigStructure tokenConfigStructure = new TokenConfigStructure(new TokenConfiguration(),
				new HashMap<String, Map<String, SensorConfigValue>>());
		byte[] byteArray = tokenConfigStructure.toByteArray(false);
		assertEquals(165, byteArray.length);
	}

}
