package br.com.lucastrevizan.forum.controller.dto;

import br.com.lucastrevizan.forum.modelo.Curso;
import org.springframework.data.domain.Page;

public class CursoDto {

    private Long id;
    private String nome;
    private String categoria;

    public CursoDto(Curso curso) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.categoria = curso.getCategoria();
    }

    public static Page<CursoDto> converterPaginado(Page<Curso> cursos) {
        return cursos.map(CursoDto::new);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }
}
