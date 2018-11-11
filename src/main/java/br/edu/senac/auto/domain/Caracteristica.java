package br.edu.senac.auto.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CARACTERISTICA")
public class Caracteristica {

    @Id
    @Column(name = "COD_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "NOM_CARACTERISTICA")
    @Length(max = 80)
    private String nome;
    
    @NotNull
    @Column(name = "DES_OBSERVACAO")
    @Length(max = 255)
    private String observacao;

    public Caracteristica() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
