package br.com.lucastrevizan.forum.controller;

import br.com.lucastrevizan.forum.controller.dto.DetalhesTopicoDto;
import br.com.lucastrevizan.forum.controller.dto.TopicoDto;
import br.com.lucastrevizan.forum.controller.form.TopicoAtualizacaoForm;
import br.com.lucastrevizan.forum.controller.form.TopicoForm;
import br.com.lucastrevizan.forum.modelo.Topico;
import br.com.lucastrevizan.forum.repository.CursoRepository;
import br.com.lucastrevizan.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> buscarCollectionResourceTopicos(){
        List<Topico> topicos = topicoRepository.findAll();
        return TopicoDto.converter(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTopicoDto> buscarSingletonTopico(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.buscarTopicoDetalhe(id);
        if(topico.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(new DetalhesTopicoDto((topico.get())));
    }

    @GetMapping("/nomeCurso={nomeCurso}")
    public List<TopicoDto> buscarTopicoPorCurso(@PathVariable String nomeCurso){
        List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
        return TopicoDto.converter(topicos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto>  cadastrarTopico(@RequestBody @Valid TopicoForm topicoForm,
                                                      UriComponentsBuilder uriBuilder){

        Topico topico = topicoForm.converterParaEntidade(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto>  atualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoAtualizacaoForm form,
                                                      UriComponentsBuilder uriBuilder){

        Optional<Topico> topicoAtualizado = form.atualizar(id, topicoRepository);
        if(topicoAtualizado.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new TopicoDto(topicoAtualizado.get()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarSingletonTopico(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
