package edu.unl.cse.csce361.voting_system.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VotingSystem {
	/*
	 * Methods (JDBC) to communicate with SQL database
	 */

	public static void addCandidate(Candidate candidate) throws SQLException {
		String values = String.format("VALUES('%s','%s','%s','%s',%d)", candidate.getFirstName(), candidate.getLastName(),
				candidate.getParty(), candidate.getPosition(), candidate.getVoteCount());
		String query = "INSERT INTO Candidates(firstName, lastName, party, position, voteCount) " + values;
		Connection connection = null;
		Statement statement = null;
		if (validateCandidate(candidate) == true) {
			try {
				connection = Database.getConnection();
				statement = connection.createStatement();
				statement.executeUpdate(query);
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
		Connection con = Database.getConnection();
		String query = "SELECT * FROM Candidates WHERE firstName = ? AND lastName = ?";
		Candidate candidate = null;

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String party = rs.getString("party");
				String position = rs.getString("position");
				int voteCount = rs.getInt("voteCount");
				candidate = new Candidate(firstName, lastName, party, position, voteCount);
			} else {
				System.out.println("Candidate does not exist.");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("SQL Exception: ");
			e.printStackTrace();
			throw new RuntimeException();
		}
		con.close();
		return candidate;
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
				candidate.setCandidateId(rs.getInt("idCandidates"));
				candidates.add(candidate);
			}
		} finally {
			rs.close();
			statement.close();
			connection.close();
		}
		return candidates;
	}

	public static Map<String, ArrayList<Candidate>> getPositions() throws SQLException {
		HashMap<String, ArrayList<Candidate>> positions = new HashMap<String, ArrayList<Candidate>>();
		ArrayList<Candidate> candidates = getCandidates();
		
		for(Candidate candidate : candidates) {
			if(positions.containsKey(candidate.getPosition())) {
				positions.get(candidate.getPosition()).add(candidate);
			} else {
				ArrayList<Candidate> filteredCandidate = new ArrayList<Candidate>();
				filteredCandidate.add(candidate);
				positions.put(candidate.getPosition(), filteredCandidate);
			}
		}
		return positions;
	}
	
	public static void removeCandidate(Candidate candidate) throws SQLException {
		String query = String.format("DELETE FROM Candidates WHERE firstName='%s' AND lastName='%s'",
				candidate.getFirstName(), candidate.getLastName());
		Connection connection = null;
		Statement statement = null;
		if (validateCandidate(candidate) == true) {
			try {
				connection = Database.getConnection();
				statement = connection.createStatement();
				statement.executeUpdate(query);
			} finally {
				statement.close();
				connection.close();
			}
		}
	}

	public static Voter getVoterInfoThirdParty(String firstName, String lastName) throws SQLException {
		ArrayList<Voter> voters = getVoters();

		for (Voter voter : voters) {
			if (voter.getFirstName().equals(firstName) && voter.getLastName().equals(lastName)) {
				return voter;
			}
		}
		return null;
	}

	public static ArrayList<Vote> getVoterVotes(String firstName, String lastName) throws SQLException {
		ArrayList<Vote> votes = getVotes();
		ArrayList<Vote> filteredVotes = new ArrayList<Vote>();
		for (Vote vote : votes) {
			if (vote.getVoter().getFirstName().equalsIgnoreCase(firstName)
					&& vote.getVoter().getLastName().equalsIgnoreCase(lastName)) {
				filteredVotes.add(vote);
			}
		}
		return filteredVotes;
	}

	public static void addVoter(Voter voter) throws SQLException {
		String values = String.format("VALUES ('%s','%s', 1);", voter.getFirstName(), voter.getLastName());
		String query = "INSERT INTO Voters (firstName, lastName, hasVoted) " + values;
		Connection connection = null;
		Statement statement = null;
		if (validateVoter(voter) == true) {
			try {
				connection = Database.getConnection();
				statement = connection.createStatement();
				statement.executeUpdate(query);
			} finally {
				statement.close();
				connection.close();
			}
		}
	}

	public static void removeVoter(Voter voter) throws SQLException {
		String query = String.format("DELETE FROM Voters WHERE firstName='%s' AND lastName='%s'", voter.getFirstName(),
				voter.getLastName());
		Connection connection = null;
		Statement statement = null;
		if (validateVoter(voter) == true) {
			try {
				connection = Database.getConnection();
				statement = connection.createStatement();
				statement.executeUpdate(query);
			} finally {
				statement.close();
				connection.close();
			}
		}
	}

	public static boolean validateVoter(Voter voter) throws SQLException {
		ArrayList<Voter> voters = getVoters();
		for (Voter voterOption : voters) {
			if (voter.getFirstName().equals(voterOption.getFirstName()) && voter.getLastName().equals(voterOption.getLastName())) {
				return false;
			}
		}
		return true;
	}

	public static ArrayList<Voter> getVoters() throws SQLException {
		String query = "SELECT * FROM Voters";
		ArrayList<Voter> voters = new ArrayList<Voter>();
		Voter voter = null;
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				voter = new Voter(rs.getString("firstName"), rs.getString("lastName"), rs.getInt("hasVoted"));
				voter.setVoterId(rs.getInt("idVoters"));
				voters.add(voter);
			}
		} finally {
			rs.close();
			statement.close();
			connection.close();
		}
		return voters;
	}

	public static void addVote(Voter voter, Candidate candidate, Proposition proposition, Integer propositionSelection)
			throws SQLException {
		Integer voterId = null;
		Integer candidateId = null;
		Integer propositionId = null;
		String updateQuery = "";
		if (validateVoter(voter)) {
			addVoter(voter);
		}
		ArrayList<Voter> voters = getVoters();

		for (Voter voterOption : voters) {
			if (voter.getFirstName().equals(voterOption.getFirstName()) && voter.getLastName().equals(voterOption.getLastName())) {
				voterId = Integer.valueOf(voterOption.getVoterId());
			}
		}
		if (candidate != null) {
			ArrayList<Candidate> candidates = getCandidates();
			for (Candidate candidateOption : candidates) {
				if (candidateOption.getFirstName().equals(candidate.getFirstName()) && candidateOption.getLastName().equals(candidate.getLastName())) {
					candidateId = Integer.valueOf(candidateOption.getCandidateId());
					updateQuery = String.format("UPDATE Candidates SET voteCount = %d WHERE firstName = '%s' AND lastName = '%s'", candidateOption.getVoteCount()+1, candidate.getFirstName(), candidate.getLastName());
				}
			}
		} else if (proposition != null) {
			ArrayList<Proposition> propositions = getPropositions();
			for (Proposition propositionOption : propositions) {
				if (propositionOption.getProposition().equals(proposition.getProposition())) {
					propositionId = Integer.valueOf(propositionOption.getPropositionId());
					int newVoteCount = propositionOption.getVoteCount() + propositionSelection.intValue();
					updateQuery = String.format("UPDATE Propositions SET voteCount = %d WHERE proposition = '%s'", newVoteCount, proposition.getProposition());
				}
			}
		}

		String values = String.format("VALUES(%d,%d,%d,%d)", voterId, candidateId, propositionId, propositionSelection);
		String query = "INSERT INTO Votes(idVoter, idCandidate, idProposition, propositionSelection) " + values;
		Connection connection = null;
		Statement statement = null;
		//if (validateCandidate(candidate) == false) {
			try {
				connection = Database.getConnection();
				statement = connection.createStatement();
				statement.executeUpdate(query);
				statement.executeUpdate(updateQuery);
			} finally {
				statement.close();
				connection.close();
			}
		//}

	}

	public static ArrayList<Vote> getVotes() throws SQLException {
		String query = "SELECT * FROM Votes";
		ArrayList<Vote> votes = new ArrayList<Vote>();
		ArrayList<Candidate> candidates = getCandidates();
		ArrayList<Voter> voters = getVoters();
		ArrayList<Proposition> propositions = getPropositions();
		Vote vote = null;
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				for (Voter voter : voters) {
					if (rs.getInt("idVoter") == voter.getVoterId()) {
						vote = new Vote(voter);
					}
				}
				if (rs.getInt("IdCandidate") == 0) {
					for (Proposition proposition : propositions) {
						if (rs.getInt("idProposition") == proposition.getPropositionId()) {
							vote.setProposition(proposition);
						}
					}
					vote.setPropositionVote(rs.getInt("propositionSelection"));
				} else {
					for (Candidate candidate : candidates) {
						if (rs.getInt("idCandidate") == candidate.getCandidateId()) {
							vote.setCandidate(candidate);
						}
					}
				}
				votes.add(vote);
			}
		} finally {
			rs.close();
			statement.close();
			connection.close();
		}
		return votes;
	}

	public static void addProposition(Proposition proposition) throws SQLException {
		Connection con = Database.getConnection();
		String query = "INSERT INTO Propositions(proposition, voteCount) values (?,?)";

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, proposition.getProposition());
			stmt.setInt(2, proposition.getVoteCount());
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			System.out.println("SQL Exception: ");
			e.printStackTrace();
			throw new RuntimeException();
		}
		con.close();
	}

	public static boolean validateProposition(Proposition proposition) throws SQLException {
		ArrayList<Proposition> prop = getPropositions();
		if (prop.contains(proposition)) {
			return false;
		} else {
			return true;
		}
	}

	public static Proposition getProposition(String prop) throws SQLException {
		Connection con = Database.getConnection();
		String query = "SELECT * FROM Propositions WHERE proposition = ?";
		Proposition proposition = null;

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, prop);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String temp = rs.getString("proposition");
				int voteCount = rs.getInt("voteCount");
				proposition = new Proposition(temp, voteCount);
			} else {
				System.out.println("Proposition does not exist.");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("SQL Exception: ");
			e.printStackTrace();
			throw new RuntimeException();
		}
		con.close();
		return proposition;
	}

	public static ArrayList<Proposition> getPropositions() throws SQLException {
		String query = "SELECT * FROM Propositions";
		ArrayList<Proposition> propositions = new ArrayList<Proposition>();
		Proposition proposition = null;
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				proposition = new Proposition(rs.getString("proposition"), rs.getInt("voteCount"));
				proposition.setPropositionId(rs.getInt("idPropositions"));
				propositions.add(proposition);
			}
		} finally {
			rs.close();
			statement.close();
			connection.close();
		}
		return propositions;
	}

	public static void removeProposition(Proposition proposition) throws SQLException {
		Connection con = Database.getConnection();
		String query = "DELETE FROM Propositions WHERE proposition = ?";
		
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, proposition.getProposition());
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			System.out.println("SQL Exception: ");
			e.printStackTrace();
			throw new RuntimeException();
		}
		con.close();
	}
	
	public static void clearElection() {
		String query1 = "DELETE FROM Votes";
		String query2 = "DELETE FROM Voters";
		String query3 = "DELETE FROM Candidates";
		String query4 = "DELETE FROM Propositions";
		Connection connection = null;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query1);
			statement.executeUpdate(query2);
			statement.executeUpdate(query3);
			statement.executeUpdate(query4);
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("SQL Exception: ");
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
}
