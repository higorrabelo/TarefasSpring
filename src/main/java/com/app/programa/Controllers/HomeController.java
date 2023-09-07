package com.app.programa.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.programa.Services.TarefaService;

@RestController
@RequestMapping("home")
@CrossOrigin(origins = "*")
public class HomeController {

    @Autowired
    private TarefaService service;

    @GetMapping
    public String listarTarefas() {
        return "Oi";
    }

}
