package com.starnberger.tokenofflineengine.model.configConversion;

import net.sourceforge.juint.Int8;
import net.sourceforge.juint.UInt16;
import net.sourceforge.juint.UInt8;

import org.apache.commons.lang3.ArrayUtils;

import com.starnberger.tokenofflineengine.model.TokenConfiguration;

/**
 * @author Roman Kaufmann
 *
 */
public class BleGeneral {
	private UInt16 advertisingInterval = null;
	private UInt8 bondable = null;
	private Int8 txPower = null;
	private UInt8 advertisingAlwaysEnabled = null;

	/**
	 * @param tokenConfiguration
	 */
	public BleGeneral(TokenConfiguration tokenConfiguration) {
		advertisingInterval = new UInt16(tokenConfiguration.getBleAdvertisingInterval());
		bondable = new UInt8(tokenConfiguration.getBleBondableInterval());
		txPower = new Int8(tokenConfiguration.getBleTxPower());
		if (tokenConfiguration.isBleAdvertisingConditionAlways())
			advertisingAlwaysEnabled = new UInt8(1);
		else
			advertisingAlwaysEnabled = new UInt8(0);
	}

	/**
	 * @param bigEndian
	 * @return
	 */
	public byte[] toByteArray(boolean bigEndian) {
		byte[] advertising = null;
		byte bondableByteValue;
		byte powerByte;
		byte alwaysByteValue;
		if (bigEndian) {
			advertising = advertisingInterval.toBigEndian();
		} else {
			advertising = advertisingInterval.toLittleEndian();
		}
		bondableByteValue = bondable.byteValue();
		powerByte = txPower.byteValue();
		alwaysByteValue = advertisingAlwaysEnabled.byteValue();
		byte[] result = ArrayUtils.add(advertising, bondableByteValue);
		result = ArrayUtils.add(result, powerByte);
		result = ArrayUtils.add(result, alwaysByteValue);
		result = ArrayUtils.add(result, (byte) 255);
		return result;
	}
}
