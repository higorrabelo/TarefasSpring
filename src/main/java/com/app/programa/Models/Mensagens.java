package com.app.programa.Models;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Mensagens implements Serializable {
    private static final long serialVersionUID = 1L;
    private String mensagem;

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
