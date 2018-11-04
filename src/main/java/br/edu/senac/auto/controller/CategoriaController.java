package br.edu.senac.auto.controller;

import br.edu.senac.auto.domain.Categoria;
import br.edu.senac.auto.repository.generic.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private IGenericRepository<Categoria> repository;

    @Autowired
    public void setRepository(IGenericRepository<Categoria> repository) {
        this.repository = repository;
        this.repository.setClazz(Categoria.class);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Categoria> saveCategory(@RequestBody Categoria categoria) {
        if (categoria.getId() == null) {
            repository.add(categoria);
        } else {
            repository.update(categoria);
        }

        return ResponseEntity.ok(categoria);
    }

    @GetMapping
    public List<Categoria> getAllCategories() {
        return repository.findAll();
    }

}
