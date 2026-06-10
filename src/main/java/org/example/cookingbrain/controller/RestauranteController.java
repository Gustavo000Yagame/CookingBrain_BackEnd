package org.example.cookingbrain.controller;

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

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    @Autowired
    private final RestauranteService service;

    @GetMapping
    public List<RestauranteResponseDTO> listar(){
        return service.listar();
    }

    @GetMapping("/{idRestaurante}")
    public RestauranteResponseDTO buscarPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }

    @PostMapping
    public RestauranteResponseDTO salvar(@RequestBody @Valid RestauranteRequestDTO dto){
        return service.salvar(dto);
    }

    @PutMapping("/{idRestaurante}")
    public RestauranteResponseDTO atualizar(@RequestBody @Valid RestauranteRequestDTO dto, @PathVariable Integer id){
        return service.atualizar(dto, id);
    }

    @DeleteMapping
    public void deletar(@PathVariable Integer id){
        service.deletar(id);
    }
}
