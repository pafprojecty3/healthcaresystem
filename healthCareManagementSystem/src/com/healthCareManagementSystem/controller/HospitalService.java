package com.healthCareManagementSystem.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.healthCareManagementSystem.model.Hospital;


@Path("/Hospital")
public class HospitalService {
	
		Hospital sheduleObject = new Hospital();

		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String viewAllShedules() {
			return sheduleObject.viewAllShedules();
		}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addSchedule(@FormParam("hospitalCode") String hospitalCode , @FormParam("doctorName") String doctorName,
			@FormParam("roomNo") String roomNo, @FormParam("timeFrom") String timeFrom, @FormParam("timeTo") String timeTo,@FormParam("date") String date ){
		String output = sheduleObject.addSchedule(hospitalCode, doctorName, roomNo, timeFrom, timeTo, date);
		return output;
	}

}
