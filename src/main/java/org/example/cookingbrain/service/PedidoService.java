package org.example.cookingbrain.service;

import lombok.RequiredArgsConstructor;
import org.example.cookingbrain.dto.PedidoRequestDTO;
import org.example.cookingbrain.dto.PedidoResponseDTO;
import org.example.cookingbrain.exception.RecursoNaoEncontradoException;
import org.example.cookingbrain.model.Cliente;
import org.example.cookingbrain.model.Pedido;
import org.example.cookingbrain.model.Prato;
import org.example.cookingbrain.repository.ClienteRepository;
import org.example.cookingbrain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;

    public List<PedidoResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public PedidoResponseDTO buscarPorId(Integer id){
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado"));
        return toResponse(pedido);
    }

    public PedidoResponseDTO salvar(PedidoRequestDTO dto){
        Pedido pedido = toEntity(dto);
        Pedido salvo = repository.save(pedido);
        return toResponse(salvo);
    }

    public PedidoResponseDTO atualizar(PedidoRequestDTO dto, Integer id){
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado"));

        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));

        pedido.setStatus(dto.status());
        pedido.setFormaPag(dto.formaPag());
        pedido.setCliente(cliente);
        pedido.setPratos(dto.pratos());

        Pedido salvo = repository.save(pedido);
        return toResponse(salvo);
    }

    public PedidoResponseDTO alterarStatus(Integer id, String status) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado"));
        pedido.setStatus(status);
        Pedido salvo = repository.save(pedido);
        return toResponse(salvo);
    }

    public void deletar(Integer id){
        repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado"));
        repository.deleteById(id);
    }

    private PedidoResponseDTO toResponse(Pedido pedido){
        return new PedidoResponseDTO(
                pedido.getIdPedido(),
                pedido.getStatus(),
                pedido.getFormaPag(),
                pedido.getCliente().getIdCliente(),
                pedido.getCliente().getNome()
        );
    }

    private Pedido toEntity(PedidoRequestDTO dto){
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setStatus(dto.status());
        pedido.setFormaPag(dto.formaPag());
        pedido.setCliente(cliente);
        pedido.setPratos(dto.pratos());

        return pedido;
    }
}
