package edu.unl.cse.csce361.voting_system.model;

public class Voter {
    private String firstName;
    private String lastName;
    private int hasVoted;

    public Voter(String firstName, String lastName, int hasVoted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hasVoted = hasVoted;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getHasVoted() { return hasVoted; }

    public void setHasVoted(int hasVoted) { this.hasVoted = hasVoted; }

}
