package com.agri_connect.Agri_connect.repositories;

import com.agri_connect.Agri_connect.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,String> {

    UserDetails findByEmail(String email);
}
