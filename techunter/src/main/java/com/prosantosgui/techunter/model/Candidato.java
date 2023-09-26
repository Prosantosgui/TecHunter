package com.prosantosgui.techunter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "candidato_model")
@Entity
public class Candidato {
	
	@Id
	@Column(name = "login_cand", nullable = false)
	private String loginCand;
	
	@Column(nullable = false)
	private String senha;

	@Column(nullable = false)
	private String nome;
	
	private String telefone;
	private String localizacao;
	
	@Column(name = "inicio_imediato")
	private boolean inicioImediato;
	private String estado;
	
}
