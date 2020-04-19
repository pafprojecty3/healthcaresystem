package com.healthCareManagementSystemAdmin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Admin {
	private Connection connect()
	{
	Connection con = null;
	try
	{
	Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
	}
	catch (Exception e)
	{e.printStackTrace();}
	return con;
	}
	
	
	public String insertAdmin(String username, String password, String reports)
	{
	String output = "";
	try 
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for inserting."; }
	// create a prepared statement
	String query = " insert into admin(adminID,adminUsername,adminPassword,adminReports)"
	+ " values (?, ?, ?, ?)";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, 0);
	preparedStmt.setString(2, username);
	preparedStmt.setString(3, password);
	preparedStmt.setString(3, reports);


	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Inserted successfully";
	}
	catch (Exception e)
	{
	output = "Error while inserting the admin.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	
	public String readAdmin()
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for reading."; }
	// Prepare the html table to be displayed
	output = "<table border=\"1\"><tr><th>Admin Username</th><th>Admin Password</th><th>Admin Reports</th><th>Update</th><th>Remove</th></tr>";
	String query = "select * from Admin";
				Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
	while (rs.next())
	{
    String adminID = Integer.toString(rs.getInt("adminID"));	
	String adminUsername = rs.getString("adminUsername");
	String adminPassword = rs.getString("adminPassword");
	String adminReports = rs.getString("adminReports");
	// Add into the html table
	output += "<tr><td>" + adminUsername + "</td>";
	output += "<td>" + adminPassword + "</td>";
	output += "<td>" + adminReports + "</td>";
	// buttons
	output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"admin.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"+ "<input name=\"itemID\" type=\"hidden\" value=\"" + adminID+ "\">" + "</form></td></tr>";
	}
	con.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the admin.";
	System.err.println(e.getMessage());
	}
	return output;
	}

	
	public String updateAdmin(String ID, String username, String password, String reports)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for updating."; }
	// create a prepared statement
	String query = "UPDATE admin SET adminUsername=?,adminPassword=?WHERE adminID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setString(1, username);
	preparedStmt.setString(2, password);
	preparedStmt.setString(3, reports);
	preparedStmt.setInt(4, Integer.parseInt(ID));
	
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Updated successfully";
	}
	catch (Exception e)
	{
	output = "Error while updating the admin.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	public String deleteAdmin(String adminID)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for deleting."; }
	// create a prepared statement
	String query = "delete from admin where adminID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(adminID));
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Deleted successfully";
	}
	catch (Exception e)
	{
	output = "Error while deleting the admin.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
}

