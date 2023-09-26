package com.prosantosgui.techunter.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "vaga_model")
@Entity
public class Vaga {

	@Id
	@Column(name = "id_vaga", nullable = false)
	private Integer idVaga;
	
	@Column(name = "id_recrutador", nullable = false)
	private String idRecrutador;
	
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
	private Date data;
	
}
