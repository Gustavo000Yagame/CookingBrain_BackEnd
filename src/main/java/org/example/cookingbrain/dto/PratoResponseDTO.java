package org.example.cookingbrain.dto;

import java.util.List;

public record PratoResponseDTO (
        Integer idPrato,
        String nome,
        String descricao,
        Double preco,
        Integer idRestaurante,
        List<PedidoResumidoDTO> pedidos,
        List<ProdutoEstoqueResumidoDTO> produtosEstoque
) {

    public record PedidoResumidoDTO(
            Integer idPedido,
            String status,
            String formpag
    ) {}

    public record ProdutoEstoqueResumidoDTO(
            Integer idProdutoEstoque,
            String nome
    ) {}
}