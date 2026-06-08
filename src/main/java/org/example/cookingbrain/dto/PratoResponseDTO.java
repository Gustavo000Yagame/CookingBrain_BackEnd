package org.example.cookingbrain.dto;

public record PratoResponseDTO (
        Integer idPrato,
        String nome,
        String descricao,
        Double preco,
        Integer idRestaurante //ManyToOne
        // TODO: Configuar a relação de many to many entre Prato com Pedido e ProdutoEstoque
) {}
