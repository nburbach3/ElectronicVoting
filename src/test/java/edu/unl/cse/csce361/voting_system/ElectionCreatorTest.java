package edu.unl.cse.csce361.voting_system;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.unl.cse.csce361.voting_system.model.Candidate;
import edu.unl.cse.csce361.voting_system.model.VotingSystem;

public class ElectionCreatorTest {

	@Test
	public void testCandidateCreation() {
		VotingSystem.addCandidate("John", "Doe", "Republican", "Mayor");
		Candidate candidate = new Candidate("John", "Doe", "Republican", "Mayor");
		Candidate database = VotingSystem.getCandidate("John", "Doe");
		assertEquals(candidate.getFirstName(), database.getFirstName());
		assertEquals(candidate.getLastName(), database.getLastName());
		assertEquals(candidate.getParty(), database.getParty());
		assertEquals(candidate.getPosition(), database.getPosition());
		VotingSystem.removeCandidate(candidate);
	}
	
	@Test
	public void testCandidateValidation() {
		
	}
	
	@Test
	public void testPropositionCreation() {
		
	}
}
