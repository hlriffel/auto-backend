package br.edu.senac.auto.controller;

import br.edu.senac.auto.domain.Caracteristica;

import org.springframework.web.bind.annotation.DeleteMapping;
import br.edu.senac.auto.repository.generic.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/caracteristica")
@CrossOrigin(origins = "*")
public class CaracteristicaController {

    private IGenericRepository<Caracteristica> repository;

    @Autowired
    public void setRepository(IGenericRepository<Caracteristica> repository) {
        this.repository = repository;
        this.repository.setClazz(Caracteristica.class);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Caracteristica> saveCaracteristica(@RequestBody Caracteristica caracteristica) {
        if (caracteristica.getId() == null) {
            repository.add(caracteristica);
        } else {
            repository.update(caracteristica);
        }

        return ResponseEntity.ok(caracteristica);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteCaracteristica(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    @GetMapping
    public List<Caracteristica> getAllCaracteristica() {
        return repository.findAll();
    }

}
