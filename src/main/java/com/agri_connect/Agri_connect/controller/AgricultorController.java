package com.agri_connect.Agri_connect.controller;

import com.agri_connect.Agri_connect.domain.agricultor.Agricultor;
import com.agri_connect.Agri_connect.domain.agricultor.AgricultorDTO;
import com.agri_connect.Agri_connect.services.AgricultorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
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
@RequestMapping(name="agricultor")
@Tag(name = "Agricultor", description ="Controller para Agricultor")
public class AgricultorController {

    @Autowired
    AgricultorService agricultorService;


    @PostMapping("/insertAgricultor")
    @Operation(summary = "Cadastrar Agricultor", description = "Cadastrar um novo Agricultor")
    public ResponseEntity<AgricultorDTO> insertAgricultor(@RequestBody AgricultorDTO agricultorDTO) throws MalformedURLException, JsonProcessingException {
        URL url = new URL("https://viacep.com.br/ws/" + agricultorDTO.getCep() + "/json/");

        StringBuilder jsonCep = new StringBuilder();
        try (InputStream is = url.openStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {

            String line;
            while ((line = br.readLine()) != null) {
                jsonCep.append(line);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        AgricultorDTO dadosCep = objectMapper.readValue(jsonCep.toString(), AgricultorDTO.class);

        // Atualiza os dados do agricultor com os dados do CEP
        agricultorDTO.setCep(dadosCep.getCep());
        agricultorDTO.setLogradouro(dadosCep.getLogradouro());
        agricultorDTO.setComplemento(dadosCep.getComplemento());
        agricultorDTO.setBairro(dadosCep.getBairro());
        agricultorDTO.setLocalidade(dadosCep.getLocalidade());
        agricultorDTO.setUf(dadosCep.getUf());
        agricultorDTO.setEstado(dadosCep.getEstado());
        agricultorDTO.setRegiao(dadosCep.getRegiao());

        agricultorService.insertAgricultor(agricultorDTO);
        return ResponseEntity.ok(agricultorDTO);
    }

    @DeleteMapping("/deleteAgricultor")
    @Operation(summary = "Deletar Agricultor", description = "Deletar Agricultor")
    public ResponseEntity<Agricultor> deleteAgricultor(UUID id){
        agricultorService.deleteAgricultor(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateAgricultor")
    @Operation(summary = "Atualizar um Agricultor", description = "Atualizar um Agricultor")
    public ResponseEntity<Agricultor> updateAgricultor(UUID id, @RequestBody Agricultor agricultor) throws Exception {
       agricultorService.updateAgricultor(id,agricultor);
        return ResponseEntity.ok(agricultor);
    }

    //BUSCA POR TODOS
    @GetMapping("/showAll")
    @Operation(summary = "Consultar todos", description = "Consultar todos agricultores")
    public ResponseEntity<Iterable<AgricultorDTO>> showAllAgricultor(){
        return ResponseEntity.ok(agricultorService.showAllAgricultores());
    }

    //BUSCA POR ID
    @GetMapping("/findById")
    @Operation(summary = "Consultar por ID", description = "Consultar os agricultor por ID")
    public ResponseEntity<Optional<AgricultorDTO>> findById(UUID id){
        return ResponseEntity.ok(agricultorService.findById(id));
    }

    //BUSCAR POR Name
    @GetMapping("/findByName")
    @Operation(summary = "Consultar por ID", description = "Consultar os agricultores por Name")
    public ResponseEntity<Optional<AgricultorDTO>> findByName(String name){
        return ResponseEntity.ok(agricultorService.findByName(name));
    }

}
