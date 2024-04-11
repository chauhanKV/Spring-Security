package com.example.SpringSecurity.repo;

import com.example.SpringSecurity.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
