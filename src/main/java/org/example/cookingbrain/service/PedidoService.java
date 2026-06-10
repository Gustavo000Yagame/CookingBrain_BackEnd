package org.example.cookingbrain.service;

import lombok.RequiredArgsConstructor;
import org.example.cookingbrain.dto.PedidoRequestDTO;
import org.example.cookingbrain.dto.PedidoResponseDTO;
import org.example.cookingbrain.model.Pedido;
import org.example.cookingbrain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;

    public List<PedidoResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();

    }

    public PedidoResponseDTO buscarPorId(Integer id){
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        return toResponse(pedido);
    }

    public PedidoResponseDTO salvar(PedidoRequestDTO dto){
        Pedido pedido = toEntity(dto);

        Pedido salvo = repository.save(pedido);
        return toResponse(salvo);
    }

    public PedidoResponseDTO atualizar(PedidoRequestDTO dto, Integer id){
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(dto.status());
        pedido.setFormaPag(dto.formaPag());
        pedido.setPedidocol(dto.pedidocol());
        pedido.setIdPedido(dto.clienteId());

        Pedido salvo = repository.save(pedido);
        return toResponse(salvo);
    }

    public void deletar(Integer id){
        repository.deleteById(id);
    }

    private PedidoResponseDTO toResponse(Pedido pedido){

        return new PedidoResponseDTO(
                pedido.getIdPedido(),
                pedido.getStatus(),
                pedido.getFormaPag(),
                pedido.getPedidocol(),
                pedido.getCliente().getIdCliente()
        );
    }

    private Pedido toEntity(PedidoRequestDTO dto){
        Pedido pedido = new Pedido();

        pedido.setStatus(dto.status());
        pedido.setFormaPag(dto.formaPag());
        pedido.setPedidocol(dto.pedidocol());

        return pedido;
    }
}
