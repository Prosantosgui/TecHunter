package com.prosantosgui.techunter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "candidato_vaga")
@Entity
public class CandidatoVaga {

	
	private String loginCand;
	private Integer idVaga;
}
