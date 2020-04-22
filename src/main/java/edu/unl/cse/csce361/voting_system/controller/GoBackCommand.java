package edu.unl.cse.csce361.voting_system.controller;

import edu.unl.cse.csce361.voting_system.view.UserInterfaceManager;

import java.util.List;

public class GoBackCommand implements Command {
    private List<Command> lastMenu;

    public GoBackCommand() {
        lastMenu = UserInterfaceManager.getUI().getMenu();
    }

    @Override
    public void execute() {
        UserInterfaceManager.getUI().replaceCommands(lastMenu);
    }

    @Override
    public String toString() {
        return "Go back to previous menu";
    }
}
