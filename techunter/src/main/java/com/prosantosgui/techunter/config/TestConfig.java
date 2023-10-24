package com.prosantosgui.techunter.config;

import com.prosantosgui.techunter.model.Application;
import com.prosantosgui.techunter.model.Candidate;
import com.prosantosgui.techunter.model.Position;
import com.prosantosgui.techunter.model.Recruiter;
import com.prosantosgui.techunter.model.enums.PositionStatus;
import com.prosantosgui.techunter.services.ApplicationService;
import com.prosantosgui.techunter.services.CandidateService;
import com.prosantosgui.techunter.services.PositionService;
import com.prosantosgui.techunter.services.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private PositionService positionService;

	@Autowired
	private RecruiterService recruiterService;

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private CandidateService candidateService;

	@Override
	public void run(String... args) throws Exception {
		try {
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

			recruiterService.saveRecruiter(rec1);
			recruiterService.saveRecruiter(rec2);

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
					Instant.now(),
					PositionStatus.OPEN
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
					Instant.now(),
					PositionStatus.OPEN
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
					Instant.now(),
					PositionStatus.CLOSED
			);

			positionService.savePosition(position1);
			positionService.savePosition(position2);
			positionService.savePosition(position3);

			Candidate cand1 = new Candidate("Vino23", "21451", "Vinicius", "2154156165", "Rio de Janeiro", true, "RJ");
			Candidate cand2 = new Candidate("Hid89", "51561", "Romeu", "2191353517", "Rio de Janeiro", true, "RJ");

			candidateService.saveCandidate(cand1);
			candidateService.saveCandidate(cand2);

			Application application1 = new Application(cand1, position2, Instant.now());
			Application application2 = new Application(cand1, position1, Instant.now());
			Application application3 = new Application(cand2, position2, Instant.now());
			Application application4 = new Application(cand2, position3, Instant.now());

			applicationService.saveApplication(application1);
			applicationService.saveApplication(application2);
			applicationService.saveApplication(application3);
			applicationService.saveApplication(application4);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
