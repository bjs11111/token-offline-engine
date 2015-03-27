/**
 * 
 */
package com.starnberger.tokenofflineengine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Roman Kaufmann
 *
 */
public final class GatewayInfo {
	private static final GatewayInfo INSTANCE = new GatewayInfo();
	public static String SERVER_URL = null;
	private static boolean isIdFromFile = false;
	public static String CPUID = "0003-06C3-0000-0000-0000-0001"; // Test number
	public String SERVICE_TOKEN = "f80ebc87-ad5c-4b29-9366-5359768df5a1"; // Generated
																			// default
																			// key.
																			// Should
																			// be
																			// changed
																			// between
																			// multiple
																			// versions
	public String MY_TOKEN = null;

	/**
	 * @return
	 */
	public static GatewayInfo getInstance() {
		return INSTANCE;
	}

	/**
	 * @return
	 */
	public String getServerUrl() {
		if (SERVER_URL != null)
			return SERVER_URL;
		// Set default value
		SERVER_URL = "http://tokenengine:8080/rest/v1/";
		// Otherwise read it from the local file
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("./url.id"));
			SERVER_URL = br.readLine();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SERVER_URL;
	}

	/**
	 * @return
	 */
	public String getUuid() {
		if (isIdFromFile)
			return CPUID;

		// Otherwise read it from the local file
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("./gateway.id"));
			CPUID = br.readLine();
			isIdFromFile = true;
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CPUID;
	}
}
