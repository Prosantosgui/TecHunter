package com.prosantosgui.techunter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "candidato_vaga")
@Entity
public class CandidatoVaga {

	@Id
	private Long id;
	private String loginCand;
	private Integer idVaga;
}
