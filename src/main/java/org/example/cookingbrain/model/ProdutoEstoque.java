package org.example.cookingbrain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ProdutoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProdutoEstoque;

    private Integer quantidade;
    private String nome;

    @ManyToMany(mappedBy = "ingredientes")
    private List<Prato> pratosQueUtilizam;

    public ProdutoEstoque() {}
}