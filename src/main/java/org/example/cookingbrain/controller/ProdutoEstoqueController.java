package org.example.cookingbrain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cookingbrain.dto.ProdutoEstoqueRequestDTO;
import org.example.cookingbrain.dto.ProdutoEstoqueResponseDTO;
import org.example.cookingbrain.service.ProdutoEstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Produtos em Estoque", description = "Rotas para gerenciamento do estoque")
@RestController
@RequestMapping("/estoque")
@RequiredArgsConstructor
public class ProdutoEstoqueController {

    private final ProdutoEstoqueService service;

    @Operation(summary = "Lista todos os produtos em estoque")
    @GetMapping
    public List<ProdutoEstoqueResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @Operation(summary = "Busca um produto em estoque por id")
    @GetMapping("/{idProduto}")
    public ProdutoEstoqueResponseDTO buscarPorId(@PathVariable Integer idProduto) {
        return service.buscarPorId(idProduto);
    }

    @Operation(summary = "Cadastra um produto em estoque")
    @PostMapping
    public ResponseEntity<ProdutoEstoqueResponseDTO> salvar(@RequestBody @Valid ProdutoEstoqueRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @Operation(summary = "Atualiza um produto em estoque")
    @PutMapping("/{idProduto}")
    public ProdutoEstoqueResponseDTO atualizar(@PathVariable Integer idProduto,
                                               @RequestBody @Valid ProdutoEstoqueRequestDTO dto) {
        return service.atualizar(idProduto, dto);
    }

    @Operation(summary = "Deleta um produto em estoque")
    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> deletar(@PathVariable Integer idProduto) {
        service.deletar(idProduto);
        return ResponseEntity.noContent().build();
    }
}
