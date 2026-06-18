package org.example.cookingbrain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    private String status;
    private String formaPag;

    @ManyToOne
    @JoinColumn(name = "Cliente_idCliente")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "Pedido_Item",
            joinColumns = @JoinColumn(name = "Pedido_idPedido"),
            inverseJoinColumns = @JoinColumn(name = "Prato_idPrato")
    )
    private List<Prato> pratos;

    public Pedido() {}
}
