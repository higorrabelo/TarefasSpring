package com.app.programa.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.programa.Models.Tarefa;
import com.app.programa.Services.TarefaService;

@RestController
@RequestMapping("tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @GetMapping
    public ResponseEntity<?> listarTarefas() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<?> salvarTarefa(@RequestBody Tarefa tarefa) {
        return service.salvar(tarefa);
    }

    @PutMapping
    public ResponseEntity<?> editarTarefa(@RequestBody Tarefa tarefa) {
        return service.editar(tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable int id) {
        return service.deletar(id);
    }

}
