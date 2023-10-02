package com.prosantosgui.techunter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
	
}
