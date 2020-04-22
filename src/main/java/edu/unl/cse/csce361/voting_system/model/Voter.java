package edu.unl.cse.csce361.voting_system.model;

//import edu.unl.cse.csce361.petting_zoo.model.Location;

public interface Voter {
    String name();

    boolean hasVoted();

    //void voted(ElectionEntity candidate);
}