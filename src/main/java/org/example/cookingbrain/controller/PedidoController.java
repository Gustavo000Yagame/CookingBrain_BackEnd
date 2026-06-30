package org.example.cookingbrain.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cookingbrain.dto.PedidoRequestDTO;
import org.example.cookingbrain.dto.PedidoResponseDTO;
import org.example.cookingbrain.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Pedidos", description = "Rotas para gerenciamento dos pedidos")
@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @Operation(summary = "Listar todos os pedidos")
    @GetMapping
    public List<PedidoResponseDTO> listar(){
        return service.listar();
    }

    @Operation(summary = "Busca um pedido por id")
    @GetMapping("/{idPedido}")
    public PedidoResponseDTO buscarPorId(@PathVariable Integer idPedido){
        return service.buscarPorId(idPedido);
    }

    @Operation(summary = "Cadastra um novo pedido")
    @PostMapping
    public PedidoResponseDTO salvar(@RequestBody @Valid PedidoRequestDTO dto){
        return service.salvar(dto);
    }

    @Operation(summary = "Atualiza um pedido")
    @PutMapping("/{idPedido}")
    public PedidoResponseDTO atualizar(@RequestBody @Valid PedidoRequestDTO dto,
                                       @PathVariable Integer idPedido){
        return service.atualizar(dto, idPedido);
    }

    @Operation(summary = "Altera apenas o status de um pedido")
    @PatchMapping("/{idPedido}/status")
    public PedidoResponseDTO alterarStatus(@PathVariable Integer idPedido,
                                           @RequestBody Map<String, String> body) {
        String status = body.get("status");
        return service.alterarStatus(idPedido, status);
    }

    @Operation(summary = "Deleta um pedido")
    @DeleteMapping("/{idPedido}")
    public void deletar(@PathVariable Integer idPedido){
        service.deletar(idPedido);
    }
}

