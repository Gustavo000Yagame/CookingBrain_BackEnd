package org.example.cookingbrain.dto;

import org.example.cookingbrain.model.Prato;

public record AvaliacaoResponseDTO(
        Integer idAvaliacao,
        Integer nota,
        String comentario,
        Prato prato //ManyToOne
) {}
