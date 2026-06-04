package org.example.cookingbrain.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public class PedidoRequestDTO {

        @NotBlank(message = "O status inicial do pedido é obrigatório.")
        @Size(max = 50, message = "O status não pode passar de 50 caracteres.")
        private String status;

        @NotBlank(message = "A forma de pagamento é obrigatória.")
        @Size(max = 100, message = "A forma de pagamento não pode passar de 100 caracteres.")
        private String formaPag;

        @Size(max = 45, message = "O protocolo do pedido não pode passar de 45 caracteres.")
        private String pedidocol;

        @NotNull(message = "O ID do cliente é obrigatório.")
        private Integer clienteId;

        // O front envia a lista com os IDs dos pratos selecionados pelo usuário
        @NotEmpty(message = "O pedido precisa ter pelo menos um prato selecionado.")
        private List<Integer> pratosIds;

        public PedidoRequestDTO() {}
    }

