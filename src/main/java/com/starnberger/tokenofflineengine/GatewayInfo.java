/**
 * 
 */
package com.starnberger.tokenofflineengine;

/**
 * @author Roman Kaufmann
 *
 */
public final class GatewayInfo {
	private static final GatewayInfo INSTANCE = new GatewayInfo(); 
	public static final String SERVER_URL = "http://127.0.0.1:8080/rest/v1/";
	public static String CPUID = "0003-06C3-0000-0000-0000-0000"; // Test number
	public String SERVICE_TOKEN = "f80ebc87-ad5c-4b29-9366-5359768df5a1"; // Generated key, differs from version to version
	public String MY_TOKEN = null;
	
	/**
	 * @return
	 */
	public static GatewayInfo getInstance() {
		return INSTANCE;
	}
}
