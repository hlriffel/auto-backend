package br.edu.senac.auto.controller;

import br.edu.senac.auto.domain.Licao;
import br.edu.senac.auto.repository.generic.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/licao")
@CrossOrigin(origins = "*")
public class LicaoController {

    private IGenericRepository<Licao> repository;

    @Autowired
    public void setRepository(IGenericRepository<Licao> repository) {
        this.repository = repository;
        this.repository.setClazz(Licao.class);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Licao> saveLicao(@RequestBody Licao licao) {
        if (licao.getId() == null) {
            repository.add(licao);
        } else {
            repository.update(licao);
        }

        return ResponseEntity.ok(licao);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteLicao(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    @GetMapping
    public List<Licao> getAllLicao() {
        return repository.findAll();
    }

}
