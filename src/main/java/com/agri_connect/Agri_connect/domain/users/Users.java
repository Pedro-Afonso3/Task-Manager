package com.agri_connect.Agri_connect.domain.users;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="users")
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String email;

    private String password;

    private UsersRole role;

    public Users( String name, String email, String password, UsersRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }



    @PrePersist
    private void gerarDados() {
        if (id == null) {
            this.id = java.util.UUID.randomUUID();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        switch (this.role) {
            case ADMIN:
                return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_AGRICULTOR"),
                        new SimpleGrantedAuthority("ROLE_TRANSPORTADOR_EMPRESA"),
                        new SimpleGrantedAuthority("ROLE_TRANSPORTADOR_INDIVIDUAL")
                );
            case AGRICULTOR:
                return List.of(new SimpleGrantedAuthority("ROLE_AGRICULTOR"));
            case TRANSPORTADOR_EMPRESA:
                return List.of(new SimpleGrantedAuthority("ROLE_TRANSPORTADOR_EMPRESA"));
            case TRANSPORTADOR_INDIVIDUAL:
                return List.of(new SimpleGrantedAuthority("ROLE_TRANSPORTADOR_INDIVIDUAL"));
            default:
                return List.of();
        }
    }



    public String getEmail(){
        return email;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
