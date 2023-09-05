package com.app.programa.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.programa.Models.Cliente;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    List<Cliente> findAll();
    Cliente findById(int id);
    int countById(int id);
    List<Cliente> findByNomeContaining(String nome);
}
