package edu.unl.cse.csce361.voting_system.model;

public class Proposition {
	private String proposition;
	private int voteCount;
	
	public Proposition(String proposition, int voteCount) {
		this.proposition = proposition;
	}
	
	public String getProposition() {
		return this.proposition;
	}
	
	public void setProposition(String proposition) {
		this.proposition = proposition;
	}
	
	public int getVoteCount() {
		return this.voteCount;
	}
}
