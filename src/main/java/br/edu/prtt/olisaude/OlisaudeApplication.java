package br.edu.prtt.olisaude;

import br.edu.prtt.olisaude.domain.Cliente;
import br.edu.prtt.olisaude.domain.ProblemaSaude;
import br.edu.prtt.olisaude.repository.ClienteRepository;
import br.edu.prtt.olisaude.repository.ProblemaSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class OlisaudeApplication {

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired ClienteRepository clienteRepository,
											   @Autowired ProblemaSaudeRepository probSaudeRepository){
		return args -> {
			Cliente c = new Cliente("Pedro", "MASCULINO" );
			Cliente c1 = new Cliente("Rogerio", "MASCULINO");
			Cliente c2 = new Cliente("Ester", "FEMININO");
			ProblemaSaude p = new ProblemaSaude("Diabete", 1);
			ProblemaSaude p1 = new ProblemaSaude("Cancer", 2);
			ProblemaSaude p2 = new ProblemaSaude("Osteopore", 3);
			List<ProblemaSaude> problemas = Arrays.asList(p,p1,p2);
			probSaudeRepository.saveAll(problemas);
			c.adicionarProblema(p);
			c.adicionarProblema(p1);
			c.adicionarProblema(p2);
			c1.adicionarProblema(p);
			c1.adicionarProblema(p2);
			c2.adicionarProblema(p1);
			c2.adicionarProblema(p2);
			List<Cliente> clientes = Arrays.asList(c,c1,c2);
			clienteRepository.saveAll(clientes);

		};
	}


	public static void main(String[] args) {
		SpringApplication.run(OlisaudeApplication.class, args);
	}

}
