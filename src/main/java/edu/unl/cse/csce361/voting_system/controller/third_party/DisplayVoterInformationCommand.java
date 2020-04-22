package edu.unl.cse.csce361.voting_system.controller.third_party;

import edu.unl.cse.csce361.voting_system.controller.Command;
import edu.unl.cse.csce361.voting_system.model.VotingSystem;
import edu.unl.cse.csce361.voting_system.view.UserInterfaceManager;


public class DisplayVoterInformationCommand implements Command {
	
	public void execute() {
		UserInterfaceManager.getUI().showInformation(VotingSystem.getAllVoterInfo());
    }
	
    @Override
    public String toString() {
        return "Get All Voter Information";
    }

}
