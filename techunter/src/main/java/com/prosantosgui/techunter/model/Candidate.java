package com.prosantosgui.techunter.model;

import jakarta.persistence.*;

import java.util.*;

@Table(name = "candidate_model")
@Entity
public class Candidate {
	
	@Id
	@Column(name = "login_cand", nullable = false, length = 25)
	private String login;

	@Column(nullable = false, length = 30)
	private String name;
	
	@Column(length = 20)
	private String phoneNumber;
	
	@Column(length = 20)
	private String country;
	
	@Column(name = "immediate_availability")
	private boolean immediateAvailability;

	@Column(length = 20)
	private String state;

	private String email;

	@OneToMany(mappedBy = "id.candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private final Set<Application> applications = new HashSet<>();

	public Candidate(){
	}

	public Candidate(String login, String name, String phoneNumber, String country, boolean immediateAvailability, String state) {
		super();
		this.login = login;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.country = country;
		this.immediateAvailability = immediateAvailability;
		this.state = state;
	}

	public String getLogin() {return login;}

	public String getName() {return name;}

	public String getPhoneNumber() {return phoneNumber;}

	public String getCountry() {return country;}

	public boolean ImmediateAvailability() {return immediateAvailability;}

	public String getState() {return state;}

	public String getEmail() {return email;}

	public Set<Application> getApplications() {return applications;}

	public void setLogin(String login) {this.login = login;}

	public void setName(String name) {this.name = name;}

	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

	public void setCountry(String country) {this.country = country;}

	public void setImmediateAvailability(boolean immediateAvailability) {this.immediateAvailability = immediateAvailability;}

	public void setState(String state) {this.state = state;}

	public void setEmail(String email) {this.email = email;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Candidate candidate = (Candidate) o;
		return Objects.equals(login, candidate.login) && Objects.equals(email, candidate.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, email);
	}
}
