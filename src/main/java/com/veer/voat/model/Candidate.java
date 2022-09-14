package com.veer.voat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int candidateId;
	private String candidateName;
	private int voteCounts;
	
	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Candidate(int candidateId, String candidateName, int voteCounts) {
		super();
		this.candidateId = candidateId;
		this.candidateName = candidateName;
		this.voteCounts = voteCounts;
	}
	public int getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public int getVoteCounts() {
		return voteCounts;
	}
	public void setVoteCounts(int voteCounts) {
		this.voteCounts = voteCounts;
	}
	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", CandidateName=" + candidateName + ", voteCounts="
				+ voteCounts + "]";
	}
	
	
	

}
