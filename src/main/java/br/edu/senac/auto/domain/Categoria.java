package br.edu.senac.auto.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {

    @Id
    @Column(name = "COD_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "NOM_CATEGORIA")
    @Length(max = 80)
    private String nome;

    public Categoria() {

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

    public void setNome(String Categoria) {
        this.nome = Categoria;
    }

}
