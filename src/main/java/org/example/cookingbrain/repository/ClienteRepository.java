package org.example.cookingbrain.repository;

import org.example.cookingbrain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository  extends JpaRepository<Cliente,Integer> {
}
