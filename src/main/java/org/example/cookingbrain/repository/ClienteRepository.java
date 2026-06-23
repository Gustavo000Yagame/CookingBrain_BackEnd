package org.example.cookingbrain.repository;

import org.example.cookingbrain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository  extends JpaRepository<Cliente,Integer> {
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    Optional<Cliente> findByEmail(String email);
}
