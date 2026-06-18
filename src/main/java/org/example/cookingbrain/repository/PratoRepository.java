package org.example.cookingbrain.repository;

import org.example.cookingbrain.model.Prato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PratoRepository extends JpaRepository<Prato,Integer> {
    List<Prato> findByNomeContainingIgnoreCase(String nome);
}
