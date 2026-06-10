package org.example.cookingbrain.service;

import org.example.cookingbrain.dto.ProdutoEstoqueRequestDTO;
import org.example.cookingbrain.dto.ProdutoEstoqueResponseDTO;
import org.example.cookingbrain.exception.RecursoNaoEncontradoException;
import org.example.cookingbrain.model.ProdutoEstoque;
import org.example.cookingbrain.repository.ProdutoEstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoEstoque {

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
        ProdutoEstoque produtoEstoque = produtoEstoqueRepository.findById(idProdutoEstoque)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Produto no estoque não encontrado"));

        return toResponseDTO(produtoEstoque);
    }

    public ProdutoEstoqueResponseDTO salvar(ProdutoEstoqueRequestDTO dto) {
        ProdutoEstoque produtoEstoque = toEntity(dto);

        ProdutoEstoque salvo = produtoEstoqueRepository.save(produtoEstoque);

        return toResponseDTO(salvo);
    }

    public ProdutoEstoqueResponseDTO atualizar(Integer idProdutoEstoque,
                                               ProdutoEstoqueRequestDTO dto) {

        ProdutoEstoque produtoEstoque = produtoEstoqueRepository.findById(idProdutoEstoque)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Produto no estoque não encontrado"));

        produtoEstoque.setNome(dto.nome());
        produtoEstoque.setQuantidade(dto.quantidade());

        ProdutoEstoque atualizado = produtoEstoqueRepository.save(produtoEstoque);

        return toResponseDTO(atualizado);
    }

    public void deletar(Integer idProdutoEstoque) {
        ProdutoEstoque produtoEstoque = produtoEstoqueRepository.findById(idProdutoEstoque)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Produto no estoque não encontrado"));

        produtoEstoqueRepository.delete(produtoEstoque);
    }

    private ProdutoEstoque toEntity(ProdutoEstoqueRequestDTO dto) {
        ProdutoEstoque produtoEstoque = new ProdutoEstoque();

        produtoEstoque.setNome(dto.nome());
        produtoEstoque.setQuantidade(dto.quantidade());

        return produtoEstoque;
    }

    private ProdutoEstoqueResponseDTO toResponseDTO(ProdutoEstoque produtoEstoque) {
        return new ProdutoEstoqueResponseDTO(
                produtoEstoque.getIdProdutoEstoque(),
                produtoEstoque.getQuantidade(),
                produtoEstoque.getNome()
        );
    }
}