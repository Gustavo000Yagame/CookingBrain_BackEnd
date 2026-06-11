package org.example.cookingbrain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cookingbrain.dto.RestauranteRequestDTO;
import org.example.cookingbrain.dto.RestauranteResponseDTO;
import org.example.cookingbrain.model.Restaurante;
import org.example.cookingbrain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@Tag(name = "Restaurantes", description = "Rotas de gerenciamento de Restaurantes")
@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    @Autowired
    private final RestauranteService service;

    @Operation(summary = "Listar todos os restaurantes")
    @GetMapping
    public List<RestauranteResponseDTO> listar(){
        return service.listar();
    }

    @Operation(summary = "Busca um restaurante por id")
    @GetMapping("/{idRestaurante}")
    public RestauranteResponseDTO buscarPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }

    @Operation(summary = "Cadastra um restaurante")
    @PostMapping
    public RestauranteResponseDTO salvar(@RequestBody @Valid RestauranteRequestDTO dto){
        return service.salvar(dto);
    }

    @Operation(summary = "Atualiza um restaurante")
    @PutMapping("/{idRestaurante}")
    public RestauranteResponseDTO atualizar(@RequestBody @Valid RestauranteRequestDTO dto, @PathVariable Integer id){
        return service.atualizar(dto, id);
    }

    @Operation(summary = "Deleta um restaurante")
    @DeleteMapping
    public void deletar(@PathVariable Integer id){
        service.deletar(id);
    }
}
