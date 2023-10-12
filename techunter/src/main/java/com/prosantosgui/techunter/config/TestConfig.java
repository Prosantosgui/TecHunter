package com.prosantosgui.techunter.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.prosantosgui.techunter.model.Candidato;
import com.prosantosgui.techunter.model.Candidatura;
import com.prosantosgui.techunter.repositories.CandidatoRepository;
import com.prosantosgui.techunter.repositories.CandidaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.prosantosgui.techunter.model.Recrutador;
import com.prosantosgui.techunter.model.Vaga;
import com.prosantosgui.techunter.repositories.RecrutadorRepository;
import com.prosantosgui.techunter.repositories.VagaRepository;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private VagaRepository vagaRepository;

	@Autowired
	private RecrutadorRepository recrutadorRepository;

	@Autowired
	private CandidaturaRepository candidaturaRepository;

	@Autowired
	private CandidatoRepository candidatoRepository;

	@Override
	public void run(String... args) throws Exception {
		
	      List<String> stacks = new ArrayList<>();
	        stacks.add("Java");
	        stacks.add("Spring Boot");

	        // Criar uma lista de benefícios
	        List<String> beneficios = new ArrayList<>();
	        beneficios.add("Plano de saúde");
	        beneficios.add("Vale-refeição");

	        // Criar uma lista de salários
	        List<String> salario = new ArrayList<>();
	        salario.add("5000");
	        salario.add("6000");
		
		Recrutador rec1 = new Recrutador("DeskTec", "1234", "Felipe Alves", "Brasil", "Desk Tecnologias");
		Recrutador rec2 = new Recrutador("Task301", "12525", "Julia Souza", "Brasil", "Rust Solutions");
		
		recrutadorRepository.saveAll(Arrays.asList(rec1, rec2));
		
	    Vaga vaga1 = new Vaga(
	            null,                         // idVaga
	            rec1,           // recrutador (você deve criar um objeto Recrutador apropriado)
	            "Desenvolvedor Java",            // tipo
	            "Tempo integral",           // tempoTrabalho
	            "Descrição da vaga",        // descricao
	            stacks,                     // stacks
	            "CLT",                      // regime
	            beneficios,                 // beneficios
	            salario,                    // salario
	            "Superior completo",        // escolaridade
	            "Brasil",                   // pais
	            "São Paulo",                // estado
	            Instant.now()
	        );
	    Vaga vaga2 = new Vaga(
	    		null,                         // idVaga
	    		rec2,           // recrutador (você deve criar um objeto Recrutador apropriado)
	    		"Java Developer",            // tipo
	    		"Tempo integral",           // tempoTrabalho
	    		"Descrição da vaga",        // descricao
	    		stacks,                     // stacks
	    		"PJ",                      // regime
	    		beneficios,                 // beneficios
	    		salario,                    // salario
	    		"Superior completo",        // escolaridade
	    		"Estados Unidos",                   // pais
	    		"California",                // estado
	    		Instant.now()
	    		);
		Vaga vaga3 = new Vaga(
	    		null,                         // idVaga
	    		rec2,           // recrutador (você deve criar um objeto Recrutador apropriado)
	    		"C# Developer",            // tipo
	    		"Tempo integral",           // tempoTrabalho
	    		"Descrição da vaga",        // descricao
	    		stacks,                     // stacks
	    		"PJ",                      // regime
	    		beneficios,                 // beneficios
	    		salario,                    // salario
	    		"Superior completo",        // escolaridade
	    		"Estados Unidos",                   // pais
	    		"New York",                // estado
	    		Instant.now()
	    		);
	    
	    vagaRepository.saveAll(Arrays.asList(vaga1, vaga2, vaga3));

		Candidato cand1 = new Candidato("Vino23","21451","Vinicius","2154156165","Rio de Janeiro",true,"RJ");
		Candidato cand2 = new Candidato("Hid89","51561","Romeu","2191353517","Rio de Janeiro",true,"RJ");

		candidatoRepository.saveAll(Arrays.asList(cand1, cand2));

		Candidatura candidatura1 = new Candidatura(cand1, vaga2, Instant.now());
		Candidatura candidatura2 = new Candidatura(cand1, vaga1, Instant.now());
		Candidatura candidatura3 = new Candidatura(cand2, vaga2, Instant.now());
		Candidatura candidatura4 = new Candidatura(cand2, vaga3, Instant.now());

		candidaturaRepository.saveAll(Arrays.asList(candidatura1, candidatura2, candidatura3, candidatura4));

	}
	
}
