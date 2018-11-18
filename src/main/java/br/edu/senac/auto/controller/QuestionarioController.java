package br.edu.senac.auto.controller;

import br.edu.senac.auto.domain.Questionario;
import br.edu.senac.auto.repository.generic.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/questionario")
@CrossOrigin(origins = "*")
public class QuestionarioController {
    private IGenericRepository<Questionario> repository;

    @Autowired
    public void setRepository(IGenericRepository<Questionario> repository) {
        this.repository = repository;
        this.repository.setClazz(Questionario.class);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Questionario> saveQuestionario(@RequestBody Questionario questionario) {
        if (questionario.getId() == null) {
            repository.add(questionario);
        } else {
            repository.update(questionario);
        }

        return ResponseEntity.ok(questionario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteQuestionario(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    @GetMapping
    public List<Questionario> getAllQuestionario() {
        return repository.findAll();
    }

}
