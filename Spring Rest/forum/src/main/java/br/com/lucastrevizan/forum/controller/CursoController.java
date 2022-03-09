package br.com.lucastrevizan.forum.controller;

import br.com.lucastrevizan.forum.controller.dto.CursoDto;
import br.com.lucastrevizan.forum.modelo.Curso;
import br.com.lucastrevizan.forum.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/pagina")
    public Page<CursoDto> buscarCursosPaginados(@RequestParam int pagina, @RequestParam int qtdPorPagina,
                                                @RequestParam String ordenacao){

        Pageable paginacao = PageRequest.of(pagina, qtdPorPagina, Sort.Direction.ASC, ordenacao);
        Page<Curso> cursos = cursoRepository.findAll(paginacao);

        return CursoDto.converterPaginado(cursos);
    }
}
