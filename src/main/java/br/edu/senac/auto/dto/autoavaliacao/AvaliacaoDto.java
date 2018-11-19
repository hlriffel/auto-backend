package br.edu.senac.auto.dto.autoavaliacao;

import java.util.List;

public class AvaliacaoDto {

    private UsuarioCategoriaDto usuarioCategoria;
    private List<PerguntaDto> perguntas;

    public AvaliacaoDto(UsuarioCategoriaDto usuarioCategoria, List<PerguntaDto> perguntas) {
        this.usuarioCategoria = usuarioCategoria;
        this.perguntas = perguntas;
    }

    public UsuarioCategoriaDto getUsuarioCategoria() {
        return usuarioCategoria;
    }

    public void setUsuarioCategoria(UsuarioCategoriaDto usuarioCategoria) {
        this.usuarioCategoria = usuarioCategoria;
    }

    public List<PerguntaDto> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<PerguntaDto> perguntas) {
        this.perguntas = perguntas;
    }
}
