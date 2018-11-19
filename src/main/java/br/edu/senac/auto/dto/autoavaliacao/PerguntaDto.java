package br.edu.senac.auto.dto.autoavaliacao;

import br.edu.senac.auto.domain.Caracteristica;

public class PerguntaDto {

    private Long id;
    private String pergunta;
    private Caracteristica caracteristica;
    private Double resposta = 0D;

    public PerguntaDto(Long id, String pergunta, Caracteristica caracteristica) {
        this.id = id;
        this.pergunta = pergunta;
        this.caracteristica = caracteristica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    public Double getResposta() {
        return resposta;
    }

    public void setResposta(Double resposta) {
        this.resposta = resposta;
    }
}
