package br.com.lucastrevizan.forum.controller;

import br.com.lucastrevizan.forum.config.secutiry.TokenService;
import br.com.lucastrevizan.forum.controller.dto.TokenDto;
import br.com.lucastrevizan.forum.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
        try{
            UsernamePasswordAuthenticationToken dadosLogin = form.converter();
            Authentication auth = authManager.authenticate(dadosLogin);

            String token = tokenService.gerarToken(auth);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));

        }catch (AuthenticationException ex){
            return ResponseEntity.badRequest().build();
        }
    }
}
