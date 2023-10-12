package com.prosantosgui.techunter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prosantosgui.techunter.pk.CandidaturaPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_candidaturas")
public class Candidatura implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CandidaturaPK id = new CandidaturaPK();

    private Instant data;

    public Candidatura(){
    }

    public Candidatura(Candidato candidato, Vaga vaga, Instant data) {
        super();
        this.id.setCandidato(candidato);
        this.id.setVaga(vaga);
        this.data = data;
    }

    @JsonIgnore
    public Candidato getCandidato(){ return id.getCandidato();}

    public void setCandidadto(Candidato candidato){ id.setCandidato(candidato);}

    public Vaga getVaga(){ return id.getVaga();}

    public void setVaga(Vaga vaga){ id.setVaga(vaga);}

    public Instant getData() { return data;}

    public void setData(Instant data) { this.data = data;}

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Candidatura that = (Candidatura) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
