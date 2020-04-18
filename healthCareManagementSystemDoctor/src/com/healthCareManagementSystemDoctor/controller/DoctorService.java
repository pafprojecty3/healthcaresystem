package com.healthCareManagementSystemDoctor.controller;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
import com.healthCareManagementSystemDoctor.model.Doctor;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Doctors")
public class DoctorService {
	Doctor doctorObj = new Doctor();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
	
		return doctorObj.readDoctors();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctors(@FormParam("doctorCode") String doctorCode, @FormParam("doctorName") String doctorName,
			@FormParam("doctorCharge") String doctorCharge, @FormParam("doctorDesc") String doctorDesc) {
		String output = doctorObj.insertDoctor(doctorCode, doctorName, doctorCharge, doctorDesc);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(String doctorData) {
		// Convert the input string to a JSON object
		JsonObject doctorObject = new JsonParser().parse(doctorData).getAsJsonObject();
		// Read the values from the JSON object
		String doctorID = doctorObject.get("doctorID").getAsString();
		String doctorCode = doctorObject.get("doctorCode").getAsString();
		String doctorName = doctorObject.get("doctorName").getAsString();
		String doctorCharge = doctorObject.get("doctorCharge").getAsString();
		String doctorDesc = doctorObject.get("doctorDesc").getAsString();
		String output = doctorObj.updateDoctor(doctorID, doctorCode, doctorName, doctorCharge, doctorDesc);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String doctorData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(doctorData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String doctorID = doc.select("doctorID").text();
		String output = doctorObj.deleteDoctor(doctorID);
		return output;
	}
}