package org.example.cookingbrain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ProdutoEstoqueRequestDTO(

        @NotBlank(message = "O nome do produto de estoque é obrigatório.")
        @Size(max = 100, message = "O nome não pode passar de 100 caracteres.")
         String nome,

        @NotNull(message = "A quantidade é obrigatória.")
        @PositiveOrZero(message = "A quantidade em estoque não pode ser negativa.")
        Integer quantidade
) {}