package edu.unl.cse.csce361.voting_system.controller;

import edu.unl.cse.csce361.voting_system.view.UserInterfaceManager;

public class ExitCommand implements Command {

    @Override
    public void execute() {
        UserInterfaceManager.getUI().endGame();
    }

    @Override
    public String toString() {
        return "Exit game";
    }
}
