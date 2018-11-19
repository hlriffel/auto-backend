package br.edu.senac.auto.controller;

import br.edu.senac.auto.domain.Caracteristica;
import br.edu.senac.auto.domain.Resposta;
import br.edu.senac.auto.domain.RespostaQuestionario;
import br.edu.senac.auto.domain.UsuarioCategoria;
import br.edu.senac.auto.dto.autoavaliacao.AvaliacaoDto;
import br.edu.senac.auto.dto.autoavaliacao.PerguntaDto;
import br.edu.senac.auto.dto.autoavaliacao.UsuarioCategoriaDto;
import br.edu.senac.auto.repository.QuestionarioRepository;
import br.edu.senac.auto.repository.RespostaRepository;
import br.edu.senac.auto.repository.UsuarioCategoriaRepository;
import br.edu.senac.auto.repository.generic.IGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autoavaliacao")
@CrossOrigin(origins = "*")
public class AutoavaliacaoController {

    @Autowired
    private RespostaRepository respostaRepository;

    private IGenericRepository<RespostaQuestionario> resQuestionarioRepository;

    @Autowired
    public void setResQuestionarioRepository(IGenericRepository<RespostaQuestionario> resQuestionarioRepository) {
        this.resQuestionarioRepository = resQuestionarioRepository;
        this.resQuestionarioRepository.setClazz(RespostaQuestionario.class);
    }

    private IGenericRepository<Caracteristica> caracteristicaRepository;

    @Autowired
    public void setCaracteristicaRepository(IGenericRepository<Caracteristica> caracteristicaRepository) {
        this.caracteristicaRepository = caracteristicaRepository;
        this.caracteristicaRepository.setClazz(Caracteristica.class);
    }

    @Autowired
    private UsuarioCategoriaRepository usuarioCategoriaRepository;

    @Autowired
    private QuestionarioRepository questionarioRepository;

    @PostMapping
    @Transactional
    public void saveResults(@RequestBody List<ResultCreationRequest> results) {
        results.forEach(result -> {
            Optional<UsuarioCategoria> usuarioCategoriaOptional = usuarioCategoriaRepository.findById(result.getUsuarioCategoria());

            if (usuarioCategoriaOptional.isPresent()) {
                UsuarioCategoria usuarioCategoria = usuarioCategoriaOptional.get();
                Resposta resposta = new Resposta();
                resposta.setUsuarioCategoria(usuarioCategoria);

                respostaRepository.deleteByUsuarioCategoria(usuarioCategoria);
                respostaRepository.save(resposta);

                result.getRespostaQuestionario().forEach(respostaQuestionarioRequest -> {
                    Caracteristica caracteristica = caracteristicaRepository.findOne(respostaQuestionarioRequest.getCaracteristica());
                    RespostaQuestionario respostaQuestionario = new RespostaQuestionario();
                    respostaQuestionario.setCaracteristica(caracteristica);
                    respostaQuestionario.setResposta(resposta);
                    respostaQuestionario.setValor(respostaQuestionarioRequest.getValor());

                    resQuestionarioRepository.add(respostaQuestionario);
                });
            }
        });
    }

    @GetMapping("/{usuarioId}")
    public List<AvaliacaoDto> getAvaliacoesByUsuario(@PathVariable Long usuarioId) {
        List<AvaliacaoDto> avaliacoes = new ArrayList<>();

        List<UsuarioCategoriaDto> usuarioCategoriaDtos = usuarioCategoriaRepository.getUsuarioCategoriaDto(usuarioId);
        usuarioCategoriaDtos.forEach(usuarioCategoriaDto -> {
            List<PerguntaDto> perguntaDtos = questionarioRepository.getPerguntaDtos(usuarioCategoriaDto.getCategoriaId());
            avaliacoes.add(new AvaliacaoDto(usuarioCategoriaDto, perguntaDtos));
        });

        return avaliacoes;
    }

    static class ResultCreationRequest {

        private Long usuarioCategoria;
        private List<RespostaQuestionarioRequest> respostaQuestionario;

        public ResultCreationRequest() {
        }

        public Long getUsuarioCategoria() {
            return usuarioCategoria;
        }

        public void setUsuarioCategoria(Long usuarioCategoria) {
            this.usuarioCategoria = usuarioCategoria;
        }

        public List<RespostaQuestionarioRequest> getRespostaQuestionario() {
            return respostaQuestionario;
        }

        public void setRespostaQuestionario(List<RespostaQuestionarioRequest> respostaQuestionario) {
            this.respostaQuestionario = respostaQuestionario;
        }

        static class RespostaQuestionarioRequest {

            private Long caracteristica;
            private Double valor;

            public RespostaQuestionarioRequest() {
            }

            public Long getCaracteristica() {
                return caracteristica;
            }

            public void setCaracteristica(Long caracteristica) {
                this.caracteristica = caracteristica;
            }

            public Double getValor() {
                return valor;
            }

            public void setValor(Double valor) {
                this.valor = valor;
            }
        }
    }
}
