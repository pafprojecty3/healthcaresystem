package com.healthCareManagementSystemAdmin.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
import com.healthCareManagementSystemAdmin.model.Admin;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/admins")
public class AdminService {
	
	Admin adminObj = new Admin();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAdmin() {
		return adminObj.readAdmin();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAdmin(@FormParam("adminUsername") String adminUsername, @FormParam("adminPassword") String adminPassword, @FormParam("adminReports") String adminReports){
		String output = adminObj.insertAdmin(adminUsername, adminPassword, adminReports);
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAdmin(String adminData)
	{
	//Convert the input string to a JSON object
	JsonObject itemObject = new JsonParser().parse(adminData).getAsJsonObject();
	//Read the values from the JSON object
	String adminID = itemObject.get("adminID").getAsString();
	String adminUsername = itemObject.get("adminUsername").getAsString();
	String adminPassword = itemObject.get("adminPassword").getAsString();
	String adminReports= itemObject.get("adminReports").getAsString();
	
	String output = adminObj.updateAdmin(adminID, adminUsername, adminPassword,adminReports);
	return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAdmin(String adminData)
	{
			//convert the input string to an XML document
			Document doc = Jsoup.parse(adminData,"",Parser.xmlParser());
			
			//Read the value from the element <adminID>
			String adminID = doc.select("adminID").text();
			
			String output = adminObj.deleteAdmin(adminID);
			
			return output;
	}
}
