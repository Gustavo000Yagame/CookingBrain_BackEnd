package org.example.cookingbrain.repository;

import org.example.cookingbrain.model.ProdutoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque,Integer> {
}
