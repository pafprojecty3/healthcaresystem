package com.healthCareManagementSystemHospital.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class Hospital {
	private Connection connect()
	{
	Connection connection = null;
	try
	{
	Class.forName("com.mysql.jdbc.Driver");
	connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaremanagment", "root", "");
	}
	catch (Exception e)
	{e.printStackTrace();}
	return connection;
	}
	public String addSchedule(String hospitalCode, String doctorName, String roomNo, String timeFrom, String timeTo, String date)
	{
	String output = "";
	
	try
	{
	Connection connection = connect();
	if (connection == null)
	{return "Error while connecting to the database for inserting."; }
	// create a prepared statement
	String query = " insert into hospital(sheduleID,hospitalCode,doctorName,roomNo,timeFrom,timeTo,date)"
	+ " values (?, ?, ?, ?, ? ,? ,?)";
	PreparedStatement preparedStmt = connection.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, 0);
	preparedStmt.setString(2, hospitalCode);
	preparedStmt.setString(3, doctorName);
	preparedStmt.setString(4, roomNo);
	preparedStmt.setString(5, timeFrom);
	preparedStmt.setString(6, timeTo);
	preparedStmt.setString(7, date);
	

	// execute the statement
	preparedStmt.execute();
	connection.close();
	output = "Inserted doctor shedule successfully";
	}
	catch (Exception e)
	{
	output = "Error while inserting the doctor shedule.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	
	
	
	public String viewAllShedules()
	{
	String output = "";
	try
	{
	Connection connection = connect();
	if (connection == null)
	{return "Error while connecting to the database for reading."; }
	// Prepare the html table to be displayed
	output = "<table border=\"1\"><tr><th>Hospital code</th><th>Doctor Name</th><th>Room NO</th><th>Time From</th><th>Time To</th><th>DATE</th><th>Update</th><th>Remove</th></tr>";
	String query = "select * from hospital";
				Statement stmt = connection.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
	while (rs.next())
	{
	String hospitalID = Integer.toString(rs.getInt("sheduleID"));
	String hospitalCode = rs.getString("hospitalCode");
	String doctorName = rs.getString("doctorName");
	String roomNo = rs.getString("roomNo");
	String timeFrom = rs.getString("timeFrom");
	String timeTo = rs.getString("timeTo");
	String date = rs.getString("date");
	// Add into the html table
	output += "<tr><td>" + hospitalCode + "</td>";
	output += "<td>" + doctorName + "</td>";
	output += "<td>" + roomNo + "</td>";
	output += "<td>" + timeFrom + "</td>";
	output += "<td>" + timeTo + "</td>";
	output += "<td>" + date + "</td>";
	// buttons
	output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"items.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Cancel\"class=\"btn btn-danger\">"+ "<input name=\"hospitalID\" type=\"hidden\" value=\"" + hospitalID+ "\">" + "</form></td></tr>";
	}
	connection.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the items.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	
	public String updateDoctorShedule(String sheduleID, String hospitalCode, String doctorName, String roomNo, String timeFrom, String timeTo, String date)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for updating."; }
	// create a prepared statement
	String query = "UPDATE hospital SET hospitalCode=?,doctorName=?,roomNo=?,timeFrom=?,timeTo=?,date=? WHERE sheduleID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setString(1, hospitalCode);
	preparedStmt.setString(2, doctorName);
	preparedStmt.setString(3, roomNo);
	preparedStmt.setString(4, timeFrom);
	preparedStmt.setString(5, timeTo);
	preparedStmt.setString(6, date);
	preparedStmt.setInt(7, Integer.parseInt(sheduleID));
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Updated successfully";
	}
	catch (Exception e)
	{
	output = "Error while updating the item.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	public String deleteShedule(String sheduleID)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for deleting."; }
	// create a prepared statement
	String query = "delete from hospital where sheduleID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(sheduleID));
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Deleted successfully";
	}
	catch (Exception e)
	{
	output = "Error while deleting the item.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	
	public String addHospital(String hospitalCode, String hospitalName, String address, String phoneNo, String email)
	{
	String output = "";
	try
	{
	Connection connection = connect();
	if (connection == null)
	{return "Error while connecting to the database for inserting."; }
	// create a prepared statement
	String query = " insert into hospitalreg(hospitalID,hospitalCode,hospitalName,address,phoneNo,email)"
	+ " values (?, ?, ?, ?, ? ,? )";
	PreparedStatement preparedStmt = connection.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, 0);
	preparedStmt.setString(2, hospitalCode);
	preparedStmt.setString(3, hospitalName);
	preparedStmt.setString(4, address);
	preparedStmt.setString(5, phoneNo);
	preparedStmt.setString(6, email);
	
	

	// execute the statement
	preparedStmt.execute();
	connection.close();
	output = "Inserted hospital  successfully";
	}
	catch (Exception e)
	{
	output = "Error while inserting the hospital.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	
	
	public String viewAllhospitals()
	{
	String output = "";
	try
	{
	Connection connection = connect();
	if (connection == null)
	{return "Error while connecting to the database for reading."; }
	// Prepare the html table to be displayed
	output = "<table border=\"1\"><tr><th>Hospital code</th><th>Hospital Name</th><th>address</th><th>Phone NO</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
	String query = "select * from hospitalreg";
				Statement stmt = connection.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
	while (rs.next())
	{
	String hospitalID = Integer.toString(rs.getInt("hospitalID"));
	String hospitalCode = rs.getString("hospitalCode");
	String hospitalName = rs.getString("hospitalName");
	String address = rs.getString("address");
	String phoneNo = rs.getString("phoneNo");
	String email = rs.getString("email");
	
	// Add into the html table
	output += "<tr><td>" + hospitalCode + "</td>";
	output += "<td>" + hospitalName + "</td>";
	output += "<td>" + address + "</td>";
	output += "<td>" + phoneNo + "</td>";
	output += "<td>" + email + "</td>";
	
	// buttons
	output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"items.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Cancel\"class=\"btn btn-danger\">"+ "<input name=\"hospitalID\" type=\"hidden\" value=\"" + hospitalID+ "\">" + "</form></td></tr>";
	}
	connection.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the items.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	public String updateHospital(String hospitalID, String hospitalCode, String hospitalName, String address, String phoneNo, String email)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for updating."; }
	// create a prepared statement
	String query = "UPDATE hospitalreg SET hospitalCode=?,hospitalName=?,address=?,phoneNo=?,email=? WHERE hospitalID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setString(1, hospitalCode);
	preparedStmt.setString(2, hospitalName);
	preparedStmt.setString(3, address);
	preparedStmt.setString(4, phoneNo);
	preparedStmt.setString(5, email);
	preparedStmt.setInt(6, Integer.parseInt(hospitalID));
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Updated successfully";
	}
	catch (Exception e)
	{
	output = "Error while updating the hospital.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	public String deletehospital(String hospitalID)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for deleting."; }
	// create a prepared statement
	String query = "delete from hospitalreg where hospitalID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(hospitalID));
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Deleted successfully";
	}
	catch (Exception e)
	{
	output = "Error while deleting the item.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	 public static void requestAllDoctors() {
		 	String output = "";
		      Client client = ClientBuilder.newClient();
	        WebTarget target = client.target("http://localhost:8080/healthCareManagementSystemDoctor/DoctorService/Doctors");
	       output= target.request(MediaType.TEXT_HTML).get(String.class);
	      
	    }

}
