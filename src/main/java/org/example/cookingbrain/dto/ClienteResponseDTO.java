package org.example.cookingbrain.dto;

public record ClienteResponseDTO (
        Integer idCliente,
        String nome,
        String email,
        Integer idPedido,
        String status,
        String formaPag

) {}
