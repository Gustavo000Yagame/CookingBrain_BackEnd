package org.example.cookingbrain.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public class PratoRequestDTO {

    @NotBlank(message = "O nome do prato é obrigatório.")
    @Size(max = 100, message = "O nome do prato não pode passar de 100 caracteres.")
    private String nome;

    @Size(max = 500, message = "A descrição não pode passar de 500 caracteres.")
    private String descricao;

    @NotNull(message = "O preço é obrigatório.")
    @Positive(message = "O preço deve ser maior que zero.")
    @Digits(integer = 4, fraction = 7, message = "Preço fora do formato válido.")
    private Double preco;

    @NotNull(message = "O ID do restaurante é obrigatório.")
    private Integer restauranteId;

    // O front envia apenas a lista de IDs dos ingredientes do estoque que compõem este prato
    @NotEmpty(message = "O prato deve conter pelo menos um ingrediente do estoque.")
    private List<Integer> ingredientesIds;

    public PratoRequestDTO() {}
}