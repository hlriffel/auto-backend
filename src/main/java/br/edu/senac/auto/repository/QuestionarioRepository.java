package br.edu.senac.auto.repository;

import br.edu.senac.auto.domain.Questionario;
import br.edu.senac.auto.dto.autoavaliacao.PerguntaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {

    @Query( " SELECT new br.edu.senac.auto.dto.autoavaliacao.PerguntaDto(q.id, q.nome, q.caracteristica) " +
            " FROM Questionario q " +
            " WHERE q.categoria.id = ?1 ")
    List<PerguntaDto> getPerguntaDtos(Long categoriaId);

    @Query("SELECT q FROM Questionario q WHERE q.categoria.id = ?1")
    List<Questionario> getQuestionarioByCategoria(Long categoriaId);

    @Query("SELECT q FROM Questionario q WHERE q.caracteristica.id = ?1")
    List<Questionario> getQuestionarioByCaracteristica(Long caracteristicaId);

    @Query("SELECT q FROM Questionario q WHERE q.categoria.id = ?1 AND q.caracteristica.id = ?2")
    List<Questionario> getQuestionarioByCategoriaAndCaracteristica(Long categoriaId, Long caracteristicaId);
}
