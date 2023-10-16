package com.prosantosgui.techunter.config;

import com.prosantosgui.techunter.model.Application;
import com.prosantosgui.techunter.model.Candidate;
import com.prosantosgui.techunter.model.Position;
import com.prosantosgui.techunter.model.Recruiter;
import com.prosantosgui.techunter.repositories.ApplicationRepository;
import com.prosantosgui.techunter.repositories.CandidateRepository;
import com.prosantosgui.techunter.repositories.PositionRepository;
import com.prosantosgui.techunter.repositories.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private PositionRepository positionRepository;

	@Autowired
	private RecruiterRepository recruiterRepository;

	@Autowired
	private ApplicationRepository ApplicationRepository;

	@Autowired
	private CandidateRepository CandidateRepository;

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
		
		Recruiter rec1 = new Recruiter("DeskTec", "1234", "Felipe Alves", "Brasil", "Desk Tecnologias");
		Recruiter rec2 = new Recruiter("Task301", "12525", "Julia Souza", "Brasil", "Rust Solutions");
		
		recruiterRepository.saveAll(Arrays.asList(rec1, rec2));
		
	    Position position1 = new Position(
	            null,                         // idPosition
	            rec1,           // recruiter (você deve criar um objeto Recruiter apropriado)
	            "Desenvolvedor Java",            // tipo
	            "Tempo integral",           // tempoTrabalho
	            "Descrição da position",        // descricao
	            stacks,                     // stacks
	            "CLT",                      // regime
	            beneficios,                 // beneficios
	            salario,                    // salario
	            "Superior completo",        // escolaridade
	            "Brasil",                   // pais
	            "São Paulo",                // estado
	            Instant.now()
	        );
	    Position position2 = new Position(
	    		null,                         // idPosition
	    		rec2,           // recruiter (você deve criar um objeto Recruiter apropriado)
	    		"Java Developer",            // tipo
	    		"Tempo integral",           // tempoTrabalho
	    		"Descrição da position",        // descricao
	    		stacks,                     // stacks
	    		"PJ",                      // regime
	    		beneficios,                 // beneficios
	    		salario,                    // salario
	    		"Superior completo",        // escolaridade
	    		"Estados Unidos",                   // pais
	    		"California",                // estado
	    		Instant.now()
	    		);
		Position position3 = new Position(
	    		null,                         // idPosition
	    		rec2,           // recruiter (você deve criar um objeto Recruiter apropriado)
	    		"C# Developer",            // tipo
	    		"Tempo integral",           // tempoTrabalho
	    		"Descrição da position",        // descricao
	    		stacks,                     // stacks
	    		"PJ",                      // regime
	    		beneficios,                 // beneficios
	    		salario,                    // salario
	    		"Superior completo",        // escolaridade
	    		"Estados Unidos",                   // pais
	    		"New York",                // estado
	    		Instant.now()
	    		);
	    
	    positionRepository.saveAll(Arrays.asList(position1, position2, position3));

		Candidate cand1 = new Candidate("Vino23","21451","Vinicius","2154156165","Rio de Janeiro",true,"RJ");
		Candidate cand2 = new Candidate("Hid89","51561","Romeu","2191353517","Rio de Janeiro",true,"RJ");

		CandidateRepository.saveAll(Arrays.asList(cand1, cand2));

		Application Application1 = new Application(cand1, position2, Instant.now());
		Application Application2 = new Application(cand1, position1, Instant.now());
		Application Application3 = new Application(cand2, position2, Instant.now());
		Application Application4 = new Application(cand2, position3, Instant.now());

		ApplicationRepository.saveAll(Arrays.asList(Application1, Application2, Application3, Application4));

	}
	
}
