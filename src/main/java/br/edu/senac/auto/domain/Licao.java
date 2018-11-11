package br.edu.senac.auto.domain;

import javax.persistence.*;

@Entity
@Table(name = "LICAO")
public class Licao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COD_CATEGORIA")
    private Categoria categoria;

    @ManyToOne
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
}
