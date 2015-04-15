/**
 * 
 */
package com.starnberger.tokenofflineengine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import org.apache.log4j.Logger;

/**
 * @author Roman Kaufmann
 *
 */
public final class GatewayInfo {
	private static final Logger logger = Logger.getLogger(GatewayInfo.class.getName());
	
	private static final String GATEWAY_ID = "./gateway.id";
	private static final GatewayInfo INSTANCE = new GatewayInfo();
	public static String SERVER_URL = null;
	private static boolean isIdFromFile = false;
	public static String CPUID = null; 
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
	public static synchronized GatewayInfo getInstance() {
		return INSTANCE;
	}

	/**
	 * @return
	 */
	public synchronized String getServerUrl() {
		if (SERVER_URL != null)
			return SERVER_URL;
		// Set default value
		SERVER_URL = "http://token-engine.appspot.com/rest/v1/";
		// Otherwise read it from the local file
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("./url.id"));
			SERVER_URL = br.readLine();
			br.close();
		} catch (FileNotFoundException e) {
			logger.fatal(e);
		} catch (IOException e) {
			logger.fatal(e);
		}
		return SERVER_URL;
	}

	/**
	 * @return
	 */
	public synchronized String getUuid() {
		if (isIdFromFile)
			return CPUID;

		// Otherwise read it from the local file
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(GATEWAY_ID));
			CPUID = br.readLine();
			isIdFromFile = true;
			br.close();
		} catch (FileNotFoundException e) {
			logger.fatal(e);
			CPUID = generateCPUID();
		} catch (IOException e) {
			logger.fatal(e);
			CPUID = generateCPUID();
		}
		return CPUID;
	}
	
	/**
	 * @return
	 */
	private synchronized String generateCPUID() {
		String uid = UUID.randomUUID().toString();
		
		// Write generated id to file
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(GATEWAY_ID));
			writer.write(uid);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			logger.fatal(e);
		}
		
		return uid;
	}
}
