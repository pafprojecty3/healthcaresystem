package com.healthCareManagementSystemHospital.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.healthCareManagementSystemHospital.model.Hospital;
@Path("secured") 
public class Security {
	 

		Hospital sheduleObj = new Hospital();
		@GET
		@Path("message")
		@Produces(MediaType.TEXT_PLAIN)
			public String securedMethod()
			{
			    return ("sucessfuly loged");
			}
		}


