package edu.unl.cse.csce361.voting_system.model;

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
}
