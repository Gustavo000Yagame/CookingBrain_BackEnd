package org.example.cookingbrain.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cookingbrain.dto.ClienteRequestDTO;
import org.example.cookingbrain.dto.ClienteResponseDTO;
import org.example.cookingbrain.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<ClienteResponseDTO> listar(){
        return clienteService.listar();
    }

    @PostMapping
    public ClienteResponseDTO salvar(@RequestBody ClienteRequestDTO clienteRequestDTO){
        return clienteService.salvar(clienteRequestDTO);
    }

    @GetMapping("/{idCliente}")
    public ClienteResponseDTO buscarPorId(@PathVariable Integer idCliente){
        return clienteService.buscarPorId(idCliente);
    }

    @PutMapping("/{idCliente}")
    public ClienteResponseDTO atualizar (@PathVariable Integer idCliente, @RequestBody @Valid ClienteRequestDTO dto){
        return clienteService.atualizar(idCliente, dto);
    }
    @GetMapping("/buscar/{nome}")
    public List<ClienteResponseDTO> buscarPorNome(@PathVariable String nome){
        return clienteService.BuscarPorNome(nome);
    }


    @DeleteMapping("/{idCliente}")
    public void deletar(@PathVariable Integer idCliente){
        clienteService.deletar(idCliente);
    }

}
