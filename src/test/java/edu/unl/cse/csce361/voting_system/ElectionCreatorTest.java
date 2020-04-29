package edu.unl.cse.csce361.voting_system;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import edu.unl.cse.csce361.voting_system.model.Candidate;
import edu.unl.cse.csce361.voting_system.model.Proposition;
import edu.unl.cse.csce361.voting_system.model.VotingSystem;

public class ElectionCreatorTest {
	
	private Candidate candidate = new Candidate("John", "Doe", "Republican", "Mayor", 0);
	private Proposition proposition = new Proposition("Should we ban milk?", 0);
	
	@Test
	public void testCandidateCreation() throws SQLException {
		VotingSystem.addCandidate(candidate);
		Candidate database = VotingSystem.getCandidate("John", "Doe");
		assertEquals(candidate.getFirstName(), database.getFirstName());
		assertEquals(candidate.getLastName(), database.getLastName());
		assertEquals(candidate.getParty(), database.getParty());
		assertEquals(candidate.getPosition(), database.getPosition());
		VotingSystem.removeCandidate(candidate);
	}
	
	@Test
	public void testCandidateValidation() throws SQLException {
		VotingSystem.addCandidate(candidate);
		boolean output = VotingSystem.validateCandidate(candidate);
		assertEquals(output, true);
		VotingSystem.removeCandidate(candidate);
	}
	
	@Test
	public void testCandidateRemoval() throws SQLException {
		VotingSystem.addCandidate(candidate);
		ArrayList<Candidate> candidates = VotingSystem.getCandidates();
		VotingSystem.removeCandidate(candidate);
		ArrayList<Candidate> database = VotingSystem.getCandidates();
		boolean output = false;
		if (candidates.size() == (database.size() + 1)) {
			output = true;
		}
		assertEquals(output, true);
	}
	
	@Test
	public void testPropositionCreation() throws SQLException {
		VotingSystem.addProposition(proposition);
		ArrayList<Proposition> database = VotingSystem.getPropositions();
		String prop = "";
		for (Proposition temp : database) {
			if (temp.getProposition().equals(proposition.getProposition())) {
				prop = temp.getProposition();
			}
		}
		assertEquals(proposition.getProposition(), prop);
		VotingSystem.removeProposition(proposition);
	}
	
	@Test
	public void testPropositionValidation() throws SQLException {
		VotingSystem.addProposition(proposition);
		boolean output = VotingSystem.validateProposition(proposition);
		assertEquals(output, true);
		VotingSystem.removeProposition(proposition);
	}
	
	@Test
	public void testPropositionRemoval() throws SQLException {
		VotingSystem.addProposition(proposition);
		ArrayList<Proposition> propositions = VotingSystem.getPropositions();
		VotingSystem.removeProposition(proposition);
		ArrayList<Proposition> database = VotingSystem.getPropositions();
		boolean output = false;
		if (propositions.size() == database.size() + 1) {
			output = true;
		}
		assertEquals(output, true);
	}
}
