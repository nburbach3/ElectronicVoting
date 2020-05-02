package edu.unl.cse.csce361.voting_system.controller.election_manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import edu.unl.cse.csce361.voting_system.controller.Command;
import edu.unl.cse.csce361.voting_system.model.Candidate;
import edu.unl.cse.csce361.voting_system.model.Proposition;
import edu.unl.cse.csce361.voting_system.model.VotingSystem;
import edu.unl.cse.csce361.voting_system.view.UserInterfaceManager;

public class GetWinnerCommand implements Command {

	@Override
	public void execute() {
		Map<String, ArrayList<Candidate>> positions = null;
		ArrayList<Proposition> propositions = null;
		String output = "";

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

			candidates = entry.getValue();
			Candidate winner = candidates.get(0);

			for (Candidate person : candidates) {
				if (person.getVoteCount() > winner.getVoteCount()) {
					winner = person;
				}
			}
			output += position + ": " + winner.getFirstName() + " " + winner.getLastName() + " (" + winner.getParty() + ")\n";
		}

		output += "\n\n";

		for (Proposition current : propositions) {
			if (current.getVoteCount() > 0) {
				output += "Proposition '" + current.getProposition() + "' has passed!\n"; 
			} else {
				output += "Proposition '" + current.getProposition() + "' has failed.\n";
			}
		}

		UserInterfaceManager.getUI().showInformation(output);
	}

	@Override
	public String toString() {
		return "Get Election Winner";
	}
}
