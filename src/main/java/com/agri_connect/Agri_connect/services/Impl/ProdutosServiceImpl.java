package com.agri_connect.Agri_connect.services.Impl;

import com.agri_connect.Agri_connect.Exceptions.ProdutoNotFoundException;
import com.agri_connect.Agri_connect.domain.agricultor.AgricultorDTO;
import com.agri_connect.Agri_connect.domain.produtos.Produtos;
import com.agri_connect.Agri_connect.domain.produtos.ProdutosDTO;
import com.agri_connect.Agri_connect.repositories.ProdutosRepository;
import com.agri_connect.Agri_connect.services.ProdutosService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutosServiceImpl implements ProdutosService {
    @Autowired
    ProdutosRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void insertProdutos(ProdutosDTO produtosDTO) {
        Produtos produtos = convertToEntity(produtosDTO);
        repository.save(produtos);
    }

    @Override
    public void deleteProdutos(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void updateProdutos(UUID id, Produtos produtos) throws Exception {
        Optional<Produtos> produtos1 = repository.findById(id);

        if (produtos1.isPresent()) {
            Produtos produtosExists = produtos1.get();

            if (produtos.getNomeProduto() != null) {
                produtosExists.setNomeProduto(produtos.getNomeProduto());
            }
            if (produtos.getPreco() != null) {
                produtosExists.setPreco(produtos.getPreco());
            }
            if (produtos.getQuantidade() != null) {
                produtosExists.setQuantidade(produtos.getQuantidade());
            }

            repository.save(produtosExists);
        } else {
            throw new Exception("Produto não encontrado");
        }
    }


    // Converte Produtos para ProdutosDTO
    private ProdutosDTO convertToDTO(Produtos produtos) {
        ProdutosDTO produtosDTO = new ProdutosDTO();

        produtosDTO.setNomeProduto(produtos.getNomeProduto());
        produtosDTO.setPreco(produtos.getPreco());
        produtosDTO.setQuantidade(produtos.getQuantidade());
        produtosDTO.setAgricultorList((List<AgricultorDTO>) produtos.getAgricultor());
        return produtosDTO;
    }

    // Converte ProdutosDTO para Produtos
    private Produtos convertToEntity(ProdutosDTO produtosDTO) {
        Produtos produtos = new Produtos();

        produtos.setNomeProduto(produtosDTO.getNomeProduto());
        produtos.setPreco(produtosDTO.getPreco());
        produtos.setQuantidade(produtosDTO.getQuantidade());
        produtos.setAgricultor(produtosDTO.getAgricultorList());
        return produtos;
    }

    @Override
    public List<ProdutosDTO> findById(UUID id) {
        Optional<Produtos> produtos = repository.findById(id);
        if (produtos.isEmpty()) {
            throw new ProdutoNotFoundException("Nenhum produto com esse ID foi encontrado!");
        }
        return produtos.stream()
                .map(produto -> modelMapper.map(produto, ProdutosDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProdutosDTO> findByNomeProduto(String nomeProduto) {
    /*return repository.findByNomeProduto(nomeProduto)
            .stream()
            .map(produto -> modelMapper.map(produto, ProdutosDTO.class))
            .collect(Collectors.toList());*/

        List<Produtos> produtos = repository.findByNomeProduto(nomeProduto);
        if (produtos.isEmpty()) {
            throw new ProdutoNotFoundException("Nenhum produto com esse NOME foi encontrado!");
        }
        return produtos.stream()
                .map(produto -> modelMapper.map(produto, ProdutosDTO.class))
                .collect(Collectors.toList());
    }


@Override
public List<ProdutosDTO> showAllProdutos() {
    List<Produtos> produtos = repository.findAll();
    if (produtos.isEmpty()) {
        throw new ProdutoNotFoundException("Nenhum produto foi encontrado!");
    }
    return produtos.stream()
            .map(produto -> modelMapper.map(produto, ProdutosDTO.class))
            .collect(Collectors.toList());
    //.map(this::convertToDTO)
    //.toList();
}
}

