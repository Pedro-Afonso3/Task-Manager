package com.agri_connect.Agri_connect.repositories;

import com.agri_connect.Agri_connect.domain.agricultor.Agricultor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AgricultorRepository extends JpaRepository<Agricultor, UUID> {

    Optional<Agricultor> findByName(String name);
}
