package com.prosantosgui.techunter.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Table(name = "recrutador_model")
@Entity
public class Recrutador {

	@Id
	@PrimaryKeyJoinColumn(name = "login_rec")
	private String loginRec;
	
	@Column(nullable = false, length = 15)
	private String senha;
	
	@Column(name = "nome_perfil", nullable = false, length = 35)
	private String nome;
	
	@Column(nullable = false, length = 20)
	private String pais;
	
	@Column(length = 20)
	private String empresa;
	
	@OneToMany(mappedBy = "recrutador")
	private List<Vaga> vagas = new ArrayList<>();
	
	public Recrutador() {
	}

	public Recrutador(String loginRec, String senha, String nome, String pais, String empresa) {
		super();
		this.loginRec = loginRec;
		this.senha = senha;
		this.nome = nome;
		this.pais = pais;
		this.empresa = empresa;
	}

	public String getLoginRec() {
		return loginRec;
	}

	public String getSenha() {
		return senha;
	}

	public String getNome() {
		return nome;
	}

	public String getPais() {
		return pais;
	}

	public String getEmpresa() {
		return empresa;
	}
	
	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setLoginRec(String loginRec) {
		this.loginRec = loginRec;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(loginRec);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recrutador other = (Recrutador) obj;
		return Objects.equals(loginRec, other.loginRec);
	}

	@Override
	public String toString() {
		return "Recrutador [loginRec=" + loginRec + ", senha=" + senha + ", nome=" + nome + ", pais=" + pais
				+ ", empresa=" + empresa + "]";
	}

}
