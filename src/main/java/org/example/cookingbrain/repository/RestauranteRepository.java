package org.example.cookingbrain.repository;


import org.example.cookingbrain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {
    List<Restaurante> findByNomeContainingIgnoreCase(String nome);
}
