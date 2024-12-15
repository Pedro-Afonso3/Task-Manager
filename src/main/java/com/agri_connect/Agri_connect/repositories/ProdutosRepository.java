package com.agri_connect.Agri_connect.repositories;

import com.agri_connect.Agri_connect.domain.produtos.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, UUID> {

    List<Produtos> findByNomeProduto(String nomeProduto);

    Optional<Produtos> findById(UUID id);
}
