package org.example.cookingbrain.controller;


import jakarta.validation.Valid;
import org.example.cookingbrain.dto.PratoRequestDTO;
import org.example.cookingbrain.dto.PratoResponseDTO;
import org.example.cookingbrain.service.PratoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pratos")
public class PratoController {

    private final PratoService pratoService;


    public PratoController(PratoService pratoService) {
        this.pratoService = pratoService;
    }


    @GetMapping
    public ResponseEntity<List<PratoResponseDTO>> listarTodos() {
        List<PratoResponseDTO> pratos = pratoService.listartodos();
        return ResponseEntity.ok(pratos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> buscarPorId(@PathVariable Integer id) {
        PratoResponseDTO prato = pratoService.buscarPorId(id);
        return ResponseEntity.ok(prato);
    }


    @PostMapping
    public ResponseEntity<PratoResponseDTO> salvar(@Valid @RequestBody PratoRequestDTO dto) {
        PratoResponseDTO salvo = pratoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody PratoRequestDTO dto) {
        PratoResponseDTO atualizado = pratoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        pratoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}