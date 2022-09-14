package com.veer.voat.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veer.voat.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

	Candidate findByCandidateName(String name);
}
