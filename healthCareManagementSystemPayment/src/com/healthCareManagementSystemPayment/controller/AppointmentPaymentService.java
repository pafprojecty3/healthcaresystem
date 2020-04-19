package com.healthCareManagementSystemPayment.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.healthCareManagementSystemPayment.model.docpayment;

@Path("/apppay")
public class AppointmentPaymentService{
	docpayment AppointmentPaymentObj = new docpayment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointmentPayment() {
		return AppointmentPaymentObj.readAppointmentPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointmentPayment(@FormParam("AppointentNo") String AppointentNo, @FormParam("PatientNIC") String PatientNIC,
			@FormParam("PatientName") String PatientName, @FormParam("TellNo") String TellNo,@FormParam("HospitalName") String HospitalName,@FormParam("DoctorName") String DoctorName,@FormParam("Date") String Date,@FormParam("DoctorCharges") String DoctorCharges,@FormParam("HospitalCharges") String HospitalCharges,@FormParam("FullPayment") String FullPayment)
	{
		String output = AppointmentPaymentObj.insertAppointmentPayment(AppointentNo, PatientNIC, PatientName, TellNo, HospitalName, DoctorName, Date, DoctorCharges, HospitalCharges, FullPayment);
		return output;
	}

	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointmentPayment(String AppointmentPaymentData)
	{
	//Convert the input string to a JSON object
	JsonObject AppointmentPaymentObject = new JsonParser().parse(AppointmentPaymentData).getAsJsonObject();
	//Read the values from the JSON object
	String AppointentNo = AppointmentPaymentObject.get("AppointentNo").getAsString();
	String PatientNIC = AppointmentPaymentObject.get("PatientNIC").getAsString();
	String PatientName = AppointmentPaymentObject.get("PatientName").getAsString();
	String TellNo = AppointmentPaymentObject.get("TellNo").getAsString();
	String HospitalName = AppointmentPaymentObject.get("HospitalName").getAsString();
	String DoctorName = AppointmentPaymentObject.get("DoctorName").getAsString();
	String Date = AppointmentPaymentObject.get("Date").getAsString();
	String DoctorCharges = AppointmentPaymentObject.get("DoctorCharges").getAsString();
	String HospitalCharges = AppointmentPaymentObject.get("HospitalCharges").getAsString();
	String FullPayment = AppointmentPaymentObject.get("FullPayment").getAsString();
	String output = AppointmentPaymentObj.updateAppointmentPayment(AppointentNo, PatientNIC, PatientName, TellNo, HospitalName, DoctorName, Date, DoctorCharges, HospitalCharges, FullPayment);
	return output;
	}
	
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointmentPayment(String AppointmentPaymentData)
	{
			//convert the input string to an XML document
			Document doc = Jsoup.parse(AppointmentPaymentData,"",Parser.xmlParser());
			
			//Read the value from the element <adminID>
			String AppointentNo = doc.select("AppointentNo").text();
			
			String output = AppointmentPaymentObj.deletedocpayment(AppointentNo);
			
			return output;
	}
	
}
