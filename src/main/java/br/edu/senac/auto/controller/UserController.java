package br.edu.senac.auto.controller;

import br.edu.senac.auto.domain.User;
import br.edu.senac.auto.repository.generic.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private IGenericRepository<User> repository;

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
}
