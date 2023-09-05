package com.app.programa.Controllers;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.programa.Models.Cliente;
import com.app.programa.Repositories.ClienteRepository;
import com.app.programa.Services.ClienteService;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
    
    @Autowired
    private ClienteService servico;

    @Autowired
    private ClienteRepository repo;

    @GetMapping
    public ResponseEntity<?> listar(){
        return servico.listarClientes();
    }
    @PostMapping
    public Cliente salvar(@RequestBody Cliente cli){
        return repo.save(cli);
    }
    @PutMapping
    public ResponseEntity<?> editar(@RequestBody Cliente cli){
        return servico.editarCliente(cli);
    }
    @GetMapping("/contar/{id}")
    public ResponseEntity<?> contar(@PathVariable Integer id){
        return servico.retornaCliente(id);
    }
    @GetMapping("/buscar/{nome}")
    public ResponseEntity<?> buscaPorNome(@PathVariable String nome){
    return new ResponseEntity<>(repo.findByNomeContaining(nome),HttpStatus.OK);
    }
}
