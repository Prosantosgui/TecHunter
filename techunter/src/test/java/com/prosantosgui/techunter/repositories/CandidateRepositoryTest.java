package com.prosantosgui.techunter.repositories;

import com.prosantosgui.techunter.model.Candidate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;

import java.util.Optional;


@DataJpaTest
@DisplayName("Tests for CandidateRepository")
class CandidateRepositoryTest {

    @Autowired
    private CandidateRepository candidateRepository;
    private Candidate candidate0;

    @BeforeEach
    public void setup(){
        candidate0 = new Candidate("Vino23",
                "21451",
                "Vinicius",
                "2154156165",
                "Rio de Janeiro",
                true,
                "RJ");
    }

    @Test
    @DisplayName("Given Candidate When Save Then return saved Candidate")
    void givenCandidateWhenSaveThenReturnSavedCandidate() {
        Candidate candidate = candidateRepository.save(candidate0);

        Assertions.assertThat(candidate).isEqualTo(candidate0);
        Assertions.assertThat(candidate).isNotNull();
        Assertions.assertThat(candidate.getLogin()).isNotEmpty();

    }
    @Test
    @DisplayName("Given Candidate with not-null field value When Save Should throw DataIntegrityViolationException")
    void givenCandidateWithNotNullFieldValueWhenSaveShouldThrowDataIntegrityViolationException(){
        candidate0.setName(null);

        Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(() -> {
            candidateRepository.save(candidate0);
        }).withMessageContaining("not-null property references a null or transient value");

    }

    @Test
    @DisplayName("Given Candidate with Id field null When Save should throw JpaSystemException")
    void givenCandidateWithIdFieldNullWhenSaveShouldThrowJpaSystemException() {
        candidate0.setLogin(null);

        Assertions.assertThatExceptionOfType(JpaSystemException.class).isThrownBy(()->{
            candidateRepository.save(candidate0);
        }).withMessageContaining("ids for this class must be manually assigned before calling save()");

    }

    @Test
    @DisplayName("Given Candidate When update fields Successfully")
    void givenCandidateWhenUpdateFieldsSuccessfully() {
        Candidate candidateSaved = this.candidateRepository.save(candidate0);
        String newName = "Viniccius";
        String newLocation = "São Paulo";

        candidateSaved.setName(newName);
        candidateSaved.setLocation(newLocation);
        Candidate updatedCandidate = this.candidateRepository.save(candidateSaved);

        Assertions.assertThat(updatedCandidate).isNotNull();
        Assertions.assertThat(updatedCandidate.getName()).isNotNull();
        Assertions.assertThat(updatedCandidate.getLocation()).isNotNull();
        Assertions.assertThat(updatedCandidate.getName()).isEqualTo(newName);
        Assertions.assertThat(updatedCandidate.getLocation()).isEqualTo(newLocation);
    }

    @Test
    @DisplayName("Given Candidate When Delete successful")
    void givenCandidateWhenDeleteSuccessful() {
        Candidate candidateSaved = candidateRepository.save(candidate0);

        this.candidateRepository.delete(candidateSaved);
        Optional<Candidate> id = candidateRepository.findById(candidateSaved.getLogin());

        Assertions.assertThat(id).isEmpty();
    }

    @Test
    @DisplayName("Given Candidate When Delete not successful")
    void givenCandidateWhenDeleteNotSuccessful(){

        Candidate candidateSaved = candidateRepository.save(candidate0);

        this.candidateRepository.deleteById("Vino22");
        Optional<Candidate> id = candidateRepository.findById(candidateSaved.getLogin());

        Assertions.assertThat(id).isPresent();
    }

}