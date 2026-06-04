package org.example.cookingbrain.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRestaurante;

    private String nome;
    private byte[] foto;
    private String local;
    private String descricao;
    private String horarioAtendimento;
    private String numero;
    private String cnpj;

    @OneToMany(mappedBy = "restaurante")
    private List<Prato> pratos;

    public Restaurante() {}
}