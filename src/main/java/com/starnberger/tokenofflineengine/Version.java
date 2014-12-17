/**
 * 
 */
package com.starnberger.tokenofflineengine;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Roman Kaufmann
 *
 */
public final class Version {
	public static Version INSTANCE = new Version();

	public String version = "0.0.2-SNAPSHOT";

	/**
	 * 
	 */
	private Version() {
		try {
			Properties properties = new Properties();
			properties.load(Version.class.getClassLoader().getResourceAsStream(
					"META-INF/maven/com.starnberger/token-offline-engine/pom.properties"));
			setVersion(properties.getProperty("version"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

}
