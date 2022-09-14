package com.veer.voat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VoteDeatails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int voteId;
	private int userId;
	private int candidateid;
	public VoteDeatails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VoteDeatails(int voteId, int userId, int candidateid) {
		super();
		this.voteId = voteId;
		this.userId = userId;
		this.candidateid = candidateid;
	}
	public int getVoteId() {
		return voteId;
	}
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCandidateid() {
		return candidateid;
	}
	public void setCandidateid(int candidateid) {
		this.candidateid = candidateid;
	}
	@Override
	public String toString() {
		return "VoteDeatails [voteId=" + voteId + ", userId=" + userId + ", candidateid=" + candidateid + "]";
	}
	
	
	
	

}
