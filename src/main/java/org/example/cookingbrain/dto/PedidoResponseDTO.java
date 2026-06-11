package org.example.cookingbrain.dto;

public record PedidoResponseDTO(
        Integer idPedido,
        String status,
        String formpag,
        Integer idCliente,
        String nome
) {}
