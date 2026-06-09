package org.example.cookingbrain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RestauranteRequestDTO(

        @NotBlank(message = "O nome do restaurante é obrigatório.")
        @Size(max = 100, message = "O nome não pode passar de 100 caracteres.")

        String nome,

        byte[] foto, // Opcional no cadastro inicial

        @NotBlank(message = "A localização é obrigatória.")
        @Size(max = 100, message = "O endereço não pode passar de 100 caracteres.")
        String local,

        @Size(max = 400, message = "A descrição não pode passar de 400 caracteres.")
        String descricao,

        @NotBlank(message = "O horário de atendimento é obrigatório.")
        @Size(max = 100, message = "O horário não pode passar de 100 caracteres.")
        String horarioAtendimento,

        @NotBlank(message = "O número de telefone é obrigatório.")
        @Size(max = 11, message = "O telefone deve ter no máximo 11 dígitos.")
        String numero,

        @NotBlank(message = "O CNPJ é obrigatório.")
        @Size(min = 14, max = 14, message = "O CNPJ deve conter exatamente 14 caracteres.")
        String cnpj
){}