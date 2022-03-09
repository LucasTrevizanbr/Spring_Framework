package br.com.lucastrevizan.forum.config.validacao;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroFormularioDto> handler(MethodArgumentNotValidException exception){

        List<ErroFormularioDto> erros = new ArrayList<>();
        List<FieldError> errosDeCampo = exception.getBindingResult().getFieldErrors();

        errosDeCampo.stream().forEach(erroCampo ->{
            String mensagem = messageSource.getMessage(erroCampo, LocaleContextHolder.getLocale());
            erros.add(new ErroFormularioDto(erroCampo.getField(), mensagem));
        });
        return erros;
    }
}
