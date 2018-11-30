package br.edu.senac.auto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USUARIO_CATEGORIA")
public class UsuarioCategoria {

    @Id
    @Column(name = "COD_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "COD_USUARIO")
    @JsonIgnore
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "COD_CATEGORIA")
    private Categoria categoria;

    @OneToMany(mappedBy = "usuarioCategoria")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Resposta> respostas;

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

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }
}
