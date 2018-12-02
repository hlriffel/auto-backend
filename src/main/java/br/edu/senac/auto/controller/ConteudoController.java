package br.edu.senac.auto.controller;

import br.edu.senac.auto.domain.Conteudo;
import br.edu.senac.auto.domain.Licao;
import br.edu.senac.auto.repository.ConteudoRepository;
import br.edu.senac.auto.repository.generic.IGenericRepository;
import br.edu.senac.auto.s3.S3Service;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rest/conteudo")
@CrossOrigin(origins = "*")
public class ConteudoController {

    @Autowired(required = false)
    private S3Service s3Service;

    private IGenericRepository<Conteudo> repository;

    @Autowired
    private ConteudoRepository specificRepository;

    @Autowired
    public void setRepository(IGenericRepository<Conteudo> repository) {
        this.repository = repository;
        this.repository.setClazz(Conteudo.class);
    }

    @PostMapping
    @Transactional
    public void addContent(@RequestBody ConteudoCreationRequest conteudo) {
        List<Conteudo> removedContents = conteudo.getRemovedContents();
        removedContents.forEach(removedContent -> {
            repository.deleteById(removedContent.getId());
            s3Service.deleteFile(removedContent.getKey());
        });

        List<Conteudo> conteudosAtuais = conteudo.getAddedContents();
        conteudosAtuais.forEach(conteudoAtual -> {
            if (conteudoAtual.getId() == null) {
                repository.add(conteudoAtual);
            } else {
                repository.update(conteudoAtual);
            }
        });
    }

    static class ConteudoCreationRequest {

        private List<Conteudo> addedContents;
        private List<Conteudo> removedContents;

        public ConteudoCreationRequest() {
        }

        public List<Conteudo> getAddedContents() {
            return addedContents;
        }

        public void setAddedContents(List<Conteudo> addedContents) {
            this.addedContents = addedContents;
        }

        public List<Conteudo> getRemovedContents() {
            return removedContents;
        }

        public void setRemovedContents(List<Conteudo> removedContents) {
            this.removedContents = removedContents;
        }
    }

    @GetMapping
    public ResponseEntity<List<Conteudo>> getIntroContents() {
        return ResponseEntity.ok(specificRepository.getIntroContents());
    }

    @GetMapping("/{lectureId}")
    public ResponseEntity<List<Conteudo>> getContentsByLectureId(@PathVariable Long lectureId) {
        return ResponseEntity.ok(specificRepository.getConteudoByLicao(lectureId));
    }

    @PostMapping("/upload")
    public ResponseEntity uploadFiles(@RequestParam(value = "file") MultipartFile[] files) {
        for (MultipartFile file : files) {
            try {
                s3Service.uploadFile(file);
            } catch (IOException ex) {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok().build();
    }
}
