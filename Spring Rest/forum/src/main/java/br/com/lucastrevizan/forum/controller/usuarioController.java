package br.com.lucastrevizan.forum.controller;

import br.com.lucastrevizan.forum.controller.dto.TopicoDto;
import br.com.lucastrevizan.forum.controller.dto.UsuarioDto;
import br.com.lucastrevizan.forum.controller.form.TopicoForm;
import br.com.lucastrevizan.forum.controller.form.UsuarioForm;
import br.com.lucastrevizan.forum.modelo.Topico;
import br.com.lucastrevizan.forum.modelo.Usuario;
import br.com.lucastrevizan.forum.repository.UsuarioRepository;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/usuarios")
public class usuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/pagina")
    @Cacheable(value = "buscaPaginadaUsuario")
    public Page<UsuarioDto> buscaFlexivelPaginada(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC,page = 0, size = 5) Pageable paginacao){

        Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
        return UsuarioDto.converterPaginado(usuarios);
    }


    @PostMapping
    @Transactional
    @CacheEvict(value = "buscaPaginadaUsuario", allEntries = true)
    public ResponseEntity<UsuarioDto> cadastrarTopico(@RequestBody @Valid UsuarioForm userForm,
                                                     UriComponentsBuilder uriBuilder){

        Usuario usuario = userForm.converterParaEntidade();
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }
}
