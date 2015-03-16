package com.starnberger.tokenofflineengine.dao;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import com.starnberger.tokenofflineengine.AuthHttpHeader;
import com.starnberger.tokenofflineengine.GatewayInfo;
import com.starnberger.tokenofflineengine.model.Gateway;

public class AuthRequestFilter implements ClientRequestFilter {

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		checkToken();
		requestContext.getHeaders().add(AuthHttpHeader.SERVICE_KEY, GatewayInfo.getInstance().SERVICE_TOKEN);
		requestContext.getHeaders().add(AuthHttpHeader.AUTH_TOKEN, GatewayInfo.getInstance().MY_TOKEN);
	}

	/**
	 * 
	 */
	protected void checkToken() {
		if (GatewayInfo.getInstance().SERVICE_TOKEN == null) {
			Gateway me = GatewayManager.getInstance().findMe();
			if (me != null)
				GatewayInfo.getInstance().SERVICE_TOKEN = me.getGatewayToken();
		}
	}

}
