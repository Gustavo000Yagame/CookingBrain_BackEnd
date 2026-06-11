package org.example.cookingbrain.service;

import org.example.cookingbrain.dto.ProdutoEstoqueRequestDTO;
import org.example.cookingbrain.dto.ProdutoEstoqueResponseDTO;
import org.example.cookingbrain.exception.RecursoNaoEncontradoException;
import org.example.cookingbrain.repository.ProdutoEstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoEstoqueService {

    private final ProdutoEstoqueRepository produtoEstoqueRepository;

    public ProdutoEstoqueService(ProdutoEstoqueRepository produtoEstoqueRepository) {
        this.produtoEstoqueRepository = produtoEstoqueRepository;
    }

    public List<ProdutoEstoqueResponseDTO> listarTodos() {
        return produtoEstoqueRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ProdutoEstoqueResponseDTO buscarPorId(Integer idProdutoEstoque) {
        ProdutoEstoqueService produtoEstoque = produtoEstoqueRepository.findById(idProdutoEstoque)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Produto no estoque não encontrado"));

        return toResponseDTO(produtoEstoque);
    }

    public ProdutoEstoqueResponseDTO salvar(ProdutoEstoqueRequestDTO dto) {
        ProdutoEstoqueService produtoEstoque = toEntity(dto);

        ProdutoEstoqueService salvo = produtoEstoqueRepository.save(produtoEstoque);

        return toResponseDTO(salvo);
    }

    public ProdutoEstoqueResponseDTO atualizar(Integer idProdutoEstoque,
                                               ProdutoEstoqueRequestDTO dto) {

        ProdutoEstoqueService produtoEstoque = produtoEstoqueRepository.findById(idProdutoEstoque)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Produto no estoque não encontrado"));

        produtoEstoque.setNome(dto.nome());
        produtoEstoque.setQuantidade(dto.quantidade());

        ProdutoEstoqueService atualizado = produtoEstoqueRepository.save(produtoEstoque);

        return toResponseDTO(atualizado);
    }

    public void deletar(Integer idProdutoEstoque) {
        ProdutoEstoqueService produtoEstoque = produtoEstoqueRepository.findById(idProdutoEstoque)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Produto no estoque não encontrado"));

        produtoEstoqueRepository.delete(produtoEstoque);
    }

    private ProdutoEstoqueService toEntity(ProdutoEstoqueRequestDTO dto) {
        ProdutoEstoqueService produtoEstoque = new ProdutoEstoqueService();

        produtoEstoque.setNome(dto.nome());
        produtoEstoque.setQuantidade(dto.quantidade());

        return produtoEstoque;
    }

    private ProdutoEstoqueResponseDTO toResponseDTO(ProdutoEstoqueService produtoEstoque) {
        return new ProdutoEstoqueResponseDTO(
                produtoEstoque.getIdProdutoEstoque(),
                produtoEstoque.getQuantidade(),
                produtoEstoque.getNome()
        );
    }
}