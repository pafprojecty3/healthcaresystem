package com.healthCareManagementSystemHospital.controller;
import com.healthCareManagementSystemHospital.model.HospitalReg;

import java.io.IOException;

import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;


import org.glassfish.jersey.internal.util.Base64;
@Provider
public class SecurityFilter implements ContainerRequestFilter {
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String ATHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URL_PREFIX = "secured";
	
	HospitalReg hos = new HospitalReg();
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		if(authHeader != null && authHeader.size() > 0)
		{
			String authToken = authHeader.get(0);
			authToken = authToken.replaceFirst(ATHORIZATION_HEADER_PREFIX, "");
			String decodedString = Base64.decodeAsString(authToken);
			StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
			String username = tokenizer.nextToken();
			String password = tokenizer.nextToken();
	
			
			Boolean val = hos.search(username, password);
			if (val == true)
			{
				return;
			}
		}
		Response unautherizedStatus = Response
											.status(Response.Status.UNAUTHORIZED)
											.entity("You cannot access the resourse please loging to the system")
											.build();
		
		requestContext.abortWith(unautherizedStatus);
												
		
		
	}
	

	}
}
