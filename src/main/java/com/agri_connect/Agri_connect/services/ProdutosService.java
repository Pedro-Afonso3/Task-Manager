package com.agri_connect.Agri_connect.services;

import com.agri_connect.Agri_connect.domain.agricultor.AgricultorDTO;
import com.agri_connect.Agri_connect.domain.produtos.Produtos;
import com.agri_connect.Agri_connect.domain.produtos.ProdutosDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface ProdutosService {

    void insertProdutos(ProdutosDTO produtosDTO);

    void deleteProdutos(UUID id);

    void updateProdutos(UUID id, Produtos produtos) throws Exception;

    Optional<ProdutosDTO> findById(UUID id);

    Optional<ProdutosDTO> findByNomeProduto(String nomeProduto);

    List<ProdutosDTO> showAllProdutos();
}
