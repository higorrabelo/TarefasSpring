package com.app.programa.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.app.programa.Models.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
    List<Tarefa> findByTituloContaining(String nome);
    List<Tarefa> findAll();
    Tarefa findById(int id);
    int countById(int id);

}
