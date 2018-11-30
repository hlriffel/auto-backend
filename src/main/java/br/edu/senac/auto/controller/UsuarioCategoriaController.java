package br.edu.senac.auto.controller;

import br.edu.senac.auto.domain.Categoria;
import br.edu.senac.auto.domain.Usuario;
import br.edu.senac.auto.domain.UsuarioCategoria;
import br.edu.senac.auto.repository.UsuarioCategoriaRepository;
import br.edu.senac.auto.repository.generic.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario-categoria")
@CrossOrigin(origins = "*")
public class UsuarioCategoriaController {

    @Autowired
    private UsuarioCategoriaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<List<UsuarioCategoria>> setUserCategories(@RequestBody UsuarioCategoriaCreationRequest creationRequest) {
        List<UsuarioCategoria> categoriesList = new ArrayList<>();

        Usuario usuario = creationRequest.getUsuario();
        List<Categoria> categorias = creationRequest.getCategorias();
        List<Long> categoriaIds = categorias.stream().map(Categoria::getId).collect(Collectors.toList());

        repository.deleteByUsuarioWithoutCategorias(usuario.getId(), categoriaIds);

        for (Categoria categoria : categorias) {
            if (repository.getQtdeByUsuarioAndCategoria(usuario.getId(), categoria.getId()) == 0) {
                UsuarioCategoria usuarioCategoria = new UsuarioCategoria();
                usuarioCategoria.setUsuario(usuario);
                usuarioCategoria.setCategoria(categoria);

                repository.save(usuarioCategoria);

                categoriesList.add(usuarioCategoria);
            }
        }

        return ResponseEntity.ok(categoriesList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UsuarioCategoria>> getUserCategories(@PathVariable Long userId) {
        List<UsuarioCategoria> categories = repository.getUserCategories(userId);

        return ResponseEntity.ok(categories);
    }

    static class UsuarioCategoriaCreationRequest {

        private Usuario usuario;
        private List<Categoria> categorias;

        public UsuarioCategoriaCreationRequest() {

        }

        public UsuarioCategoriaCreationRequest(Usuario usuario, List<Categoria> categorias) {
            this.usuario = usuario;
            this.categorias = categorias;
        }

        public Usuario getUsuario() {
            return usuario;
        }

        public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
        }

        public List<Categoria> getCategorias() {
            return categorias;
        }

        public void setCategorias(List<Categoria> categorias) {
            this.categorias = categorias;
        }
    }
}
