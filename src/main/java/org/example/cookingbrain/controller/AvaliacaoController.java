package org.example.cookingbrain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cookingbrain.dto.AvaliacaoRequestDTO;
import org.example.cookingbrain.dto.AvaliacaoResponseDTO;
import org.example.cookingbrain.service.AvaliacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Avaliações", description = "Rotas para gerenciamento das Avaliações")
@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @Operation(summary = "Listar todas as avaliações")
    @GetMapping
    public List<AvaliacaoResponseDTO> listar(){ return avaliacaoService.listartodos(); }

    @Operation(summary = "Busca uma avaliação por id")
    @GetMapping("/{idAvaliacao}")
    public AvaliacaoResponseDTO buscarPorId(@PathVariable Integer idAvaliacao) {
        return avaliacaoService.buscarPorId(idAvaliacao);
    }

    @Operation(summary = "Cadastra uma nova avaliação")
    @PostMapping
    public AvaliacaoResponseDTO cadastrar(@RequestBody @Valid AvaliacaoRequestDTO dto) {
        return avaliacaoService.salvar(dto);
    }

    @Operation(summary = "Atualiza uma avaliação")
    @PutMapping("/{idAvaliacao}")
    public AvaliacaoResponseDTO atualizar(@PathVariable Integer idAvaliacao, @RequestBody @Valid AvaliacaoRequestDTO dto) {
        return avaliacaoService.atualizar(idAvaliacao, dto);
    }

    @Operation(summary = "Deleta uma avaliação")
    @DeleteMapping("/{idAvaliacao}")
    public void deletar(@PathVariable Integer idAvaliacao) {
        avaliacaoService.deletar(idAvaliacao);
    }
}
