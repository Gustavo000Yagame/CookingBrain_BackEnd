package org.example.cookingbrain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cookingbrain.dto.ClienteRequestDTO;
import org.example.cookingbrain.dto.ClienteResponseDTO;
import org.example.cookingbrain.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Rotas para gerenciamento dos Clientes")
@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Listar todos os clientes")
    @GetMapping
    public List<ClienteResponseDTO> listar(){
        return clienteService.listar();
    }

    @Operation(summary = "Cadastrar um novo cliente")
    @PostMapping
    public ClienteResponseDTO salvar(@RequestBody ClienteRequestDTO clienteRequestDTO){
        return clienteService.salvar(clienteRequestDTO);
    }

    @Operation(summary = "Busca um cliente por id")
    @GetMapping("/{idCliente}")
    public ClienteResponseDTO buscarPorId(@PathVariable Integer idCliente){
        return clienteService.buscarPorId(idCliente);
    }

    @Operation(summary = "Atualiza um cliente")
    @PutMapping("/{idCliente}")
    public ClienteResponseDTO atualizar (@PathVariable Integer idCliente, @RequestBody @Valid ClienteRequestDTO dto){
        return clienteService.atualizar(idCliente, dto);
    }

    @Operation(summary = "Faz a busca do cliente passando o nome")
    @GetMapping("/buscar/{nome}")
    public List<ClienteResponseDTO> buscarPorNome(@PathVariable String nome){
        return clienteService.BuscarPorNome(nome);
    }

    @Operation(summary = "Deleta um cliente")
    @DeleteMapping("/{idCliente}")
    public void deletar(@PathVariable Integer idCliente){
        clienteService.deletar(idCliente);
    }

}
