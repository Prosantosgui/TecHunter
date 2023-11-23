package com.prosantosgui.techunter.repositories;

import com.prosantosgui.techunter.model.Candidate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;


@DataJpaTest
@DisplayName("Tests for CandidateRepository")
class CandidateRepositoryTest {

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    @DisplayName("Persist Candidate in database when successful")
    void save_PersistCandidate_WhenSuccessful(){
        Candidate candidateToBeSaved = new Candidate("Vino23", "21451", "Vinicius", "2154156165", "Rio de Janeiro", true, "RJ");
        Candidate candidateSaved = this.candidateRepository.save(candidateToBeSaved);

        Assertions.assertThat(candidateSaved).isNotNull();
        Assertions.assertThat(candidateSaved.getLogin()).isNotNull();
        Assertions.assertThat(candidateToBeSaved.getLogin()).isEqualTo(candidateSaved.getLogin());

    }

    @Test()
    @DisplayName("Persist Candidate with null mandatory fields")
    void save_PersistCandidate_WhenNotSuccessful(){
        Candidate candidateToBeSaved = new Candidate(null, null, null, "2154156165", "Rio de Janeiro", true, "RJ");
        try {
           Candidate candidateSaved = this.candidateRepository.save(candidateToBeSaved);
           Assertions.assertThat(candidateSaved).isNull();
        }catch (Exception e){
            Assertions.assertThat(e.getMessage()).containsAnyOf("not-null property references a null or transient value", "ids for this class must be manually assigned before calling save()");
        }

    }


    @Test
    @DisplayName("Save updated Candidate in database when successful")
    void save_UpdateCandidate_WhenSuccessful(){
        Candidate candidateToBeSaved = new Candidate("Vino23", "21451", "Vinicius", "2154156165", "Rio de Janeiro", true, "RJ");
        Candidate candidateSaved = this.candidateRepository.save(candidateToBeSaved);

        candidateSaved.setName("Viniccius");

        Candidate updatedCandidate = this.candidateRepository.save(candidateSaved);

        Assertions.assertThat(updatedCandidate).isNotNull();
        Assertions.assertThat(updatedCandidate.getName()).isNotNull();
        Assertions.assertThat(updatedCandidate.getName()).isEqualTo(candidateSaved.getName());

    }

    @Test
    @DisplayName("Save updated Candidate in database when not successful")
    void save_UpdateCandidate_WhenNotSuccessful(){
        Candidate candidateToBeSaved = new Candidate("Vino23", "21451", "Vinicius", "2154156165", "Rio de Janeiro", true, "RJ");
        Candidate candidateSaved = this.candidateRepository.save(candidateToBeSaved);

        candidateSaved.setLogin(null);
        candidateSaved.setName(null);
        candidateSaved.setPassword(null);
        try {
            Candidate updatedCandidate = this.candidateRepository.save(candidateSaved);
        }catch (Exception e){
            Assertions.assertThat(e.getMessage()).isNotNull();
        }

    }

    @Test
    @DisplayName("Delete candidate in database when successful")
    void delete_RemoveCandidate_WhenSuccessful(){

        Candidate candidateToBeSaved = new Candidate("Vino23", "21451", "Vinicius", "2154156165", "Rio de Janeiro", true, "RJ");
        Candidate candidateSaved = this.candidateRepository.save(candidateToBeSaved);

        this.candidateRepository.delete(candidateSaved);

        Optional<Candidate> id = this.candidateRepository.findById(candidateSaved.getLogin());

        Assertions.assertThat(id).isEmpty();
    }

    @Test
    @DisplayName("Delete candidate in database when not successful")
    void delete_RemoveCandidate_WhenNotSuccessful(){

        Candidate candidateToBeSaved = new Candidate("Vino23", "21451", "Vinicius", "2154156165", "Rio de Janeiro", true, "RJ");
        Candidate candidateSaved = this.candidateRepository.save(candidateToBeSaved);

        this.candidateRepository.deleteById("Vino22");

        Optional<Candidate> id = this.candidateRepository.findById(candidateSaved.getLogin());

        Assertions.assertThat(id).isPresent();
    }
}