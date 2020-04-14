package com.healthCareManagementSystem.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//For JSON
//For XML
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.healthCareManagementSystem.model.Hospital;


@Path("/Hospital")
public class HospitalService {
	
		Hospital sheduleObj = new Hospital();

		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String viewAllShedules() {
			return sheduleObj.viewAllShedules();
		}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addSchedule(@FormParam("hospitalCode") String hospitalCode , @FormParam("doctorName") String doctorName,
			@FormParam("roomNo") String roomNo, @FormParam("timeFrom") String timeFrom, @FormParam("timeTo") String timeTo,@FormParam("date") String date ){
		String output = sheduleObj.addSchedule(hospitalCode, doctorName, roomNo, timeFrom, timeTo, date);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctorShedule(String itemData)
	{
	//Convert the input string to a JSON object
	JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	String hospitalID = itemObject.get("hospitalID").getAsString();
	String hospitalCode = itemObject.get("hospitalCode").getAsString();
	String doctorName = itemObject.get("doctorName").getAsString();
	String roomNo = itemObject.get("roomNo").getAsString();
	String timeFrom = itemObject.get("timeFrom").getAsString();
	String timeTo = itemObject.get("timeTo").getAsString();
	String date = itemObject.get("date").getAsString();
	String output = sheduleObj.updateDoctorShedule(hospitalID, hospitalCode, doctorName, roomNo, timeFrom, timeTo, date);
	return output;
	}

}
