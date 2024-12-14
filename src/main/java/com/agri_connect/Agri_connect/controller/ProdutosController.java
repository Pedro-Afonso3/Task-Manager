package com.agri_connect.Agri_connect.controller;

import com.agri_connect.Agri_connect.domain.agricultor.Agricultor;
import com.agri_connect.Agri_connect.domain.agricultor.AgricultorDTO;
import com.agri_connect.Agri_connect.domain.produtos.Produtos;
import com.agri_connect.Agri_connect.domain.produtos.ProdutosDTO;
import com.agri_connect.Agri_connect.services.AgricultorService;
import com.agri_connect.Agri_connect.services.ProdutosService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(name="produtos")
@Tag(name = "Produtos", description ="Controller para Produtos")
public class ProdutosController {

    @Autowired
    ProdutosService produtosService;

    @PostMapping("/insertProdutos")
    @Operation(summary = "Cadastrar Produtos", description = "Cadastrar um novo Produtos")
    public ResponseEntity<ProdutosDTO> insertAgricultor(@RequestBody ProdutosDTO produtosDTO) {
        produtosService.insertProdutos(produtosDTO);
        return ResponseEntity.ok(produtosDTO);
    }

    @DeleteMapping("/deleteProdutos")
    @Operation(summary = "Deletar Produtos", description = "Deletar Produtos")
    public ResponseEntity<Produtos> deleteAgricultor(UUID id){
        produtosService.deleteProdutos(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateProdutos")
    @Operation(summary = "Atualizar um Produtos", description = "Atualizar um Produtos")
    public ResponseEntity<Produtos> updateProdutos(UUID id, @RequestBody Produtos produtos) throws Exception {
        produtosService.updateProdutos(id,produtos);
        return ResponseEntity.ok(produtos);
    }

    //BUSCA POR TODOS
    @GetMapping("/showAll")
    @Operation(summary = "Consultar todos", description = "Consultar todos Produtos")
    public ResponseEntity<Iterable<ProdutosDTO>> showAllAgricultor(){
        return ResponseEntity.ok(produtosService.showAllProdutos());
    }

    //BUSCA POR ID
    @GetMapping("/findById")
    @Operation(summary = "Consultar por ID", description = "Consultar os Produtos por ID")
    public ResponseEntity<Optional<ProdutosDTO>> findById(UUID id){
        return ResponseEntity.ok(produtosService.findById(id));
    }

    //BUSCAR POR Name
    @GetMapping("/findByNomeProduto")
    @Operation(summary = "Consultar por ID", description = "Consultar os Produtos por Nome")
    public ResponseEntity<Optional<ProdutosDTO>> findByNomeProduto(String nomeProduto){
        return ResponseEntity.ok(produtosService.findByNomeProduto(nomeProduto));
    }
}
