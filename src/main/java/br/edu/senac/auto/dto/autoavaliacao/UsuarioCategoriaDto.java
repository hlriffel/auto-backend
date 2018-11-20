package br.edu.senac.auto.dto.autoavaliacao;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsuarioCategoriaDto {

    private Long id;
    private String nome;

    @JsonIgnore
    private Long categoriaId;

    public UsuarioCategoriaDto(Long id, String nome, Long categoriaId) {
        this.id = id;
        this.nome = nome;
        this.categoriaId = categoriaId;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
