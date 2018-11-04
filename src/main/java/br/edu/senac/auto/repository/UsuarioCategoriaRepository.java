package br.edu.senac.auto.repository;

import br.edu.senac.auto.domain.Usuario;
import br.edu.senac.auto.domain.UsuarioCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioCategoriaRepository extends JpaRepository<UsuarioCategoria, Long> {

    public void deleteByUsuario(Usuario usuario);

    @Query("SELECT uc FROM UsuarioCategoria uc WHERE uc.usuario.id = ?1")
    public List<UsuarioCategoria> getUserCategories(Long userId);

}
