package br.edu.senac.auto.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USUARIO_CATEGORIA")
public class UsuarioCategoria {

    @Id
    @Column(name = "COD_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "COD_USUARIO")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "COD_CATEGORIA")
    private Categoria categoria;

    public UsuarioCategoria() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
