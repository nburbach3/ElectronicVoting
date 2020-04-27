package edu.unl.cse.csce361.voting_system.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class VotingSystem {
	/*
	 * Methods (JDBC) to communicate with
	 * SQL database
	 */
	
	public static void addCandidate(String firstName, String lastName, String party, String position) {
		//TODO: Add candidate to database using SQL query
	}
	
	public static boolean validateCandidate(String firstName, String lastName, String party, String position) {
		//TODO: Verify that proposed candidate has not already been created by querying their name
		//Returns true if candidate has not been created and false if they already have
		return true;
	}
	
	public static Candidate getCandidate(String firstName, String lastName) {
		//TODO: Query the database for candidate, create object, and return it
		Candidate candidate = null;
		return candidate;
	}
	
	public static ArrayList<Candidate> getCandidates() throws SQLException{
		String query = "SELECT * FROM Candidates";
		ArrayList<Candidate> candidates = new ArrayList<Candidate>();
		Candidate candidate = null;
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while(rs.next()) {
				candidate = new Candidate(rs.getString("firstName"), rs.getString("lastName"), rs.getString("party"), rs.getString("position"), rs.getInt("voteCount"));
				candidates.add(candidate);
				
			}
		} finally {
			rs.close();
			statement.close();
			connection.close();
		}
		return candidates;
		
	}
	
	public static void removeCandidate(Candidate candidate) {
		//TODO: Query database to remove candidate
	}
	
	public static String getVoterInfo(String firstName, String lastName) {
		//TODO: Query the database to get voter's first/last name, and whether or not they have voted.
		//Return well formatted String with voter info
		//Return "Invalid Input, voter does not exist" if voter is not in database
		return "";
	}
	
	public static String getAllVoterInfo() {
		//TODO: Query Voter table to get all accessible voter info (name/wheter they have voted)
		//Return well formatted String with all voter info
		//Return "No voter information available" if table is empty
		return "";
	}
	
	public static void addProposition(Proposition proposition) {
		//TODO: Query database to add proposition to proposition table
	}
	
	public static boolean validateProposition(Proposition proposition) {
		//TODO: Verify that proposition has not already been added to prop table
		//Returns true if proposition has not been added, false if it already has
		return true;
	}
	
	public static ArrayList<Proposition> getPropositions() {
		//TODO: Query database to return an ArrayList of all propositions
		ArrayList<Proposition> propositions = new ArrayList<Proposition>();
		return propositions;
	}
	
	public static void removeProposition(Proposition proposition) {
		//TODO: Query database to remove proposition from table
	}
}
