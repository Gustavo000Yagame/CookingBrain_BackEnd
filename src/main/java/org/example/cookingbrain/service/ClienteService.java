package org.example.cookingbrain.service;


import org.example.cookingbrain.dto.ClienteRequestDTO;
import org.example.cookingbrain.dto.ClienteResponseDTO;
import org.example.cookingbrain.model.Cliente;
import org.example.cookingbrain.repository.ClienteRepository;
import org.example.cookingbrain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    private final PedidoRepository pedidoRepository;

    public ClienteService(ClienteRepository repository, PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.repository = repository;
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto){
        Cliente cliente = toEntity(dto);
        Cliente salvo = repository.save(cliente);
        return toResponseDTO(salvo);
    }

    public List<ClienteResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Integer id){
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return toResponseDTO(cliente);
    }

    public ClienteResponseDTO atualizar(Integer idCliente, ClienteRequestDTO dto){
        Cliente cliente = repository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());

        Cliente atualizado = repository.save(cliente);

        return toResponseDTO(atualizado);
    }

    public void deletar(Integer idCliente){
        repository.deleteById(idCliente);
    }

    public List<ClienteResponseDTO> BuscarPorNome (String nome){
        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    private Cliente toEntity(ClienteRequestDTO dto){
        //Pedido pedido = pedidoService.buscarEntidade(dto.idpedido());
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        //cliente.setPedidos();
        return cliente;

        //não precisa de Pedido aqui deixei pra tu ver
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente){
        return  new ClienteResponseDTO(
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getEmail()
        );
    }
}
