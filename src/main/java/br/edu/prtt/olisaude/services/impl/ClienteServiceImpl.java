package br.edu.prtt.olisaude.services.impl;

import br.edu.prtt.olisaude.domain.Cliente;
import br.edu.prtt.olisaude.domain.ProblemaSaude;
import br.edu.prtt.olisaude.domain.dto.ClienteDTO;
import br.edu.prtt.olisaude.domain.dto.ProblemaSaudeDTO;
import br.edu.prtt.olisaude.repository.ClienteRepository;
import br.edu.prtt.olisaude.repository.ProblemaSaudeRepository;
import br.edu.prtt.olisaude.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ClienteServiceImpl implements ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProblemaSaudeRepository problemaSaudeRepository;

    @Override
    @Transactional
    public Cliente salvar(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setSexo(dto.getSexo());
        cliente.setDataNascimento(dto.getDataNascimento());
        Set<ProblemaSaude> problemas = converterProblemas(cliente, dto.getProblemasSaude());
        cliente.setProblemasSaude(problemas);
        clienteRepository.save(cliente);
        return  cliente;
    }

    private Set<ProblemaSaude> converterProblemas(Cliente cliente, Set<ProblemaSaudeDTO> problemas) {
        if(problemas.isEmpty()){
            return null;
        }else{
            return problemas.stream()
                    .map(dto -> {
                        Integer idProblema = dto.getId();
                        ProblemaSaude problemaSaude = problemaSaudeRepository.findById(idProblema)
                                .orElseThrow(() ->
                                        new ResponseStatusException(HttpStatus.NOT_FOUND,"Problema de saude nao encontrado"));
                        return problemaSaude;
                    }).collect(Collectors.toSet());
        }

    }
}
