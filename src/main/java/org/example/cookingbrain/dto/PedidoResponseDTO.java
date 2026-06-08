package org.example.cookingbrain.dto;

public record PedidoResponseDTO(
        Integer idPedido,
        String status,
        String formpag,
        String pedidocol,
        Integer idCliente //ManyToOne
        //TODO: Configuar a relação de muitos para muitos entre Pedido e Prato
) {}
