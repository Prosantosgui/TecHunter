package com.prosantosgui.techunter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prosantosgui.techunter.pk.ApplicationPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

//Application 
@Entity
@Table(name = "tb_application")
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ApplicationPK id = new ApplicationPK();

    private Instant date;

    public Application(){
    }

    public Application(Candidate Candidate, Position position, Instant date) {
        super();
        this.id.setCandidate(Candidate);
        this.id.setPosition(position);
        this.date = date;
    }

    @JsonIgnore
    public Candidate getCandidate(){ return id.getCandidate();}

    public void setCandidate(Candidate Candidate){ id.setCandidate(Candidate);}

    public Position getPosition(){ return id.getPosition();}

    public void setPosition(Position position){ id.setPosition(position);}

    public Instant getDate() { return date;}

    public void setDate(Instant date) { this.date = date;}

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Application that = (Application) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
