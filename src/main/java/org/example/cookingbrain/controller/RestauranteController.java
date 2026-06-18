package org.example.cookingbrain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cookingbrain.dto.RestauranteRequestDTO;
import org.example.cookingbrain.dto.RestauranteResponseDTO;
import org.example.cookingbrain.service.RestauranteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Restaurantes", description = "Rotas de gerenciamento de Restaurantes")
@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    private final RestauranteService service;

    @Operation(summary = "Listar todos os restaurantes")
    @GetMapping
    public List<RestauranteResponseDTO> listar() {
        return service.listar();
    }

    @Operation(summary = "Busca um restaurante por id")
    @GetMapping("/{idRestaurante}")
    public RestauranteResponseDTO buscarPorId(@PathVariable Integer idRestaurante) {
        return service.buscarPorId(idRestaurante);
    }

    @Operation(summary = "Cadastra um restaurante")
    @PostMapping
    public RestauranteResponseDTO salvar(@RequestBody @Valid RestauranteRequestDTO dto) {
        return service.salvar(dto);
    }

    @Operation(summary = "Atualiza um restaurante")
    @PutMapping("/{idRestaurante}")
    public RestauranteResponseDTO atualizar(@RequestBody @Valid RestauranteRequestDTO dto,
                                            @PathVariable Integer idRestaurante) {
        return service.atualizar(dto, idRestaurante);
    }

    @Operation(summary = "Deleta um restaurante")
    @DeleteMapping("/{idRestaurante}")
    public void deletar(@PathVariable Integer idRestaurante) {
        service.deletar(idRestaurante);
    }
    @Operation(summary = "Busca  restaurante por nome")
    @GetMapping("/buscar/nome")
    public List<RestauranteResponseDTO> buscarPorNome(@RequestParam String nome) {
        return service.listarRestaurantesNome(nome);
    }
}
