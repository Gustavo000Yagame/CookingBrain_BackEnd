package org.example.cookingbrain.dto;


public record AvaliacaoResponseDTO(
        Integer idAvaliacao,
        Integer nota,
        String comentario,
        PratoResumido prato
) {

    public record PratoResumido(
            Integer idPrato,
            String nome,
            String descricao,
            Double preco,
            Integer idRestaurante
    ) {}
}
