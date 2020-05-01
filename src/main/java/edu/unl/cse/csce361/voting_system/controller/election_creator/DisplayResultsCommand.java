package edu.unl.cse.csce361.voting_system.controller.election_creator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import edu.unl.cse.csce361.voting_system.controller.Command;
import edu.unl.cse.csce361.voting_system.model.Candidate;
import edu.unl.cse.csce361.voting_system.model.Proposition;
import edu.unl.cse.csce361.voting_system.model.VotingSystem;
import edu.unl.cse.csce361.voting_system.view.UserInterfaceManager;

public class DisplayResultsCommand implements Command {

	public void execute() {
		String output = "";
		Map<String, ArrayList<Candidate>> positions = null;
		ArrayList<Proposition> propositions = null;

		try {
			positions = VotingSystem.getPositions();
			propositions = VotingSystem.getPropositions();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String position = "";
		ArrayList<Candidate> candidates;

		for (Map.Entry<String, ArrayList<Candidate>> entry : positions.entrySet()) {
			position = entry.getKey();
			output += "\n" + position + "\n";

			candidates = entry.getValue();

			for (Candidate person : candidates) {
				output += "(" + person.getParty() + ") " + person.getFirstName() + " " + person.getLastName() + ": "
						+ person.getVoteCount() + "\n";
			}
		}
		
		output += "\n\n";
		
		for (Proposition current : propositions) {
			output += current.getProposition() + ": " + current.getVoteCount() + "\n";
		}
		
		UserInterfaceManager.getUI().showInformation(output);
	}

	@Override
	public String toString() {
		return "Display Election Results";
	}
}
