package br.edu.prtt.olisaude.repository;

import br.edu.prtt.olisaude.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ClienteRepository  extends JpaRepository<Cliente, Integer> {
}
