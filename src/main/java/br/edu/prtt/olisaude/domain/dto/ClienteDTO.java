package br.edu.prtt.olisaude.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTO {

    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private Set<ProblemaSaudeDTO> problemasSaude;

}
