package com.prosantosgui.techunter.pk;

import com.prosantosgui.techunter.model.Candidate;
import com.prosantosgui.techunter.model.Position;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

public class ApplicationPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    public Position getPosition() {return position;}
    public Candidate getCandidate() {return candidate;}
    public void setPosition(Position position) {this.position = position;}
    public void setCandidate(Candidate candidate) {this.candidate = candidate;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationPK that = (ApplicationPK) o;
        return Objects.equals(position, that.position) && Objects.equals(candidate, that.candidate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, candidate);
    }
}
