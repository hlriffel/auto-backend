package br.edu.senac.auto.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.senac.auto.domain.Caracteristica;

public interface  CaracteristicaRepository extends JpaRepository<Caracteristica, Long> {

}
