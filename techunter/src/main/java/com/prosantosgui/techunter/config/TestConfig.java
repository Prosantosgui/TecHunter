package com.prosantosgui.techunter.config;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	            "Desenvolvedor",            // tipo
	            "Tempo integral",           // tempoTrabalho
	            "Descrição da vaga",        // descricao
	            stacks,                     // stacks
	            "CLT",                      // regime
	            beneficios,                 // beneficios
	            salario,                    // salario
	            "Superior completo",        // escolaridade
	            "Brasil",                   // pais
	            "São Paulo",                // estado
	            Instant.now()               // data (você pode ajustar isso de acordo com a data desejada)
	        );
	    
	    vagaRepository.save(vaga1);
	}
	
	
}
