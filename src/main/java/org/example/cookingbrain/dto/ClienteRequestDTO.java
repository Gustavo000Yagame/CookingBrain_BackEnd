package org.example.cookingbrain.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(
        @NotBlank(message = "O nome do cliente é obrigatório.")
        @Size(max = 45, message = "O nome não pode passar de 45 caracteres.")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Insira um endereço de e-mail válido.")
        @Size(max = 45, message = "O e-mail não pode passar de 45 caracteres.")
        String email
) {}

