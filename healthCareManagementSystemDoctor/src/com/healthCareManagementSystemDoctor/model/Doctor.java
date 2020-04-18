package com.healthCareManagementSystemDoctor.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor {

		// A common method to connect to the DB
		private Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaremanagment", "root", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}

		public String insertDoctor(String code, String name, String charge, String desc) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = " insert into doctors(doctorID,doctorCode,doctorName,doctorCharge,doctorDesc)"
						+ " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, code);
				preparedStmt.setString(3, name);
				preparedStmt.setDouble(4, Double.parseDouble(charge));
				preparedStmt.setString(5, desc);

				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			} catch (Exception e) {
				output = "Error while inserting the item.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String readDoctors() {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>Doctor Code</th><th>Doctor Name</th><th>Doctor Charge</th><th>Doctor Description</th><th>Update</th><th>Remove</th></tr>";
				String query = "select * from doctors";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()) {
					String doctorID = Integer.toString(rs.getInt("doctorID"));
					String doctorCode = rs.getString("doctorCode");
					String doctorName = rs.getString("doctorName");
					String doctorCharge = Double.toString(rs.getDouble("doctorCharge"));
					String doctorDesc = rs.getString("doctorDesc");
					// Add into the html table
					output += "<tr><td>" + doctorCode + "</td>";
					output += "<td>" + doctorName + "</td>";
					output += "<td>" + doctorCharge + "</td>";
					output += "<td>" + doctorDesc + "</td>";
					// buttons
					output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
							+ "<td><form method=\"post\" action=\"doctors.jsp\">"
							+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
							+ "<input name=\"itemID\" type=\"hidden\" value=\"" + doctorID + "\">" + "</form></td></tr>";
				}
				con.close();
				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String updateDoctor(String ID, String code, String name, String charge, String desc) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE doctors SET doctorCode=?,doctorName=?,doctorCharge=?,doctorDesc=?WHERE doctorID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, code);
				preparedStmt.setString(2, name);
				preparedStmt.setDouble(3, Double.parseDouble(charge));
				preparedStmt.setString(4, desc);
				preparedStmt.setInt(5, Integer.parseInt(ID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			} catch (Exception e) {
				output = "Error while updating the item.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String deleteDoctor(String doctorID) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from doctors where doctorID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(doctorID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting the item.";
				System.err.println(e.getMessage());
			}
			return output;
		}

}
