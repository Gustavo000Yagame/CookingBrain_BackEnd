package org.example.cookingbrain.dto;

import jakarta.validation.constraints.*;

public class AvaliacaoRequestDTO {

    @NotNull(message = "A nota é obrigatória.")
    @Min(value = 1, message = "A nota mínima é 1.")
    @Max(value = 5, message = "A nota máxima é 5.")
    private Integer nota;

    @Size(max = 100, message = "O comentário não pode passar de 100 caracteres.")
    private String comentario;

    @NotNull(message = "O ID do prato avaliado é obrigatório.")
    private Integer pratoId;

    public AvaliacaoRequestDTO() {}
}