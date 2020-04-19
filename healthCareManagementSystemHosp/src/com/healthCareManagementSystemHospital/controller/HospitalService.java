package com.healthCareManagementSystemHospital.controller;



	import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
	//For JSON
	import com.google.gson.*;
import com.healthCareManagementSystemHospital.model.Hospital;
import com.healthCareManagementSystemHospital.model.HospitalReg;

//For XML
	import org.jsoup.*;
	import org.jsoup.parser.*;
	import org.jsoup.nodes.Document;

	@Path("/Hospital")
	public class HospitalService {

		Hospital sheduleObj = new Hospital();
		HospitalReg hospObj = new HospitalReg();
		Boolean user = false;

		@GET
		@Path("views")
		@Produces(MediaType.TEXT_HTML)
		
		public String viewAllShedules() {
			if(user = false)
			{
				//Client client = ClientBuilder.newClient();
				//WebTarget target = client.target("http://localhost:8080/healthCareManagementSystemHospital/Security/secured/message");
				
		  }
		
			return sheduleObj.viewAllShedules();
		}
		public String viewDoctors() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/healthCareManagementSystemDoctor/DoctorService/Doctors/d");

				
				return  target.request(MediaType.TEXT_HTML).get(String.class);
				}

		

		@POST
		@Path("adds")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String addSchedule(@FormParam("hospitalCode") String hospitalCode,
				@FormParam("doctorName") String doctorName, @FormParam("roomNo") String roomNo,
				@FormParam("timeFrom") String timeFrom, @FormParam("timeTo") String timeTo,
				@FormParam("date") String date) {
			String output = sheduleObj.addSchedule(hospitalCode, doctorName, roomNo, timeFrom, timeTo, date);
			return output;
		}

		@PUT
		@Path("updates")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateShedule(String itemData) {
			// Convert the input string to a JSON object
			
			JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
			// Read the values from the JSON object
			String sheduleID = itemObject.get("sheduleID").getAsString();
			String hospitalCode = itemObject.get("hospitalCode").getAsString();
			String doctorName = itemObject.get("doctorName").getAsString();
			String roomNo = itemObject.get("roomNo").getAsString();
			String timeFrom = itemObject.get("timeFrom").getAsString();
			String timeTo = itemObject.get("timeTo").getAsString();
			String date = itemObject.get("date").getAsString();
			String output = sheduleObj.updateDoctorShedule(sheduleID, hospitalCode, doctorName, roomNo, timeFrom, timeTo,
					date);
			return output;
		}

		@DELETE
		@Path("deletes")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteItem(String itemData) {
			// Convert the input string to an XML document
			Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
			// Read the value from the element <itemID>
			String hospitalID = doc.select("itemID").text();
			String output = sheduleObj.deleteShedule(hospitalID);
			return output;
		}
	//get hospitals
		@GET
		@Path("viewh")
		@Produces(MediaType.TEXT_HTML)
		public String viewAllhospitals() {
			return hospObj.viewAllhospitals();
		}
		
		@POST
		@Path("addh")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String addHospital(@FormParam("hospitalCode") String hospitalCode,
				@FormParam("hospitalName") String hospitalName, @FormParam("address") String address,
				@FormParam("phoneNo") String phoneNo, @FormParam("email") String email) {
			String output = hospObj.addHospital(hospitalCode, hospitalName, address, phoneNo, email);
			return output;
		}

		@PUT
		@Path("updateh")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateHospital(String hospitalData) {
	//Convert the input string to a JSON object
			JsonObject itemObject = new JsonParser().parse(hospitalData).getAsJsonObject();
	//Read the values from the JSON object
			String hospitalID = itemObject.get("hospitalID").getAsString();
			String hospitalCode = itemObject.get("hospitalCode").getAsString();
			String hospitalName = itemObject.get("hospitalName").getAsString();
			String address = itemObject.get("address").getAsString();
			String phoneNo = itemObject.get("phoneNo").getAsString();
			String email = itemObject.get("email").getAsString();

			String output = hospObj.updateHospital(hospitalID, hospitalCode, hospitalName, address, phoneNo, email);
			return output;
		}

		@DELETE
		@Path("deleteh")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteHospital(String hospitalData) {
	//Convert the input string to an XML document
			Document doc = Jsoup.parse(hospitalData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
			String hospitalID = doc.select("itemID").text();
			String output = hospObj.deletehospital(hospitalID);
			return output;
		}
		
		
	}

	


