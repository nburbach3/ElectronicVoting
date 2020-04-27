package edu.unl.cse.csce361.voting_system.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.unl.cse.csce361.voting_system.controller.election_creator.*;
import edu.unl.cse.csce361.voting_system.controller.third_party.*;
import edu.unl.cse.csce361.voting_system.controller.voter.*;

public class MainMenus {
    // When we later migrate to Java 11, we'll want to use List.of()

    public static final List<Command> mainMenu = Collections.unmodifiableList(Arrays.asList(
    		new ContinueAsVoterCommand(),
    		new ContinueAsCreatorCommand(),
    		new ContinueAsThirdPartyCommand(),
    		new ExitCommand()
    ));
    
    public static final List<Command> voterMenu = Collections.unmodifiableList(Arrays.asList(
            new EnterVoterInformationCommand()
    ));
    
    public static final List<Command> creatorMenu = Collections.unmodifiableList(Arrays.asList(
    		new EnterCandidateInformationCommand(),
    		new DeleteCandidateCommand(),
    		new EnterPropositionInformationCommand(),
    		new DeletePropositionCommand()
    ));
    
    public static final List<Command> thirdPartyMenu = Collections.unmodifiableList(Arrays.asList(
    		new SearchVoterCommand(),
    		new DisplayVoterInformationCommand()
    ));
}
