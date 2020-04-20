package edu.unl.cse.csce361.voting_system.view;

import edu.unl.cse.csce361.voting_system.view.javafxview.GraphicalUserInterface;

public class UserInterfaceManager {
    private static UserInterface ui = null;

    static void setUI(UserInterface userInterface) {
        ui = userInterface;
    }

    public static UserInterface getUI() {
        if (ui == null) {
            ui = new GraphicalUserInterface();  // TODO: replace with dynamically-chosen UI
        }
        return ui;
    }
}
