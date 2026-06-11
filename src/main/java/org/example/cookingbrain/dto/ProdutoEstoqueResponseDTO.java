package org.example.cookingbrain.dto;

public record ProdutoEstoqueResponseDTO(
        Integer idProduto,
        Integer quantidade,
        String nome

) {}
