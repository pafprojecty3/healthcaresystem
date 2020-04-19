package com.healthCareManagementSystemPayment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class docpayment {
	//A common method to connect to the DB
		private Connection connect()
		{
		Connection con = null;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		//Provide the correct details
		con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaremanagment", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
		}
		public String insertdocpayment(String PaymentID,String Paymentcode, String DocID, String DocName, String PaymentType, String Amount, String DateOfPayed)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for inserting."; }
		// create a prepared statement
		String query = " insert into docpayment(Paymentcode,DocID,DocName,PaymentType,Amount,DateOfPayed)"
		+ " values (?, ?, ?, ?, ? ,?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, Paymentcode);
		preparedStmt.setString(3, DocID);
		preparedStmt.setString(4, DocName);
		preparedStmt.setString(5, PaymentType);
		preparedStmt.setDouble(6, Float.parseFloat(Amount));
		preparedStmt.setString(7, DateOfPayed);

		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Inserted successfully";
		}
		catch (Exception e)
		{
		output = "Error while inserting the Docor Payment.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		public String readdocpayment()
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for reading."; }
		// Prepare the html table to be displayed
		output = "<table border=\"1\"><tr><th>Paymentcode</th><th>DocID</th><th>DocName</th><th>PaymentType</th><th>Amount</th><th>DateOfPayed</th>";
		String query = "select * from docpayment";
					java.sql.Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
		while (rs.next())
		{
		String PaymentID = Integer.toString(rs.getInt("PaymentID"));
		String Paymentcode = rs.getString("Paymentcode");
		String DocID = rs.getString("DocID");
		String DocName = rs.getString("DocName");
		String PaymentType = rs.getString("PaymentType");
		String Amount = Float.toString(rs.getFloat("Amount"));
		String DateOfPayed = rs.getString("DateOfPayed");
		// Add into the html table
		output += "<tr><td>" + Paymentcode + "</td>";
		output += "<td>" + DocID + "</td>";
		output += "<td>" + DocName + "</td>";
		output += "<td>" + PaymentType + "</td>";
		output += "<td>" + Amount + "</td>";
		output += "<td>" + DateOfPayed + "</td>";
		// buttons
		//output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"Docpayment.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"+ "<input name=\"PaymentID\" type=\"hidden\" value=\"" + PaymentID + "\">" + "</form></td></tr>";
		}
		con.close();
		// Complete the html table
		output += "</table>";
		}
		catch (Exception e)
		{
		output = "Error while reading the Docpayment.";
		System.err.println(e.getMessage());
		}
		return output;
		}

		public String updatedocpayment(String PaymentID,String Paymentcode, String DocID, String DocName, String PaymentType, String Amount, String DateOfPayed)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for updating."; }
		// create a prepared statement
		String query = "UPDATE docpayment SET Paymentcode=?,DocID=?,DocName=?,PaymentType=?,Amount=?,DateOfPayed=?,WHERE PaymentID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, Paymentcode);
		preparedStmt.setString(3, DocID);
		preparedStmt.setString(4, DocName);
		preparedStmt.setString(5, PaymentType);
		preparedStmt.setFloat(6, Float.parseFloat(Amount));
		preparedStmt.setString(7, DateOfPayed);
		
		preparedStmt.execute();
		con.close();
		output = "Updated successfully";
		}
		catch (Exception e)
		{
		output = "Error while updating the Docpayment.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		public String deletedocpayment(String PaymentID)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for deleting."; }
		// create a prepared statement
		String query = "delete from docpayment where PaymentID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(PaymentID));
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Deleted successfully";
		}
		catch (Exception e)
		{
		output = "Error while deleting the Docpayment.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		
		
		public String insertAppointmentPayment(String AppointentNo, String PatientNIC, String PatientName, String TellNo, String HospitalName, String DoctorName, String Date, String DoctorCharges, String HospitalCharges, String FullPayment )
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for inserting."; }
		// create a prepared statement
		String query = " insert into appointentpayment(AppointentNo,PatientNIC,PatientName,TellNo,HospitalName,DoctorName,Date,DoctorCharges,HospitalCharges,FullPayment)"
		+ " values (?, ?, ?, ?, ? ,? ,? ,? ,? ,?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		preparedStmt.setString(1, AppointentNo);
		preparedStmt.setString(2, PatientNIC);
		preparedStmt.setString(3, PatientName);
		preparedStmt.setString(4, TellNo);
		preparedStmt.setString(5, HospitalName);
		preparedStmt.setString(6, DoctorName);
		preparedStmt.setString(7, Date);
		preparedStmt.setFloat(8, Float.parseFloat(DoctorCharges));
		preparedStmt.setFloat(9, Float.parseFloat(HospitalCharges));
		preparedStmt.setFloat(10, Float.parseFloat(FullPayment));

		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Inserted successfully";
		}
		catch (Exception e)
		{
		output = "Error while inserting the Appointment Payment.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		
		public String readAppointmentPayment()
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for reading."; }
		// Prepare the html table to be displayed
		output = "<table border=\"1\"><tr><th>AppointentNo</th><th>PatientNIC</th><th>PatientName</th><th>TellNo</th><th>HospitalName</th><th>DoctorName</th><th>Date</th><th>DoctorCharges</th><th>HospitalCharges</th><th>FullPayment</th>";
		String query = "select * from appointentpayment";
					java.sql.Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
		while (rs.next())
		{
		//String PaymentID = Integer.toString(rs.getInt("PaymentID"));
		String AppointentNo = rs.getString("AppointentNo");
		String PatientNIC = rs.getString("PatientNIC");
		String PatientName = rs.getString("PatientName");
		String TellNo = rs.getString("TellNo");
		String HospitalName = rs.getString("HospitalName");
		String DoctorName = rs.getString("DoctorName");
		String Date = rs.getString("Date");
		String DoctorCharges = Float.toString(rs.getFloat("DoctorCharges"));
		String HospitalCharges = Float.toString(rs.getFloat("HospitalCharges"));
		String FullPayment = Float.toString(rs.getFloat("FullPayment"));
		
		// Add into the html table
		output += "<tr><td>" + AppointentNo + "</td>";
		output += "<td>" + PatientNIC + "</td>";
		output += "<td>" + PatientName + "</td>";
		output += "<td>" + TellNo + "</td>";
		output += "<td>" + HospitalName + "</td>";
		output += "<td>" + DoctorName + "</td>";
		output += "<td>" + Date + "</td>";
		output += "<td>" + DoctorCharges + "</td>";
		output += "<td>" + HospitalCharges + "</td>";
		output += "<td>" + FullPayment + "</td>";
		// buttons
		//output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"Docpayment.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"+ "<input name=\"AppointentNo\" type=\"hidden\" value=\"" + AppointentNo + "\">" + "</form></td></tr>";
		}
		con.close();
		// Complete the html table
		output += "</table>";
		}
		catch (Exception e)
		{
		output = "Error while reading the AppointmentPayment.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		
		
		
		public String updateAppointmentPayment(String AppointentNo, String PatientNIC, String PatientName, String TellNo, String HospitalName, String DoctorName, String Date, String DoctorCharges, String HospitalCharges, String FullPayment )
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for updating."; }
		// create a prepared statement
		String query = "UPDATE appointentpayment SET AppointentNo=?,PatientNIC=?,PatientName=?,TellNo=?,HospitalName=?,DoctorName=?,Date=?,DoctorCharges=?,HospitalCharges=?,FullPayment=?,WHERE AppointentNo=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		
		preparedStmt.setString(1, AppointentNo);
		preparedStmt.setString(2, PatientNIC);
		preparedStmt.setString(3, PatientName);
		preparedStmt.setString(4, TellNo);
		preparedStmt.setString(5, HospitalName);
		preparedStmt.setString(6, DoctorName);
		preparedStmt.setString(7, Date);
		preparedStmt.setFloat(8, Float.parseFloat(DoctorCharges));
		preparedStmt.setFloat(9, Float.parseFloat(HospitalCharges));
		preparedStmt.setFloat(10, Float.parseFloat(FullPayment));

		
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Updated successfully";
		}
		catch (Exception e)
		{
		output = "Error while updating the Appointentpayment.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		
		
		
		public String deleteAppointmentPayment(String AppointentNo)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for deleting."; }
		// create a prepared statement
		String query = "delete from appointentpayment where AppointentNo=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(AppointentNo));
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Deleted successfully";
		}
		catch (Exception e)
		{
		output = "Error while deleting the AppointmentPayment.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		

}
