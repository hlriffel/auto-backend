package br.edu.senac.auto.repository;

import br.edu.senac.auto.domain.Resposta;
import br.edu.senac.auto.domain.UsuarioCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    void deleteByUsuarioCategoria(UsuarioCategoria usuarioCategoria);

    @Query("SELECT r FROM Resposta r WHERE r.usuarioCategoria.usuario.id = ?1 ")
    List<Resposta> getRespostasByUsuario(Long usuarioId);
}
