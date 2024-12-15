package com.agri_connect.Agri_connect.domain.agricultor;

import com.agri_connect.Agri_connect.domain.produtos.Produtos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="agricultor")
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Agricultor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String telefone;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;

    @OneToMany(mappedBy = "agricultor",cascade = CascadeType.ALL)
    @Column(name="produtosList")
    private List<Produtos> produtosList;

    @PrePersist
    private void gerarDados() {
        if (id == null) {
            this.id = java.util.UUID.randomUUID();
        }
    }
}
