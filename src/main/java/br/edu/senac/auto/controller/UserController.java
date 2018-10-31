package br.edu.senac.auto.controller;

import br.edu.senac.auto.domain.User;
import br.edu.senac.auto.repository.UserRepository;
import br.edu.senac.auto.repository.generic.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private IGenericRepository<User> repository;

    @Autowired
    private UserRepository specificRepository;

    @Autowired
    public void setRepository(IGenericRepository<User> repository) {
        this.repository = repository;
        this.repository.setClazz(User.class);
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        repository.add(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = specificRepository.findUserByEmail(email);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.notFound().build();
    }
}
