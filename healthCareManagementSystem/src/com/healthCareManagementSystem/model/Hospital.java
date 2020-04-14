package com.healthCareManagementSystem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
	String query = " insert into hospital(hospitalID,hospitalCode,doctorName,roomNo,timeFrom,timeTo,date)"
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
	String hospitalID = Integer.toString(rs.getInt("hospitalID"));
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

}
