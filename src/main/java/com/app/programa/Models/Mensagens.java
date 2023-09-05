package com.app.programa.Models;

import org.springframework.stereotype.Component;

@Component
public class Mensagens {
    private String mensagem;

    public String getMensagem(){
        return this.mensagem;
    }
    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }
}
