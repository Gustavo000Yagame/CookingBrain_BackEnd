package org.example.cookingbrain.dto;

import java.util.List;

public record ClienteResponseDTO (
        Integer idCliente,
        String nome,
        String email,
        List<PedidoResponseDTO> pedidos
) {}
