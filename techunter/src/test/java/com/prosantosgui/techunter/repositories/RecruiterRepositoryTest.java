package com.prosantosgui.techunter.repositories;

import com.prosantosgui.techunter.model.Recruiter;
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
@DisplayName("Tests for RecruiterRepository")
class RecruiterRepositoryTest {

    @Autowired
    private RecruiterRepository recruiterRepository;

    private Recruiter recruiter0;
    @BeforeEach
    public void setup(){
        recruiter0 = new Recruiter("DeskTec",
                "Felipe Alves",
                "Brasil",
                "Desk Tecnologias");
    }

    @Test
    @DisplayName("Given Recruiter When Save Then return saved Recruiter")
    void givenRecruiterWhenSaveThenReturnSavedRecruiter() {
        Recruiter recruiter = recruiterRepository.save(recruiter0);

        Assertions.assertThat(recruiter).isEqualTo(recruiter0);
        Assertions.assertThat(recruiter).isNotNull();
        Assertions.assertThat(recruiter.getLogin()).isNotEmpty();

    }

    @Test
    @DisplayName("Given Recruiter with not-null field value When Save Should throw DataIntegrityViolationException")
    void givenRecruiterWithNotNullFieldValueWhenSaveShouldThrowDataIntegrityViolationException(){
        recruiter0.setName(null);

        Assertions.assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(() -> {
            recruiterRepository.save(recruiter0);
        }).withMessageContaining("not-null property references a null or transient value");

    }

    @Test
    @DisplayName("Given Recruiter with Id field null When Save should throw JpaSystemException")
    void givenRecruiterWithIdFieldNullWhenSaveShouldThrowJpaSystemException() {
        recruiter0.setLogin(null);

        Assertions.assertThatExceptionOfType(JpaSystemException.class).isThrownBy(()->{
            recruiterRepository.save(recruiter0);
        }).withMessageContaining("ids for this class must be manually assigned before calling save()");

    }
    @Test
    @DisplayName("Given Recruiter When update fields Successfully")
    void givenRecruiterWhenUpdateFieldsSuccessfully() {
        Recruiter recruiterSaved = recruiterRepository.save(recruiter0);
        String newName = "Charlie";
        String newCountry = "United States";

        recruiterSaved.setName(newName);
        recruiterSaved.setCountry(newCountry);
        Recruiter updatedRecruiter = recruiterRepository.save(recruiterSaved);

        Assertions.assertThat(updatedRecruiter).isNotNull();
        Assertions.assertThat(updatedRecruiter.getName()).isNotNull();
        Assertions.assertThat(updatedRecruiter.getCountry()).isNotNull();
        Assertions.assertThat(updatedRecruiter.getName()).isEqualTo(newName);
        Assertions.assertThat(updatedRecruiter.getCountry()).isEqualTo(newCountry);
    }

    @Test
    @DisplayName("Given Recruiter When Delete successful")
    void givenRecruiterWhenDeleteSuccessful() {
        Recruiter recruiterSaved = recruiterRepository.save(recruiter0);

        recruiterRepository.delete(recruiterSaved);
        Optional<Recruiter> id = recruiterRepository.findById(recruiterSaved.getLogin());

        Assertions.assertThat(id).isEmpty();
    }

    @Test
    @DisplayName("Given Recruiter When Delete not successful")
    void givenRecruiterWhenDeleteNotSuccessful(){

        Recruiter recruiterSaved = recruiterRepository.save(recruiter0);

        recruiterRepository.deleteById("Vino22");
        Optional<Recruiter> id = recruiterRepository.findById(recruiterSaved.getLogin());

        Assertions.assertThat(id).isPresent();
    }

}