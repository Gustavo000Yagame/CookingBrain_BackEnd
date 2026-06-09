package org.example.cookingbrain.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record PedidoRequestDTO(
        @NotBlank(message = "O status inicial do pedido é obrigatório.")
        @Size(max = 50, message = "O status não pode passar de 50 caracteres.")
        String status,

        @NotBlank(message = "A forma de pagamento é obrigatória.")
        @Size(max = 100, message = "A forma de pagamento não pode passar de 100 caracteres.")
        String formaPag,

        @Size(max = 45, message = "O protocolo do pedido não pode passar de 45 caracteres.")
        String pedidocol,

        @NotNull(message = "O ID do cliente é obrigatório.")
        Integer clienteId,

        @NotEmpty(message = "O pedido precisa ter pelo menos um prato selecionado.")
        List<Integer> pratosIds
) {}


