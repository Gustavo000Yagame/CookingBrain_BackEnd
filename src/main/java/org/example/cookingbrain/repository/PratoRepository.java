package org.example.cookingbrain.repository;

import org.example.cookingbrain.model.Prato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PratoRepository extends JpaRepository<Prato,Integer> {
}
