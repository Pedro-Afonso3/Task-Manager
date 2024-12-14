package com.agri_connect.Agri_connect.repositories;

import com.agri_connect.Agri_connect.domain.produtos.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProdutosRepository extends JpaRepository<Produtos, UUID> {

    Optional<Produtos> findByNomeProduto(String nomeProduto);
}
