package org.example.cookingbrain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AvaliacaoRequestDTO(
        @NotNull(message = "A nota é obrigatória.")
        @Min(value = 1, message = "A nota mínima é 1.")
        @Max(value = 5, message = "A nota máxima é 5.")
        Integer nota,

        @Size(max = 100, message = "O comentário não pode passar de 100 caracteres.")
        String comentario,

        @NotNull(message = "O ID do prato avaliado é obrigatório.")
        Integer idPrato
)
{}
