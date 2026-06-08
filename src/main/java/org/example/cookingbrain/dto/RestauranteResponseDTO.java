package org.example.cookingbrain.dto;

public record RestauranteResponseDTO(
        Integer idRestaurante,
        String nome,
        byte[] foto,
        String local,
        String descricao,
        String horarioAtendimento,
        String numero,
        String cnpj,
) {}
