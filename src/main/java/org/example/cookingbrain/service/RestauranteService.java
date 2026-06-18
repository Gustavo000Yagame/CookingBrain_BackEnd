package org.example.cookingbrain.service;

import org.example.cookingbrain.dto.RestauranteRequestDTO;
import org.example.cookingbrain.dto.RestauranteResponseDTO;
import org.example.cookingbrain.exception.RecursoNaoEncontradoException;
import org.example.cookingbrain.model.Restaurante;
import org.example.cookingbrain.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    private final RestauranteRepository repository;

    public RestauranteService(RestauranteRepository repository) {
        this.repository = repository;
    }

    public List<RestauranteResponseDTO> listar(){
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public RestauranteResponseDTO buscarPorId(Integer id){
        Restaurante restaurante = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Restaurante não encontrado"));
        return toResponse(restaurante);
    }

    public RestauranteResponseDTO salvar(RestauranteRequestDTO dto){
        Restaurante restaurante = toEntity(dto);
        Restaurante salvo = repository.save(restaurante);
        return toResponse(salvo);
    }

    public RestauranteResponseDTO atualizar(RestauranteRequestDTO dto, Integer id){
        Restaurante restaurante = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Restaurante não encontrado"));

        restaurante.setNome(dto.nome());
        restaurante.setFoto(dto.foto());
        restaurante.setLocal(dto.local());
        restaurante.setDescricao(dto.descricao());
        restaurante.setHorarioAtendimento(dto.horarioAtendimento());
        restaurante.setNumero(dto.numero());
        restaurante.setCnpj(dto.cnpj());

        Restaurante atualizado = repository.save(restaurante);
        return toResponse(atualizado);
    }

    public void deletar(Integer id){
        repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Restaurante não encontrado"));
        repository.deleteById(id);
    }

    public List<RestauranteResponseDTO> listarRestaurantesNome(String nome){
        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toResponse)
                .toList();
    }



    private RestauranteResponseDTO toResponse(Restaurante restaurante){
        return new RestauranteResponseDTO(
                restaurante.getIdRestaurante(),
                restaurante.getNome(),
                restaurante.getFoto(),
                restaurante.getLocal(),
                restaurante.getDescricao(),
                restaurante.getHorarioAtendimento(),
                restaurante.getNumero(),
                restaurante.getCnpj()
        );
    }

    private Restaurante toEntity(RestauranteRequestDTO dto){
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(dto.nome());
        restaurante.setFoto(dto.foto());
        restaurante.setLocal(dto.local());
        restaurante.setDescricao(dto.descricao());
        restaurante.setHorarioAtendimento(dto.horarioAtendimento());
        restaurante.setNumero(dto.numero());
        restaurante.setCnpj(dto.cnpj());
        return restaurante;
    }
}
