package br.edu.prtt.olisaude.domain;


import javax.persistence.*;
import java.util.Set;

@Entity
public class ProblemaSaude {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer grau;

    public ProblemaSaude() {
    }

    public ProblemaSaude(String nome, Integer grau) {
        this.nome = nome;
        this.grau = grau;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getGrau() {
        return grau;
    }

    public void setGrau(Integer grau) {
        this.grau = grau;
    }


}
