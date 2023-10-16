package com.prosantosgui.techunter.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Table(name = "position_model")
@Entity
public class Position implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_position", nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_recruiter")
	@JsonIgnore
	private Recruiter recruiter;
	
	@Column(nullable = false)
	private String type;
	
	@Column(name = "work_duration", nullable = false)
	private String workDuration;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private List<String> stacks;
	
	@Column(name = "employment_type" ,nullable = false)
	private String employmentType;
	private List<String> benefits;
	
	@Column(nullable = false)
	private List<String> salaryRanges;

	@Column(name = "required_education")
	private String requiredEducation;
	
	@Column(nullable = false)
	private String country;
	
	private String state;

	private Instant date;

	@OneToMany(mappedBy = "id.position")
	private Set<Application> Candidates = new HashSet<>();

	public Position(){

	}

	public Position(Long id, Recruiter recruiter, String type, String workDuration, String description,
					List<String> stacks, String employmentType, List<String> benefits, List<String> salaryRanges,
					String requiredEducation, String country, String state, Instant date) {
		super();
		this.id = id;
		this.recruiter = recruiter;
		this.type = type;
		this.workDuration = workDuration;
		this.description = description;
		this.stacks = stacks;
		this.employmentType = employmentType;
		this.benefits = benefits;
		this.salaryRanges = salaryRanges;
		this.requiredEducation = requiredEducation;
		this.country = country;
		this.state = state;
		this.date = date;
	}

	public Long getId() {return id;}

	public Recruiter getRecruiter() {return recruiter;}

	public String getType() {return type;}

	public String getWorkDuration() {return workDuration;}

	public String getDescription() {return description;}

	public List<String> getStacks() {return stacks;}

	public String getEmploymentType() {return employmentType;}

	public List<String> getBenefits() {return benefits;}

	public List<String> getSalaryRanges() {return salaryRanges;}

	public String getRequiredEducation() {return requiredEducation;}

	public String getCountry() {return country;}

	public String getState() {return state;}

	public Instant getDate() {return date;}

	public void setId(Long id) {this.id = id;}

	public void setRecruiter(Recruiter recruiter) {this.recruiter = recruiter;}

	public void setType(String type) {this.type = type;}

	public void setWorkDuration(String workDuration) {this.workDuration = workDuration;}

	public void setDescription(String description) {this.description = description;}

	public void setStacks(List<String> stacks) {this.stacks = stacks;}

	public void setEmploymentType(String employmentType) {this.employmentType = employmentType;}

	public void setBenefits(List<String> benefits) {this.benefits = benefits;}

	public void setSalaryRanges(List<String> salaryRanges) {this.salaryRanges = salaryRanges;}

	public void setRequiredEducation(String requiredEducation) {this.requiredEducation = requiredEducation;}

	public void setCountry(String country) {this.country = country;}

	public void setState(String state) {this.state = state;}

	public void setDate(Instant date) {this.date = date;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return Objects.equals(id, position.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
