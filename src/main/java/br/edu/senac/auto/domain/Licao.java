package br.edu.senac.auto.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "LICAO")
public class Licao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COD_ID")
    private Long id;

    @NotNull
    @JoinColumn(name = "NOM_LICAO")
    private String nome;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "COD_CATEGORIA")
    private Categoria categoria;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "COD_CARACTERISTICA")
    private Caracteristica caracteristica;

    public Licao() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
