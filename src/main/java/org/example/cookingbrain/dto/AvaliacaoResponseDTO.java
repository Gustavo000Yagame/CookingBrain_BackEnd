package org.example.cookingbrain.dto;

public record AvaliacaoResponseDTO(
        Integer idAvaliacao,
        Integer nota,
        String comentario,
        Integer idPrato //ManyToOne
) {}
