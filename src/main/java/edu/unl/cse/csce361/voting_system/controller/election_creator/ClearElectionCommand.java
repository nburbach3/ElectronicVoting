package edu.unl.cse.csce361.voting_system.controller.election_creator;

import edu.unl.cse.csce361.voting_system.controller.Command;
import edu.unl.cse.csce361.voting_system.model.VotingSystem;
import edu.unl.cse.csce361.voting_system.view.UserInterfaceManager;

import java.util.List;

public class ClearElectionCommand implements Command {
    private List<Command> lastMenu;

    public ClearElectionCommand() {
        lastMenu = UserInterfaceManager.getUI().getMenu();
    }

    @Override
    public void execute() {
    	VotingSystem.clearElection();
        UserInterfaceManager.getUI().showInformation("Election tables have been cleared.");
    }

    @Override
    public String toString() {
        return "Clear Election";
    }
}
