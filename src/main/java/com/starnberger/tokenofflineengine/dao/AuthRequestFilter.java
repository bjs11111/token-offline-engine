package com.starnberger.tokenofflineengine.dao;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import com.starnberger.tokenofflineengine.AuthHttpHeader;
import com.starnberger.tokenofflineengine.GatewayInfo;

public class AuthRequestFilter implements ClientRequestFilter {

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		requestContext.getHeaders().add(AuthHttpHeader.SERVICE_KEY, GatewayInfo.SERVICE_TOKEN);
		requestContext.getHeaders().add(AuthHttpHeader.AUTH_TOKEN, GatewayInfo.MY_TOKEN);
	}

}
