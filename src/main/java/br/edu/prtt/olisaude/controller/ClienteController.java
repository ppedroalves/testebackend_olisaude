package br.edu.prtt.olisaude.controller;


import br.edu.prtt.olisaude.domain.Cliente;
import br.edu.prtt.olisaude.domain.dto.ClienteDTO;
import br.edu.prtt.olisaude.repository.ClienteRepository;
import br.edu.prtt.olisaude.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.math.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;


    @GetMapping()
    public List<Cliente> buscarTodosClientes(){
        return clienteRepository.findAll();
    }


    @GetMapping("{id}")
    public Cliente buscarCliente(@PathVariable Integer id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("{id}")
    public void atualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente){
        clienteRepository.findById(id)
                .map(c -> {
                  cliente.setId(c.getId());
                  clienteRepository.save(cliente);
                  return c;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PostMapping()
    public Integer cadastrarCliente(@RequestBody ClienteDTO clienteDTO){
        Cliente clienteSalvo = clienteService.salvar(clienteDTO);
        return clienteSalvo.getId();
    }


    @GetMapping("/score")
    public List<Cliente> clientesComMaiorRisco(){
        return clienteRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Cliente::gethealthScores).reversed())
                .limit(10)
                .collect(Collectors.toList());

    }

}
