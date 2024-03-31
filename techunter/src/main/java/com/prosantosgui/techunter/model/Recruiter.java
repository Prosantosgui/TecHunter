package com.prosantosgui.techunter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "recruiter_model")
@Entity
public class Recruiter {

	@Id
	@NotNull @NotBlank
	private String login;

	@Column(nullable = false, length = 35)
	@NotNull @NotBlank
	private String name;
	
	@Column(nullable = false, length = 20)
	@NotNull @NotBlank
	private String country;
	
	@Column(length = 20)
	@NotNull @NotBlank
	private String company;
	
	@OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private final List<Position> positions = new ArrayList<>();
	
	public Recruiter() {
	}

	public Recruiter(String login, String name, String country, String company) {
		this.login = login;
		this.name = name;
		this.country = country;
		this.company = company;
	}

	public String getLogin() {return login;}

	public String getName() {return name;}

	public String getCountry() {return country;}

	public String getCompany() {return company;}

	public List<Position> getPositions() {return positions;}

	public void setLogin(String login) {this.login = login;}

	public void setName(String name) {this.name = name;}

	public void setCountry(String country) {this.country = country;}

	public void setCompany(String company) {this.company = company;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Recruiter recruiter = (Recruiter) o;
		return Objects.equals(login, recruiter.login);
	}

	@Override
	public int hashCode() {
		return Objects.hash(login);
	}

}
