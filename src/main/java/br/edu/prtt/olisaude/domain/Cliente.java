package br.edu.prtt.olisaude.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;



    @ManyToMany
    @JoinTable(name = "problemas_clientes",
            joinColumns = @JoinColumn(name = "cliente_fk"),
            inverseJoinColumns = @JoinColumn(name = "problema_fk"))
    private Set<ProblemaSaude> problemasSaude = new HashSet<>();


    public Cliente(){

    }

    public Cliente(String nome, String sexo) {
        this.nome = nome;
        this.dataNascimento = LocalDate.now();
        this.sexo = sexo;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Set<ProblemaSaude> getProblemasSaude() {
        return problemasSaude;
    }

    public void setProblemasSaude(Set<ProblemaSaude> problemasSaude) {
        this.problemasSaude = problemasSaude;
    }

    public void adicionarProblema(ProblemaSaude problemaSaude){
        this.problemasSaude.add(problemaSaude);
    }

    public Integer somaProblemas(){
        return this.problemasSaude.stream()
                .map(ProblemaSaude::getGrau)
                .reduce(0, Integer::sum);
    }

    public Double gethealthScores(){
        return  (1 / (1 + Math.pow(Math.E, -(-2.8 + somaProblemas())) )) * 100;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "idd=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}
