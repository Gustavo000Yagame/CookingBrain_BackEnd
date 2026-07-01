package org.example.cookingbrain.service;

import org.example.cookingbrain.dto.ClienteRequestDTO;
import org.example.cookingbrain.dto.ClienteResponseDTO;
import org.example.cookingbrain.dto.PedidoResponseDTO;
import org.example.cookingbrain.exception.RecursoNaoEncontradoException;
import org.example.cookingbrain.model.Cliente;
import org.example.cookingbrain.model.Pedido;
import org.example.cookingbrain.repository.ClienteRepository;
import org.example.cookingbrain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final PedidoRepository pedidoRepository;
    private final Set<String> emailsDono;

    public ClienteService(
            ClienteRepository repository,
            PedidoRepository pedidoRepository,
            @Value("${app.dono.emails:}") String emailsRaw) {
        this.repository = repository;
        this.pedidoRepository = pedidoRepository;
        this.emailsDono = Arrays.stream(emailsRaw.split(","))
                .map(String::trim)
                .filter(e -> !e.isEmpty())
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }

    private boolean isDono(String email) {
        if (email == null) return false;
        return emailsDono.contains(email.toLowerCase());
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto) {
        if (isDono(dto.email())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Este e-mail pertence ao administrador e não pode ser cadastrado como cliente.");
        }
        Cliente cliente = toEntity(dto);
        Cliente salvo = repository.save(cliente);
        return toResponseDTO(salvo);
    }

    public List<ClienteResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Integer id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
        return toResponseDTO(cliente);
    }

    public ClienteResponseDTO atualizar(Integer idCliente, ClienteRequestDTO dto) {
        Cliente cliente = repository.findById(idCliente)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
        if (isDono(dto.email())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Este e-mail pertence ao administrador.");
        }
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        return toResponseDTO(repository.save(cliente));
    }

    public void deletar(Integer idCliente) {
        repository.findById(idCliente)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
        repository.deleteById(idCliente);
    }

    public List<ClienteResponseDTO> BuscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Cliente obterOuCriarCliente(String email, String nomeGoogle) {
        return repository.findByEmail(email)
                .orElseGet(() -> {
                    Cliente novo = new Cliente();
                    novo.setEmail(email);
                    novo.setNome(nomeGoogle);
                    return repository.save(novo);
                });
    }

    public ClienteResponseDTO buscarMeuPerfil(String email, String nome) {
        if (isDono(email)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Donos do restaurante não possuem perfil de cliente.");
        }
        return toResponseDTO(obterOuCriarCliente(email, nome));
    }

    private Cliente toEntity(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        return cliente;
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente) {
        List<PedidoResponseDTO> pedidos = pedidoRepository
                .findByClienteIdCliente(cliente.getIdCliente())
                .stream()
                .map(this::toPedidoResponseDTO)
                .toList();
        return new ClienteResponseDTO(
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getEmail(),
                pedidos
        );
    }

    private PedidoResponseDTO toPedidoResponseDTO(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getIdPedido(),
                pedido.getStatus(),
                pedido.getFormaPag(),
                pedido.getCliente().getIdCliente(),
                pedido.getCliente().getNome()
        );
    }
}
