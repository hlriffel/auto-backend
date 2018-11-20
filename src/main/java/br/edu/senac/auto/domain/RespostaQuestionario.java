package br.edu.senac.auto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RESPOSTA_QUESTIONARIO")
public class RespostaQuestionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COD_RESPOSTA")
    @JsonIgnore
    private Resposta resposta;

    @ManyToOne
    @JoinColumn(name = "COD_CARACTERISTICA")
    private Caracteristica caracteristica;

    @NotNull
    @Column(name = "NUM_VALOR")
    private Double valor;

    public RespostaQuestionario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Resposta getResposta() {
        return resposta;
    }

    public void setResposta(Resposta resposta) {
        this.resposta = resposta;
    }

    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
