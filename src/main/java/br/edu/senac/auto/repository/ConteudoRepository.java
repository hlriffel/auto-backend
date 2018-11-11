package br.edu.senac.auto.repository;

import br.edu.senac.auto.domain.Conteudo;
import br.edu.senac.auto.domain.Licao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConteudoRepository extends JpaRepository<Conteudo, Long> {

    @Query("SELECT c FROM Conteudo c WHERE c.intro = 'S'")
    public List<Conteudo> getIntroContents();

    public List<Conteudo> getConteudoByLicao(Licao lecture);

    @Query("SELECT c FROM Conteudo c WHERE c.licao.id = ?1")
    public List<Conteudo> getConteudoByLicao(Long lectureId);
}
