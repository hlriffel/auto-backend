package br.edu.senac.auto.controller;

import br.edu.senac.auto.domain.User;
import br.edu.senac.auto.repository.generic.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    IGenericRepository<User> repository;

    @Autowired
    public void setRepository(IGenericRepository<User> repository) {
        this.repository = repository;
        this.repository.setClazz(User.class);
    }

    @PostMapping
    public void addUser(User user) {
        repository.add(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
