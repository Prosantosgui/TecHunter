package com.prosantosgui.techunter.pk;

import com.prosantosgui.techunter.model.Candidato;
import com.prosantosgui.techunter.model.Vaga;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

public class CandidaturaPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

    public Vaga getVaga() {return vaga;}
    public Candidato getCandidato() {return candidato;}
    public void setVaga(Vaga vaga) {this.vaga = vaga;}
    public void setCandidato(Candidato candidato) {this.candidato = candidato;}

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CandidaturaPK that = (CandidaturaPK) o;
        return Objects.equals(vaga, that.vaga) && Objects.equals(candidato, that.candidato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vaga, candidato);
    }
}
