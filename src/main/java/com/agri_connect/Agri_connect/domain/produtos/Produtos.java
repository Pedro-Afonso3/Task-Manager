package com.agri_connect.Agri_connect.domain.produtos;

import com.agri_connect.Agri_connect.domain.agricultor.Agricultor;
import jakarta.persistence.*;
import lombok.*;


import java.util.UUID;

@Entity
@Table(name="produtos")
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nomeProduto;

    private Float preco;

    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name="agricultor_id")
    private Agricultor agricultor;
}
