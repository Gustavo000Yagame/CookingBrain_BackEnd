package org.example.cookingbrain.service;

import org.example.cookingbrain.dto.AvaliacaoRequestDTO;
import org.example.cookingbrain.dto.AvaliacaoResponseDTO;
import org.example.cookingbrain.dto.PratoResponseDTO;
import org.example.cookingbrain.exception.RecursoNaoEncontradoException;
import org.example.cookingbrain.model.Avaliacao;
import org.example.cookingbrain.model.Pedido;
import org.example.cookingbrain.model.Prato;
import org.example.cookingbrain.repository.AvaliacaoRepository;
import org.example.cookingbrain.repository.PratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    private final PratoRepository pratoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, PratoRepository pratoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.pratoRepository = pratoRepository;
    }

    public List<AvaliacaoResponseDTO> listartodos() {
        return avaliacaoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public AvaliacaoResponseDTO buscarPorId(Integer idAvaliacao) {
        Avaliacao avaliacao = avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Avaliação não encontrada"));
        return toResponseDTO(avaliacao);
    }

    public AvaliacaoResponseDTO salvar(AvaliacaoRequestDTO dto) {
        Avaliacao avaliacao = toEntity(dto);
        Avaliacao salvo = avaliacaoRepository.save(avaliacao);
        return toResponseDTO(salvo);
    }

    public AvaliacaoResponseDTO atualizar(Integer idAvaliacao, AvaliacaoRequestDTO dto) {
        Avaliacao avaliacao = avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Avaliação não encontrada"));

        Prato prato = pratoRepository.findById(dto.idPrato())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Prato não encontrado"));

        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());
        avaliacao.setPrato(prato);

        Avaliacao atualizado = avaliacaoRepository.save(avaliacao);

        return toResponseDTO(atualizado);
    }

    public void deletar(Integer idAvaliacao) {
        Avaliacao avaliacao = avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Avaliacao não encontrada"));
        avaliacaoRepository.deleteById(idAvaliacao);
    }

    private Avaliacao toEntity(AvaliacaoRequestDTO dto) {
        Prato prato = pratoRepository.findById(dto.idPrato())
                .orElseThrow(() -> new RuntimeException("Prato não encontrado"));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());
        avaliacao.setPrato(prato);
        return avaliacao;
    }

    private AvaliacaoResponseDTO toResponseDTO(Avaliacao avaliacao) {
        return new AvaliacaoResponseDTO(
                avaliacao.getIdAvaliacao(),
                avaliacao.getNota(),
                avaliacao.getComentario(),

                new PratoResponseDTO(
                        avaliacao.getPrato().getIdPrato(),
                        avaliacao.getPrato().getNome(),
                        avaliacao.getPrato().getDescricao(),
                        avaliacao.getPrato().getPreco(),
                        avaliacao.getPrato().getRestaurante().getIdRestaurante(),

                        avaliacao.getPrato().getPedidos().stream()
                                .map(pedido -> new PratoResponseDTO.PedidoResumidoDTO(
                                        pedido.getIdPedido(),
                                        pedido.getStatus(),
                                        pedido.getFormaPag()
                                )).toList(),

                        avaliacao.getPrato().getIngredientes().stream() // corrigido aqui
                                .map(produto -> new PratoResponseDTO.ProdutoEstoqueResumidoDTO(
                                        produto.getIdProdutoEstoque(),
                                        produto.getNome()
                                )).toList()
                )
        );
    }
}