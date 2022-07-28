package br.edu.prtt.olisaude.services;

import br.edu.prtt.olisaude.domain.Cliente;
import br.edu.prtt.olisaude.domain.dto.ClienteDTO;
import org.springframework.stereotype.Service;



public interface ClienteService {

    Cliente salvar(ClienteDTO dto);
}
