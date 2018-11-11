package br.edu.senac.auto.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CONTEUDO")
public class Conteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COD_LICAO")
    private Licao licao;

    @NotNull
    @Length(max = 1)
    @Column(name = "IND_INTRODUCAO")
    private String intro;

    @NotNull
    @Length(max = 1)
    @Column(name = "IND_TIPO")
    private String tipo;

    @NotNull
    @Column(name = "NUM_ORDEM")
    private Integer ordem;

    @NotNull
    @Column(name = "DES_CHAVE")
    private String key;

    @NotNull
    @Column(name = "DES_CAMINHO")
    private String caminho;

    public Conteudo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Licao getLicao() {
        return licao;
    }

    public void setLicao(Licao licao) {
        this.licao = licao;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public String getCaminho() {
        return caminho;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
}
