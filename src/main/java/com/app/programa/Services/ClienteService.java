package com.app.programa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.programa.Models.Cliente;
import com.app.programa.Models.Mensagens;
import com.app.programa.Repositories.ClienteRepository;
import com.app.programa.libs.HashMD5;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private Mensagens mensagem;

    public ResponseEntity<?> listarClientes() {
        if (repo.count() == 0) {
            mensagem.setMensagem("Não Há Clientes Cadastrados");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> salvarCliente(Cliente cliente) {
        if (cliente.getNome() == "") {
            mensagem.setMensagem("Preencha o Campo Nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (cliente.getSenha() == "") {
            mensagem.setMensagem("Preencha o Campo Senha");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (cliente.getEmail() == "") {
            mensagem.setMensagem("Preencha o Campo Email");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            String senha = HashMD5.getMD5(cliente.getSenha());
            cliente.setSenha(senha);
            return new ResponseEntity<>(repo.save(cliente), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> buscaPorNome(String nome) {
        if (repo.findByNomeContaining(nome).isEmpty() == true) {
            mensagem.setMensagem("Sem usuários com esse nome");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(repo.findByNomeContaining(nome), HttpStatus.OK);
        }

    }

    public ResponseEntity<?> retornaCliente(int id) {
        if (repo.countById(id) == 0) {
            mensagem.setMensagem("Não há Clientes com esse Id");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } else {
            Cliente cli = repo.findById(id);
            return new ResponseEntity<>(HashMD5.getMD5(cli.getSenha()), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> editarCliente(Cliente cliente) {
        int i = Integer.parseInt(cliente.getId().toString());
        if (repo.countById(i) == 0) {
            mensagem.setMensagem("Não há Clientes com esse Id");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } else if (cliente.getNome() == "") {
            mensagem.setMensagem("Preencha o Campo Nome");
            return new ResponseEntity<>(repo.save(cliente), HttpStatus.BAD_REQUEST);
        } else if (cliente.getSenha() == "") {
            mensagem.setMensagem("Preencha o Campo Senha");
            return new ResponseEntity<>(repo.save(cliente), HttpStatus.BAD_REQUEST);
        } else if (cliente.getEmail() == "") {
            mensagem.setMensagem("Preencha o Campo Email");
            return new ResponseEntity<>(repo.save(cliente), HttpStatus.BAD_REQUEST);
        } else {
            String senha = HashMD5.getMD5(cliente.getSenha());
            cliente.setSenha(senha);
            return new ResponseEntity<>(repo.save(cliente), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> deletar(int id) {
        if (repo.countById(id) == 0) {
            mensagem.setMensagem("Cliente não Existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NO_CONTENT);
        } else {
            mensagem.setMensagem("Usuário Deletado");
            Cliente cli = repo.findById(id);
            repo.delete(cli);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }

}
