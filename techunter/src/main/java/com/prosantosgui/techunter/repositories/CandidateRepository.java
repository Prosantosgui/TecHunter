package com.prosantosgui.techunter.repositories;

import com.prosantosgui.techunter.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, String>{

}
