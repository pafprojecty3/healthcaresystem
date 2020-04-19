package com.healthCareManagementSystemPayment.controller;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
import com.healthCareManagementSystemPayment.model.docpayment;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/docpay")
public class DocpaymentService {
	
	docpayment docpaymentObj = new docpayment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readdocpayment() {
		return docpaymentObj.readdocpayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertdocpayment(@FormParam("PaymentID") String PaymentID, @FormParam("Paymentcode") String Paymentcode,
			@FormParam("DocID") String DocID, @FormParam("DocName") String DocName, @FormParam("PaymentType") String PaymentType, @FormParam("Amount") String Amount, @FormParam("DateOfPayed") String DateOfPayed) {
		String output = docpaymentObj.insertdocpayment(PaymentID,Paymentcode, DocID, DocName, PaymentType,Amount,DateOfPayed);
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatedocpayment(String docpaymentData)
	{
	//Convert the input string to a JSON object
	JsonObject docpaymentObject = new JsonParser().parse(docpaymentData).getAsJsonObject();
	//Read the values from the JSON object
	String PaymetID = docpaymentObject.get("PaymetID").getAsString();
	String Paymentcode = docpaymentObject.get("Paymentcode").getAsString();
	String DocID = docpaymentObject.get("DocID").getAsString();
	String DocName = docpaymentObject.get("DocName").getAsString();
	String PaymentType = docpaymentObject.get("PaymentType").getAsString();
	String Amount = docpaymentObject.get("Amount").getAsString();
	String DateOfPayed = docpaymentObject.get("DateOfPayed").getAsString();
	String output = docpaymentObj.updatedocpayment(PaymetID, Paymentcode, DocID, DocName, PaymentType, Amount, DateOfPayed );
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletedocpayment(String docpaymentData)
	{
			//convert the input string to an XML document
			Document doc = Jsoup.parse(docpaymentData,"",Parser.xmlParser());
			
			//Read the value from the element <adminID>
			String PaymetID = doc.select("PaymetID").text();
			
			String output = docpaymentObj.deletedocpayment(PaymetID);
			
			return output;
	}
	
}
