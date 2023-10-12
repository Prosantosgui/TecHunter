package com.prosantosgui.techunter.model;

import jakarta.persistence.*;

import java.util.*;

@Table(name = "candidato_model")
@Entity
public class Candidato {
	
	@Id
	@Column(name = "login_cand", nullable = false, length = 15)
	private String loginCand;
	
	@Column(nullable = false, length = 15)
	private String senha;

	@Column(nullable = false, length = 30)
	private String nome;
	
	@Column(length = 20)
	private String telefone;
	
	@Column(length = 20)
	private String localizacao;
	
	@Column(name = "inicio_imediato")
	private boolean inicioImediato;

	@Column(length = 20)
	private String estado;

	private String email;

	@OneToMany(mappedBy = "id.candidato")
	private Set<Candidatura> candidaturas = new HashSet<>();

	public Candidato(){
	}

	public Candidato(String loginCand, String senha, String nome, String telefone, String localizacao, boolean inicioImediato, String estado) {
		super();
		this.loginCand = loginCand;
		this.senha = senha;
		this.nome = nome;
		this.telefone = telefone;
		this.localizacao = localizacao;
		this.inicioImediato = inicioImediato;
		this.estado = estado;
	}

	public String getLoginCand() {
		return loginCand;
	}

	public void setLoginCand(String loginCand) {
		this.loginCand = loginCand;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public boolean isInicioImediato() {
		return inicioImediato;
	}

	public void setInicioImediato(boolean inicioImediato) {
		this.inicioImediato = inicioImediato;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Candidatura> getCandidaturas() {return candidaturas;}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Candidato candidato = (Candidato) o;
		return loginCand.equals(candidato.loginCand) && email.equals(candidato.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(loginCand, email);
	}
}
