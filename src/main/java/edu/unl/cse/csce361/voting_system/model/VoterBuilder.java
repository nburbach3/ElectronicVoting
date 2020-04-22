package edu.unl.cse.csce361.voting_system.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class VoterBuilder {
    private String name;
    private boolean hasVoted;

    public VoterBuilder() {
        setVacuousDefaults();
    }

    /**
     * Retrieves species defaults from archetype file.
     * @param species   snake_case name of species whose archetype will be loaded
     */
    public VoterBuilder(String name) {
        this.name = name;
        hasVoted = false;
    }
    
    public VoterBuilder hasVoted() {
    	this.hasVoted = true;
    	return this;
    }

    private void setVacuousDefaults() {
        name = "Unnamed Voter";
        hasVoted = false;
    }

//    public Voter build() {
//        return new VoterEntity(name);
//    }
}
