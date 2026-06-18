package org.example.cookingbrain.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.cookingbrain.dto.PratoRequestDTO;
import org.example.cookingbrain.dto.PratoResponseDTO;
import org.example.cookingbrain.dto.RestauranteResponseDTO;
import org.example.cookingbrain.service.PratoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pratos", description = "Rotas de gerenciamento dos Pratos")
@RestController
@RequestMapping("/pratos")
public class PratoController {

    private final PratoService pratoService;


    public PratoController(PratoService pratoService) {
        this.pratoService = pratoService;
    }

    @Operation(summary = "Lista todos os pratos")
    @GetMapping
    public ResponseEntity<List<PratoResponseDTO>> listarTodos() {
        List<PratoResponseDTO> pratos = pratoService.listartodos();
        return ResponseEntity.ok(pratos);
    }

    @Operation(summary = "Busca um prato por id")
    @GetMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> buscarPorId(@PathVariable Integer id) {
        PratoResponseDTO prato = pratoService.buscarPorId(id);
        return ResponseEntity.ok(prato);
    }

    @Operation(summary = "Cadastra um prato")
    @PostMapping
    public ResponseEntity<PratoResponseDTO> salvar(@Valid @RequestBody PratoRequestDTO dto) {
        PratoResponseDTO salvo = pratoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @Operation(summary = "Atualiza um prato")
    @PutMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody PratoRequestDTO dto) {
        PratoResponseDTO atualizado = pratoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Deleta um prato")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        pratoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Busca  Prato por nome")
    @GetMapping("/buscarPrato/nome")
    public List<PratoResponseDTO> buscarPorNome(@RequestParam String nome) {
        return pratoService.listarPratoNome(nome);
    }
}