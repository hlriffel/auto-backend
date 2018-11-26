package br.edu.senac.auto.controller;

import br.edu.senac.auto.domain.Licao;
import br.edu.senac.auto.repository.LicaoRepository;
import br.edu.senac.auto.repository.generic.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/licao")
@CrossOrigin(origins = "*")
public class LicaoController {

    @Autowired
    private LicaoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Licao> saveLicao(@RequestBody Licao licao) {
        repository.save(licao);

        return ResponseEntity.ok(licao);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteLicao(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    @GetMapping
    public List<Licao> getLicoes(@RequestParam Map<String, String> queryParams) {
        List<Licao> licoes = new ArrayList<>();

        if (queryParams.isEmpty()) {
            licoes.addAll(repository.findAll());
        } else {
            if (queryParams.get("categoriaId") != null
                    && queryParams.get("caracteristicaId") != null) {
                licoes.addAll(repository.getLicaoCategoriaAndCaracteristica(
                        Long.valueOf(queryParams.get("categoriaId")),
                        Long.valueOf(queryParams.get("caracteristicaId"))));
            } else {
                if (queryParams.get("categoriaId") != null) {
                    licoes.addAll(repository.getLicaoByCategoria(Long.valueOf(queryParams.get("categoriaId"))));
                }

                if (queryParams.get("caracteristicaId") != null) {
                    licoes.addAll(repository.getLicaoByCaracteristica(Long.valueOf(queryParams.get("caracteristicaId"))));
                }
            }
        }

        return licoes;
    }

    @GetMapping("/{licaoId}")
    public ResponseEntity<Licao> getLicaoById(@PathVariable Long licaoId) {
        Optional<Licao> licao = repository.findById(licaoId);

        if (licao.isPresent()) {
            return ResponseEntity.ok(licao.get());
        }

        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/{categoriaId}/{caracteristicaId}")
    public List<Licao> getLicaoCategoriaAndCaracteristica(@PathVariable Long categoriaId, @PathVariable Long caracteristicaId) {
        return repository.getLicaoCategoriaAndCaracteristica(categoriaId,caracteristicaId);
    }
}
