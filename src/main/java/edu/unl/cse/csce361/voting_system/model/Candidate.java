package edu.unl.cse.csce361.voting_system.model;

public class Candidate {
	private String firstName;
	private String lastName;
	private String party;
	private String position;
	
	public Candidate(String firstName, String lastName, String party, String position) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.party = party;
		this.position = position;
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
	
	public String getParty() {
		return this.party;
	}
	
	public void setParty(String party) {
		this.party = party;
	}
	
	public String getPosition() {
		return this.position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
}
