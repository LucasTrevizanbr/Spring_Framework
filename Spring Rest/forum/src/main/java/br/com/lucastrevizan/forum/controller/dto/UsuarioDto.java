package br.com.lucastrevizan.forum.controller.dto;

import br.com.lucastrevizan.forum.modelo.Usuario;
import org.springframework.data.domain.Page;

import java.util.List;

public class UsuarioDto {

    private String nome;
    private String email;

    public UsuarioDto(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    public static Page<UsuarioDto> converterPaginado(Page<Usuario> usuarios) {
        return usuarios.map(UsuarioDto::new);
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
