package com.prosantosgui.techunter.repositories;

import com.prosantosgui.techunter.model.Candidato;
import com.prosantosgui.techunter.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatoRepository extends JpaRepository<Candidato, String>{

}
