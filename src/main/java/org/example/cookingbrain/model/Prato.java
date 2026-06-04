package org.example.cookingbrain.model;

import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Prato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrato;

    private String nome;

    private String descricao;

    private Double preco;

    @ManyToOne
    @JoinColumn(name = "Restaurante_idRestaurante")
    private Restaurante restaurante;

    @OneToMany(mappedBy = "prato")
    private List<Avaliacao> avaliacoes;



    //inverseJoinColumns: Significa "coluna da relação inversa". Define o nome da coluna que vai armazenar a chave primária da tabela que está do outro lado da lista (no caso, o idPrato).
    //Se você olhar o seu banco de dados depois que o JPA rodar, a tabela Pedido_Item terá exatamente essas duas colunas estruturadas para fazer a ponte entre as compras e os pratos do cardápio.

    @ManyToMany
    @JoinTable(
            name = "Prato_Ingrediente",
            joinColumns = @JoinColumn(name = "Prato_idPrato"),
            inverseJoinColumns = @JoinColumn(name = "ProdutoEstoque_idProdutoEstoque")
    )
    private List<ProdutoEstoque> ingredientes;

    public Prato() {}
}