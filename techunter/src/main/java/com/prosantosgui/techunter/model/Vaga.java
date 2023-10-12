package com.prosantosgui.techunter.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@SuppressWarnings("ALL")
@Table(name = "vaga_model")
@Entity
public class Vaga implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vaga", nullable = false)
	private Long idVaga;
	
	@ManyToOne
	@JoinColumn(name = "id_recrutador")
	@JsonIgnore
	private Recrutador recrutador;
	
	@Column(nullable = false)
	private String tipo;
	
	@Column(name = "tempo_trabalho", nullable = false)
	private String tempoTrabalho;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private List<String> stacks;
	
	@Column(nullable = false)
	private String regime;
	private List<String> beneficios;
	
	@Column(nullable = false)
	private List<String> salario;
	
	private String escolaridade;
	
	@Column(nullable = false)
	private String pais;
	
	private String estado;
	
	@Column(name = "data_vaga")
	private Instant data;

	@OneToMany(mappedBy = "id.vaga")
	private Set<Candidatura> candidatos = new HashSet<>();

	public Vaga(){

	}

	public Vaga(Long idVaga, Recrutador recrutador, String tipo, String tempoTrabalho, String descricao,
			List<String> stacks, String regime, List<String> beneficios, List<String> salario, String escolaridade,
			String pais, String estado, Instant data) {
		super();
		this.idVaga = idVaga;
		this.recrutador = recrutador;
		this.tipo = tipo;
		this.tempoTrabalho = tempoTrabalho;
		this.descricao = descricao;
		this.stacks = stacks;
		this.regime = regime;
		this.beneficios = beneficios;
		this.salario = salario;
		this.escolaridade = escolaridade;
		this.pais = pais;
		this.estado = estado;
		this.data = data;
	}

	public Long getIdVaga() {
		return idVaga;
	}

	public Recrutador getRecrutador() {
		return recrutador;
	}

	public String getTipo() {
		return tipo;
	}

	public String getTempoTrabalho() {
		return tempoTrabalho;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<String> getStacks() {
		return stacks;
	}

	public String getRegime() {
		return regime;
	}

	public List<String> getBeneficios() {
		return beneficios;
	}

	public List<String> getSalario() {
		return salario;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public String getPais() {
		return pais;
	}

	public String getEstado() {
		return estado;
	}

	public Instant getData() {
		return data;
	}

	/*
	public HashSet<Candidato> getCandidatos() {
		return candidatos;
	}*/

	public void setIdVaga(Long idVaga) {
		this.idVaga = idVaga;
	}

	public void setRecrutador(Recrutador recrutador) {
		this.recrutador = recrutador;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setTempoTrabalho(String tempoTrabalho) {
		this.tempoTrabalho = tempoTrabalho;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setStacks(List<String> stacks) {
		this.stacks = stacks;
	}

	public void setRegime(String regime) {
		this.regime = regime;
	}

	public void setBeneficios(List<String> beneficios) {
		this.beneficios = beneficios;
	}

	public void setSalario(List<String> salario) {
		this.salario = salario;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idVaga);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vaga other = (Vaga) obj;
		return Objects.equals(idVaga, other.idVaga);
	}
	
	

}
