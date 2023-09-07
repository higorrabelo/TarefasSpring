package com.app.programa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.programa.Models.Mensagens;
import com.app.programa.Models.Tarefa;
import com.app.programa.Repositories.TarefaRepository;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repo;

    @Autowired
    private Mensagens mensagem;

    public ResponseEntity<?> listar() {
        if (repo.count() == 0) {
            mensagem.setMensagem("Sem Registros de Tarefa");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> salvar(Tarefa tarefa) {
        if (tarefa.getTitulo() == "") {
            mensagem.setMensagem("Título não inserido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (tarefa.getDescricao() == "") {
            mensagem.setMensagem("Tarefa não Possui Descrição");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            java.sql.Date data = new java.sql.Date(new java.util.Date().getTime());
            tarefa.setDataCadastro(data);
            return new ResponseEntity<>(repo.save(tarefa), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> editar(Tarefa tarefa) {
        int i = Integer.parseInt(tarefa.getId().toString());
        if (repo.countById(i) == 0) {
            mensagem.setMensagem("Identificação não encontrada");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (tarefa.getTitulo() == "") {
            mensagem.setMensagem("Título não inserido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (tarefa.getDescricao() == "") {
            mensagem.setMensagem("Tarefa não Possui Descrição");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (tarefa.getDataCadastro() == null) {
            java.sql.Date data = new java.sql.Date(new java.util.Date().getTime());
            tarefa.setDataCadastro(data);
            return new ResponseEntity<>(repo.save(tarefa), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(repo.save(tarefa), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> deletar(int id) {
        Tarefa tarefa = repo.findById(id);
        repo.delete(tarefa);
        mensagem.setMensagem("Tarefa Removida com sucesso");
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }
}
