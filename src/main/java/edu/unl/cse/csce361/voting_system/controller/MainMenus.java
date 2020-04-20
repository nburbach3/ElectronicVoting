package edu.unl.cse.csce361.voting_system.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainMenus {
    // When we later migrate to Java 11, we'll want to use List.of()

    public static final List<Command> mainMenu = Collections.unmodifiableList(Arrays.asList(
            //TODO: VoterCommand, ThirdPartyCommand, ElectionCreatorCommand
    ));
}
