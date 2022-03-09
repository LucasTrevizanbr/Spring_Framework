package br.com.lucastrevizan.forum.controller.form;

import br.com.lucastrevizan.forum.modelo.Topico;
import br.com.lucastrevizan.forum.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class TopicoAtualizacaoForm {


    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String titulo;

    @NotNull @NotEmpty @Length(min = 10)
    private String mensagem;

    public Optional<Topico> atualizar(Long id, TopicoRepository topicoRepository) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            topico.get().setMensagem(this.getMensagem());
            topico.get().setTitulo(this.getTitulo());
        }
        return topico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
