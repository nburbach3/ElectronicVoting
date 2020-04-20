package edu.unl.cse.csce361.voting_system.controller;

import java.util.LinkedList;
import java.util.List;

import edu.unl.cse.csce361.voting_system.view.UserInterfaceManager;


public class ContinueAsCreatorCommand implements Command {
	
	public void execute() {
        List<Command> newMenu = new LinkedList<>();
        newMenu.add(new GoBackCommand());
        newMenu.addAll(MainMenus.creatorMenu);
        UserInterfaceManager.getUI().replaceCommands(newMenu);
    }
	
	
    @Override
    public String toString() {
        return "Continue as Election Creator";
    }

}
