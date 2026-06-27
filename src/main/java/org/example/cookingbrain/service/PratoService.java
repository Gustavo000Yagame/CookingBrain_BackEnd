package org.example.cookingbrain.service;

import lombok.AllArgsConstructor;
import org.example.cookingbrain.dto.PratoRequestDTO;
import org.example.cookingbrain.dto.PratoResponseDTO;
import org.example.cookingbrain.dto.RestauranteResponseDTO;
import org.example.cookingbrain.exception.RecursoNaoEncontradoException;
import org.example.cookingbrain.model.Prato;
import org.example.cookingbrain.model.ProdutoEstoque;
import org.example.cookingbrain.model.Restaurante;
import org.example.cookingbrain.repository.PratoRepository;
import org.example.cookingbrain.repository.ProdutoEstoqueRepository;
import org.example.cookingbrain.repository.RestauranteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class PratoService {

    private final PratoRepository pratoRepository;
    private final RestauranteRepository restauranteRepository;
    private final ProdutoEstoqueRepository produtoEstoqueRepository;

    public PratoService(PratoRepository pratoRepository,
                        RestauranteRepository restauranteRepository,
                        ProdutoEstoqueRepository produtoEstoqueRepository) {
        this.pratoRepository = pratoRepository;
        this.restauranteRepository = restauranteRepository;
        this.produtoEstoqueRepository = produtoEstoqueRepository;
    }

    @Transactional(readOnly = true)
    public List<PratoResponseDTO> listartodos() {
        return pratoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public PratoResponseDTO buscarPorId(Integer idPrato) {
        Prato prato = pratoRepository.findById(idPrato)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Prato não encontrado"));
        return toResponseDTO(prato);
    }

    public PratoResponseDTO salvar(PratoRequestDTO dto) {
        Prato prato = toEntity(dto);
        Prato salvo = pratoRepository.save(prato);
        return toResponseDTO(salvo);
    }

    public PratoResponseDTO atualizar(Integer idPrato, PratoRequestDTO dto) {
        Prato prato = pratoRepository.findById(idPrato)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Prato não encontrado"));

        Restaurante restaurante = restauranteRepository.findById(dto.restauranteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Restaurante não encontrado"));

        List<ProdutoEstoque> ingredientes = produtoEstoqueRepository.findAllById(dto.ingredientesIds());
        if (ingredientes.size() != dto.ingredientesIds().size()) {
            throw new RecursoNaoEncontradoException("Um ou mais ingredientes não foram encontrados no estoque");
        }

        prato.setNome(dto.nome());
        prato.setDescricao(dto.descricao());
        prato.setPreco(dto.preco());
        prato.setRestaurante(restaurante);
        prato.setIngredientes(ingredientes);

        Prato atualizado = pratoRepository.save(prato);

        return toResponseDTO(atualizado);
    }

    public void deletar(Integer idPrato) {
        Prato prato = pratoRepository.findById(idPrato)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Prato não encontrado"));
        pratoRepository.deleteById(idPrato);
    }
    public List<PratoResponseDTO> listarPratoNome(String nome){
        return pratoRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }



    private Prato toEntity(PratoRequestDTO dto) {
        Restaurante restaurante = restauranteRepository.findById(dto.restauranteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Restaurante não encontrado"));

        List<ProdutoEstoque> ingredientes = produtoEstoqueRepository.findAllById(dto.ingredientesIds());
        if (ingredientes.size() != dto.ingredientesIds().size()) {
            throw new RecursoNaoEncontradoException("Um ou mais ingredientes não foram encontrados no estoque");
        }

        Prato prato = new Prato();
        prato.setNome(dto.nome());
        prato.setDescricao(dto.descricao());
        prato.setPreco(dto.preco());
        prato.setRestaurante(restaurante);
        prato.setIngredientes(ingredientes);
        return prato;
    }

    private PratoResponseDTO toResponseDTO(Prato prato) {
        List<PratoResponseDTO.ProdutoEstoqueResumidoDTO> estoqueDTO = prato.getIngredientes() != null ?
                prato.getIngredientes().stream()
                        .map(produto -> new PratoResponseDTO.ProdutoEstoqueResumidoDTO(
                                produto.getIdProdutoEstoque(),
                                produto.getNome()))
                        .toList()
                : List.of();

        List<PratoResponseDTO.PedidoResumidoDTO> pedidosDTO = List.of();


        pedidosDTO = prato.getPedidos() != null ?
                prato.getPedidos().stream()
                        .map(pedido -> new PratoResponseDTO.PedidoResumidoDTO(
                                pedido.getIdPedido(),
                                pedido.getStatus(),
                                pedido.getFormaPag()))
                        .toList()
                : List.of();


        return new PratoResponseDTO(
                prato.getIdPrato(),
                prato.getNome(),
                prato.getDescricao(),
                prato.getPreco(),
                prato.getRestaurante() != null ? prato.getRestaurante().getIdRestaurante() : null,
                pedidosDTO,
                estoqueDTO
        );
    }
}
