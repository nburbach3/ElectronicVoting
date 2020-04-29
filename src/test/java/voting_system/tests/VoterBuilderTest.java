package voting_system.tests;

import static org.junit.Assert.*;

import edu.unl.cse.csce361.voting_system.model.Candidate;
import edu.unl.cse.csce361.voting_system.model.Voter;
import edu.unl.cse.csce361.voting_system.model.VotingSystem;
import org.junit.Test;

import java.sql.SQLException;

public class VoterBuilderTest {

	private Voter voter = new Voter("Test", "Voter", 0);

	@Test
	public void testVoterCreation() throws SQLException {
		VotingSystem.addVoter(voter);
		Voter database = VotingSystem.getVoterInfoThirdParty("Test", "Voter");
		assertEquals(voter.getFirstName(), database.getFirstName());
		assertEquals(voter.getLastName(), database.getLastName());
		VotingSystem.removeVoter(voter);
	}

}
