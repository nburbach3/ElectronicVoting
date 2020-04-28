package edu.unl.cse.csce361.voting_system.model;

public class Proposition {
	private String proposition;
	private int voteCount;
	private int propositionId;
	
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
	
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	
	public int getPropositionId() {
		return this.propositionId;
	}
	
	public void setPropositionId(int propositionId) {
		this.propositionId = propositionId;
	}
}
