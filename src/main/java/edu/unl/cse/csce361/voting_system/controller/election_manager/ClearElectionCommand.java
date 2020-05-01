package edu.unl.cse.csce361.voting_system.controller.election_manager;

import edu.unl.cse.csce361.voting_system.controller.Command;
import edu.unl.cse.csce361.voting_system.model.VotingSystem;
import edu.unl.cse.csce361.voting_system.view.UserInterfaceManager;

public class ClearElectionCommand implements Command {

    @Override
    public void execute() {
        VotingSystem.clearElection();
    }

    @Override
    public String toString() {
        return "Clear Election";
    }
}
