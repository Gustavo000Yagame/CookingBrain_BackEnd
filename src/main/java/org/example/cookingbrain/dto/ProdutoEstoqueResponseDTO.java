package org.example.cookingbrain.dto;

public record ProdutoEstoqueResponseDTO(
        Integer idProduto,
        Integer quantidade,
        String nome
        // TODO: Configuar a relação many to many entre ProdutoEstoque e Prato
) {}
