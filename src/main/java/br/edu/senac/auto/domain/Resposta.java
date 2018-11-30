package br.edu.senac.auto.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "RESPOSTA")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COD_USUARIO_CATEGORIA")
    private UsuarioCategoria usuarioCategoria;

    @NotNull
    @Column(name = "DAT_RESPOSTA")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date datResposta = new Date();

    @OneToMany(mappedBy = "resposta")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<RespostaQuestionario> respostas;

    public Resposta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioCategoria getUsuarioCategoria() {
        return usuarioCategoria;
    }

    public void setUsuarioCategoria(UsuarioCategoria usuarioCategoria) {
        this.usuarioCategoria = usuarioCategoria;
    }

    public Date getDatResposta() {
        return datResposta;
    }

    public void setDatResposta(Date datResposta) {
        this.datResposta = datResposta;
    }

    public List<RespostaQuestionario> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<RespostaQuestionario> respostas) {
        this.respostas = respostas;
    }
}
