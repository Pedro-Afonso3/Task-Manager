package com.agri_connect.Agri_connect.services.Impl;

import com.agri_connect.Agri_connect.domain.agricultor.Agricultor;
import com.agri_connect.Agri_connect.domain.agricultor.AgricultorDTO;
import com.agri_connect.Agri_connect.domain.produtos.Produtos;
import com.agri_connect.Agri_connect.domain.produtos.ProdutosDTO;
import com.agri_connect.Agri_connect.repositories.AgricultorRepository;
import com.agri_connect.Agri_connect.services.AgricultorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AgricultorServiceImpl implements AgricultorService {

    @Autowired
    AgricultorRepository repository;


    @Override
    public void insertAgricultor(AgricultorDTO agricultorDTO) {
        Agricultor agricultor = convertToEntity(agricultorDTO);
        repository.save(agricultor);
    }

    @Override
    public void deleteAgricultor(UUID id) {
        repository.deleteById(id);

    }

    @Override
    public void updateAgricultor(UUID id, Agricultor agricultor) throws Exception {
        Optional<Agricultor> agricultor1 = repository.findById(id);

        if (agricultor1.isPresent()) {
            Agricultor agricultorExists = agricultor1.get();

            if (agricultor.getName() != null) {
                agricultorExists.setName(agricultor.getName());
            }

            if (agricultor.getTelefone() != null) {
                agricultorExists.setTelefone(agricultor.getTelefone());
            }

            if (agricultor.getCep() != null) {
                agricultorExists.setCep(agricultor.getCep());
            }

            if (agricultor.getLogradouro() != null) {
                agricultorExists.setLogradouro(agricultor.getLogradouro());
            }

            if (agricultor.getComplemento() != null) {
                agricultorExists.setComplemento(agricultor.getComplemento());
            }

            if (agricultor.getBairro() != null) {
                agricultorExists.setBairro(agricultor.getBairro());
            }

            if (agricultor.getLocalidade() != null) {
                agricultorExists.setLocalidade(agricultor.getLocalidade());
            }

            if (agricultor.getUf() != null) {
                agricultorExists.setUf(agricultor.getUf());
            }

            if (agricultor.getEstado() != null) {
                agricultorExists.setEstado(agricultor.getEstado());
            }

            if (agricultor.getRegiao() != null) {
                agricultorExists.setRegiao(agricultor.getRegiao());
            }

            // Atualizar ProdutosList
            if (agricultor.getProdutosList() != null) {
                agricultorExists.getProdutosList().clear();
                agricultorExists.getProdutosList().addAll(agricultorExists.getProdutosList());
            }
            repository.save(agricultorExists);
        }else{
            throw new Exception("Agricultor não Encontrado");
            }

    }

    @Override
    public Optional<AgricultorDTO> findById(UUID id) {
        return repository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public Optional<AgricultorDTO> findByName(String name) {
        return repository.findByName(name)
                .map(this::convertToDTO);
    }

    @Override
    public List<AgricultorDTO> showAllAgricultores() {
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Converte Agricultor para AgricultorDTO
    private AgricultorDTO convertToDTO(Agricultor agricultor){
        AgricultorDTO agricultorDTO = new AgricultorDTO();

        agricultorDTO.setName(agricultor.getName());
        agricultorDTO.setTelefone(agricultor.getTelefone());
        agricultorDTO.setCep(agricultor.getCep());
        agricultorDTO.setLogradouro(agricultor.getLogradouro());
        agricultorDTO.setComplemento(agricultor.getComplemento());
        agricultorDTO.setBairro(agricultor.getBairro());
        agricultorDTO.setLocalidade(agricultor.getLocalidade());
        agricultorDTO.setUf(agricultor.getUf());
        agricultorDTO.setEstado(agricultor.getEstado());
        agricultorDTO.setRegiao(agricultor.getRegiao());
        agricultorDTO.setProdutosList(agricultor.getProdutosList());

        return agricultorDTO;
    }

    // Converte AgricultorDTO para Agricultor
    private Agricultor convertToEntity(AgricultorDTO agricultorDTO){
        Agricultor agricultor = new Agricultor();

        agricultor.setName(agricultorDTO.getName());
        agricultor.setTelefone(agricultorDTO.getTelefone());
        agricultor.setCep(agricultorDTO.getCep());
        agricultor.setLogradouro(agricultorDTO.getLogradouro());
        agricultor.setComplemento(agricultorDTO.getComplemento());
        agricultor.setBairro(agricultorDTO.getBairro());
        agricultor.setLocalidade(agricultorDTO.getLocalidade());
        agricultor.setUf(agricultorDTO.getUf());
        agricultor.setEstado(agricultorDTO.getEstado());
        agricultor.setRegiao(agricultorDTO.getRegiao());
        agricultor.setProdutosList(agricultorDTO.getProdutosList());

        return agricultor;
    }

    // Converte lista de Agricultores para lista de AgricultoresDTO
    private Iterable<AgricultorDTO> convertToDTOList(Iterable<Agricultor> agricultorList) {
        List<AgricultorDTO> agricultorDTOList = new ArrayList<>();
        for (Agricultor agricultor : agricultorList) {
            agricultorDTOList.add(convertToDTO(agricultor));
        }
        return agricultorDTOList;
    }
}
