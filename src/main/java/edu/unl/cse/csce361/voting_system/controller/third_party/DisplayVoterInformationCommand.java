package edu.unl.cse.csce361.voting_system.controller.third_party;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.unl.cse.csce361.voting_system.controller.Command;
import edu.unl.cse.csce361.voting_system.model.Voter;
import edu.unl.cse.csce361.voting_system.model.VotingSystem;
import edu.unl.cse.csce361.voting_system.view.UserInterfaceManager;


public class DisplayVoterInformationCommand implements Command {
	
	public void execute() {
		ArrayList<Voter> voters = null;
		try {
			voters = VotingSystem.getVoters();
		} catch (SQLException e) {
			System.out.println("SQL Exception: ");
			e.printStackTrace();
		}
		String output = "(Last, First, Has/Hasn't Voted)\n";
		
		for (Voter voter : voters) {
			if (voter.getHasVoted() == 0) {
				output += voter.getLastName() + ", " + voter.getFirstName() + ": Not Voted\n";
			} else {
				output += voter.getLastName() + ", " + voter.getFirstName() + ": Voted\n";
			}
		}
		UserInterfaceManager.getUI().showInformation(output);
    }
	
    @Override
    public String toString() {
        return "Get All Voter Information";
    }

}
