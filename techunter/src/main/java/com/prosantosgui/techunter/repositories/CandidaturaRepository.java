package com.prosantosgui.techunter.repositories;

import com.prosantosgui.techunter.model.Candidato;
import com.prosantosgui.techunter.model.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {

}
