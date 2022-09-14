package com.veer.voat.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.veer.voat.model.VoteDeatails;

public interface VoteDetailsRepository extends JpaRepository<VoteDeatails, Integer> {

	public VoteDeatails findByUserId(int id);
}
