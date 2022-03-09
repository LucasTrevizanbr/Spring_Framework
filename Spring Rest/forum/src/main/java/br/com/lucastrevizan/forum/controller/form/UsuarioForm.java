package br.com.lucastrevizan.forum.controller.form;

import br.com.lucastrevizan.forum.modelo.Usuario;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioForm {

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 100)
    private String nome;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 16)
    private String senha;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario converterParaEntidade() {
        Usuario usuario = new Usuario(nome, email, senha);
        return usuario;
    }
}
