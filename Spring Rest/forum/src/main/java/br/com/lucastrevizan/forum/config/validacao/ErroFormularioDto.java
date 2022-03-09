package br.com.lucastrevizan.forum.config.validacao;

public class ErroFormularioDto {

    private String campo;
    private String msgErro;

    public ErroFormularioDto(String campo, String msgErro) {
        this.campo = campo;
        this.msgErro = msgErro;
    }

    public String getCampo() {
        return campo;
    }

    public String getMsgErro() {
        return msgErro;
    }
}
