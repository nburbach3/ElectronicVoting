package edu.unl.cse.csce361.voting_system.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VotingSystem {
	/*
	 * Methods (JDBC) to communicate with SQL database
	 */

	public static void addCandidate(Candidate candidate) throws SQLException {
		String values = String.format("VALUES(%s,%s,%s,%s,%i)", candidate.getFirstName(), candidate.getLastName(),
				candidate.getParty(), candidate.getPosition(), candidate.getVouteCount());
		String query = "INSERT INTO Candidates(firstName, lastName, party, position, voteCount) " + values;
		Connection connection = null;
		Statement statement = null;
		if (validateCandidate(candidate) == false) {
			try {
				connection = Database.getConnection();
				statement = connection.createStatement();
				statement.executeQuery(query);
			} finally {
				statement.close();
				connection.close();
			}
		}
	}

	public static boolean validateCandidate(Candidate candidate) throws SQLException {
		ArrayList<Candidate> candidates = getCandidates();
		if (candidates.contains(candidate)) {
			return false;
		} else {
			return true;
		}
	}

	public static Candidate getCandidate(String firstName, String lastName) throws SQLException {
		ArrayList<Candidate> candidates = getCandidates();

		for (Candidate candidate : candidates) {
			if (candidate.getFirstName().equals(firstName) && candidate.getLastName().equals(lastName)) {
				return candidate;
			}
		}
		return null;
	}

	public static ArrayList<Candidate> getCandidates() throws SQLException {
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
			while (rs.next()) {
				candidate = new Candidate(rs.getString("firstName"), rs.getString("lastName"), rs.getString("party"),
						rs.getString("position"), rs.getInt("voteCount"));
				candidates.add(candidate);

			}
		} finally {
			rs.close();
			statement.close();
			connection.close();
		}
		return candidates;
	}

	public static void removeCandidate(Candidate candidate) throws SQLException {
		String query = String.format("DELETE FROM Candidates WHERE firstName='%s' AND lastName='%s'",candidate.getFirstName(),candidate.getLastName());
		Connection connection = null;
		Statement statement = null;
		if (validateCandidate(candidate) == true) {
			try {
				connection = Database.getConnection();
				statement = connection.createStatement();
				statement.executeQuery(query);
			} finally {
				statement.close();
				connection.close();
			}
		}
	}

	public static String getVoterInfo(String firstName, String lastName) {
		// TODO: Query the database to get voter's first/last name, and whether or not
		// they have voted.
		// Return well formatted String with voter info
		// Return "Invalid Input, voter does not exist" if voter is not in database
		return "";
	}

	public static String getAllVoterInfo() {
		// TODO: Query Voter table to get all accessible voter info (name/wheter they
		// have voted)
		// Return well formatted String with all voter info
		// Return "No voter information available" if table is empty
		return "";
	}

	public static void addProposition(Proposition proposition) {
		// TODO: Query database to add proposition to proposition table
	}

	public static boolean validateProposition(Proposition proposition) {
		// TODO: Verify that proposition has not already been added to prop table
		// Returns true if proposition has not been added, false if it already has
		return true;
	}

	public static ArrayList<Proposition> getPropositions() {
		// TODO: Query database to return an ArrayList of all propositions
		ArrayList<Proposition> propositions = new ArrayList<Proposition>();
		return propositions;
	}

	public static void removeProposition(Proposition proposition) {
		// TODO: Query database to remove proposition from table
	}
}
