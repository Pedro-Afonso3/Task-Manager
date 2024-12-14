package com.agri_connect.Agri_connect.domain.produtos;

import com.agri_connect.Agri_connect.domain.agricultor.Agricultor;
import com.agri_connect.Agri_connect.domain.agricultor.AgricultorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutosDTO {

    private UUID id;
    private String nomeProduto;
    private Float preco;
    private Integer quantidade;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AgricultorDTO> agricultorList;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @JsonIgnore // Oculta na serialização (POST e PUT)
    public Agricultor getAgricultorList() {
        return (Agricultor) agricultorList;
    }

    public void setAgricultorList(List<AgricultorDTO> agricultorList) {
        this.agricultorList = agricultorList;
    }
}
