package org.example.cookingbrain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cookingbrain.dto.ProdutoEstoqueRequestDTO;
import org.example.cookingbrain.dto.ProdutoEstoqueResponseDTO;
import org.example.cookingbrain.service.ProdutoEstoqueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Produtos em Estoque", description = "Rotas para gerenciamento dos produtos em estoque")
@RestController
@RequestMapping("/produtos-estoque")
@RequiredArgsConstructor
public class ProdutoEstoqueController {

    private final ProdutoEstoqueService produtoEstoqueService;

    @Operation(summary = "Listar todos os produtos do estoque")
    @GetMapping
    public List<ProdutoEstoqueResponseDTO> listar() {
        return produtoEstoqueService.listarTodos();
    }

    @Operation(summary = "Buscar produto do estoque por ID")
    @GetMapping("/{idProdutoEstoque}")
    public ProdutoEstoqueResponseDTO buscarPorId(
            @PathVariable Integer idProdutoEstoque) {

        return produtoEstoqueService.buscarPorId(idProdutoEstoque);
    }

    @Operation(summary = "Cadastrar produto no estoque")
    @PostMapping
    public ProdutoEstoqueResponseDTO salvar(
            @RequestBody @Valid ProdutoEstoqueRequestDTO dto) {

        return produtoEstoqueService.salvar(dto);
    }

    @Operation(summary = "Atualizar produto do estoque")
    @PutMapping("/{idProdutoEstoque}")
    public ProdutoEstoqueResponseDTO atualizar(
            @PathVariable Integer idProdutoEstoque,
            @RequestBody @Valid ProdutoEstoqueRequestDTO dto) {

        return produtoEstoqueService.atualizar(idProdutoEstoque, dto);
    }

    @Operation(summary = "Remover produto do estoque")
    @DeleteMapping("/{idProdutoEstoque}")
    public void deletar(
            @PathVariable Integer idProdutoEstoque) {

        produtoEstoqueService.deletar(idProdutoEstoque);
    }
}