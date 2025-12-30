package com.poetry.poetry_documentation_reporting.repository;

import com.poetry.poetry_documentation_reporting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
