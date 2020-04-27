package edu.unl.cse.csce361.voting_system.model;

public class Vote {
	private String firstName;
	private String lastName;
	private Candidate candidate;
	private Proposition proposition;
	private boolean propositionVote;
	
	public Vote(String firstName, String lastName, Candidate candidate, Proposition proposition, boolean propositionVote) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.candidate = candidate;
		this.proposition = proposition;
		this.propositionVote = propositionVote;
		
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Candidate getCandidate() {
		return this.candidate;
	}
	
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	public Proposition getProposition() {
		return this.proposition;
	}
	
	public void setProposition(Proposition proposition) {
		this.proposition = proposition;
	}
	
	public boolean getPropositionVote() {
		return this.propositionVote;
	}
	
	public void setPropositionVote(boolean propositionVote) {
		this.propositionVote = propositionVote;
	}
}

