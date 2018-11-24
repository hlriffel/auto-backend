package br.edu.senac.auto.controller;

import br.edu.senac.auto.domain.Resposta;
import br.edu.senac.auto.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resposta")
@CrossOrigin(origins = "*")
public class RespostaController {

    @Autowired
    private RespostaRepository repository;

    @GetMapping("/{userId}")
    public List<Resposta> getRespostasByUsuario(@PathVariable Long userId) {
        return repository.getRespostasByUsuario(userId);
    }
}
