package com.example.demo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.RegisterBean;

public interface UserRepository extends JpaRepository<RegisterBean,Long> {
	@Query("SELECT u FROM RegisterBean u WHERE u.email = ?1")
    public RegisterBean findByEmail(String email);
}
