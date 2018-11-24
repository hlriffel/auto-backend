package br.edu.senac.auto.repository;

import br.edu.senac.auto.domain.Licao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LicaoRepository extends JpaRepository<Licao, Long> {

    @Query("SELECT l FROM Licao l WHERE l.categoria.id = ?1")
    List<Licao> getLicaoByCategoria(Long categoriaId);

    @Query("SELECT l FROM Licao l WHERE l.caracteristica.id = ?1")
    List<Licao> getLicaoByCaracteristica(Long caracteristicaId);

    @Query("SELECT l FROM Licao l WHERE l.categoria.id = ?1 AND l.caracteristica.id = ?2")
    List<Licao> getLicaoCategoriaAndCaracteristica(Long categoriaId, Long caracteristicaId);
}
