package com.agri_connect.Agri_connect.repositories;

import com.agri_connect.Agri_connect.domain.agricultor.Agricultor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgricultorRepository extends JpaRepository<Agricultor, UUID> {

    Optional<Agricultor> findByName(String name);
}
