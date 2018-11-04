package br.edu.senac.auto.controller;

import br.edu.senac.auto.domain.Usuario;
import br.edu.senac.auto.repository.UsuarioRepository;
import br.edu.senac.auto.repository.generic.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private IGenericRepository<Usuario> repository;

    @Autowired
    private UsuarioRepository specificRepository;

    @Autowired
    public void setRepository(IGenericRepository<Usuario> repository) {
        this.repository = repository;
        this.repository.setClazz(Usuario.class);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> saveUser(@RequestBody Usuario usuario) {
        if (usuario.getId() == null) {
            repository.add(usuario);
        } else {
            repository.update(usuario);
        }

        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public List<Usuario> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Usuario> getUserByEmail(@PathVariable String email) {
        Optional<Usuario> user = specificRepository.findUserByEmail(email);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.notFound().build();
    }
}
