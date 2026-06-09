package org.example.cookingbrain.dto;

import java.util.List;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public record PratoRequestDTO(
        @NotBlank(message = "O nome do prato é obrigatório.")
        @Size(max = 100, message = "O nome do prato não pode passar de 100 caracteres.")
        String nome,

        @Size(max = 500, message = "A descrição não pode passar de 500 caracteres.")
        String descricao,

        @NotNull(message = "O preço é obrigatório.")
        @Positive(message = "O preço deve ser maior que zero.")
        @Digits(integer = 4, fraction = 7, message = "Preço fora do formato válido.")
        Double preco,

        @NotNull(message = "O ID do restaurante é obrigatório.")
        Integer restauranteId,

        @NotEmpty(message = "O prato deve conter pelo menos um ingrediente do estoque.")
        List<Integer> ingredientesIds
) {}
