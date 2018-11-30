package br.edu.senac.auto.repository;

import br.edu.senac.auto.domain.Usuario;
import br.edu.senac.auto.domain.UsuarioCategoria;
import br.edu.senac.auto.dto.autoavaliacao.UsuarioCategoriaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioCategoriaRepository extends JpaRepository<UsuarioCategoria, Long> {

    @Query("SELECT uc FROM UsuarioCategoria uc WHERE uc.usuario.id = ?1")
    List<UsuarioCategoria> getUserCategories(Long userId);

    @Query( " SELECT new br.edu.senac.auto.dto.autoavaliacao.UsuarioCategoriaDto(uc.id, c.nome, c.id) " +
            " FROM UsuarioCategoria uc " +
            " INNER JOIN uc.categoria c " +
            " WHERE uc.usuario.id = ?1 ")
    List<UsuarioCategoriaDto> getUsuarioCategoriaDto(Long userId);

    @Query("SELECT count(uc) FROM UsuarioCategoria uc WHERE uc.usuario.id = ?1 AND uc.categoria.id = ?2")
    Integer getQtdeByUsuarioAndCategoria(Long usuarioId, Long categoriaId);

    @Modifying
    @Query("DELETE FROM UsuarioCategoria uc WHERE uc.usuario.id = ?1 AND uc.categoria.id NOT IN (?2)")
    void deleteByUsuarioWithoutCategorias(Long usuarioId, List<Long> categoriaIds);
}
