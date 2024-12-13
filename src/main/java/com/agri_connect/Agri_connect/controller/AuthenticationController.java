package com.agri_connect.Agri_connect.controller;

import com.agri_connect.Agri_connect.domain.users.AuthenticationDTO;
import com.agri_connect.Agri_connect.domain.users.LoginResponseDTO;
import com.agri_connect.Agri_connect.domain.users.RegisterDTO;
import com.agri_connect.Agri_connect.domain.users.Users;
import com.agri_connect.Agri_connect.infra.security.TokenService;
import com.agri_connect.Agri_connect.repositories.UsersRepository;
import com.agri_connect.Agri_connect.services.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Tag(name = "Autenticação", description ="Controller Login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsersRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Realização de login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword =new UsernamePasswordAuthenticationToken(data.email(),data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    @Operation(summary = "Cadastro de User", description = "Cadastro de novo User")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        //if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        if (this.repository.findByEmail(data.getEmail()) != null) { return ResponseEntity.badRequest().body("Erro: Usuário já existe."); }

        String encrytedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Users newUser = new Users(data.getName(),data.getEmail(),encrytedPassword, data.getRole());

        String subject = "Bem-vindo ao nosso serviço!";
        String text = "Olá " + ",\n\n Obrigado por se cadastrar no nosso serviço!";
        emailService.sendEmail(data.getEmail(), subject, text);

        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
