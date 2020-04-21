package edu.unl.cse.csce361.voting_system.model;

public class VotingSystem {
	/*
	 * Methods (JDBC) to communicate with
	 * SQL database
	 */
	
	public static void addCandidate(String firstName, String lastName, String party) {
		//TODO: Add candidate to database using SQL query
	}
	
	public static boolean validateCandidate(String firstName, String lastName, String party) {
		//TODO: Verify that proposed candidate has not already been created by querying their name
		//Returns true if candidate has not been created and false if they already have
		return true;
	}
}
