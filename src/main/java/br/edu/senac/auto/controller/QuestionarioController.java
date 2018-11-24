package br.edu.senac.auto.controller;

import br.edu.senac.auto.domain.Questionario;
import br.edu.senac.auto.repository.QuestionarioRepository;
import br.edu.senac.auto.repository.generic.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/questionario")
@CrossOrigin(origins = "*")
public class QuestionarioController {

    @Autowired
    private QuestionarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Questionario> saveQuestionario(@RequestBody Questionario questionario) {
        repository.save(questionario);

        return ResponseEntity.ok(questionario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteQuestionario(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    @GetMapping
    public List<Questionario> getQuestionarios(@RequestParam Map<String, String> queryParams) {
        List<Questionario> questionarios = new ArrayList<>();

        if (queryParams.isEmpty()) {
            questionarios.addAll(repository.findAll());
        } else {
            if (queryParams.get("categoriaId") != null
                && queryParams.get("caracteristicaId") != null) {
                questionarios.addAll(repository.getQuestionarioByCategoriaAndCaracteristica(
                        Long.valueOf(queryParams.get("categoriaId")),
                        Long.valueOf(queryParams.get("caracteristicaId"))));
            } else {
                if (queryParams.get("categoriaId") != null) {
                    questionarios.addAll(repository.getQuestionarioByCategoria(Long.valueOf(queryParams.get("categoriaId"))));
                }

                if (queryParams.get("caracteristicaId") != null) {
                    questionarios.addAll(repository.getQuestionarioByCaracteristica(Long.valueOf(queryParams.get("caracteristicaId"))));
                }
            }
        }

        return questionarios;
    }

}
