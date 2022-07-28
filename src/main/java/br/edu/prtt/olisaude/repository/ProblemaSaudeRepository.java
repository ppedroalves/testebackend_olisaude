package br.edu.prtt.olisaude.repository;

import br.edu.prtt.olisaude.domain.ProblemaSaude;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemaSaudeRepository extends JpaRepository<ProblemaSaude, Integer> {
}
