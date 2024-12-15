package com.agri_connect.Agri_connect.services;

import com.agri_connect.Agri_connect.domain.agricultor.Agricultor;

import com.agri_connect.Agri_connect.domain.agricultor.AgricultorDTO;
import com.agri_connect.Agri_connect.repositories.AgricultorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface AgricultorService {

    void insertAgricultor(AgricultorDTO agricultorDTO);

    void deleteAgricultor(UUID id);

    void updateAgricultor(UUID id, Agricultor agricultor) throws Exception;

    List<AgricultorDTO> findById(UUID id);

    List<AgricultorDTO> findByName(String name);

    List<AgricultorDTO> showAllAgricultores();
}
