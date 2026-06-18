package org.example.cookingbrain.repository;

import org.example.cookingbrain.model.Cliente;
import org.example.cookingbrain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByClienteIdCliente(Integer id);
}
