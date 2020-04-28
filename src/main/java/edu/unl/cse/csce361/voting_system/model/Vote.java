package edu.unl.cse.csce361.voting_system.model;

public class Vote {
	private Voter voter;
	private Candidate candidate;
	private Proposition proposition;
	private int propositionVote;
	
	public Vote(Voter voter) {
		this.voter = voter;
	}
	
	public Voter getVoter() {
		return this.voter;
	}
	
	public void setVoter(Voter voter) {
		this.voter = voter;
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
	
	public int getPropositionVote() {
		return this.propositionVote;
	}
	
	public void setPropositionVote(int propositionVote) {
		this.propositionVote = propositionVote;
	}
}

