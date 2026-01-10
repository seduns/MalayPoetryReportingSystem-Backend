package com.poetry.poetry_documentation_reporting.repository;

import com.poetry.poetry_documentation_reporting.model.User;
import com.poetry.poetry_documentation_reporting.model.enumoption.USER_ROLE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    long countByRole(USER_ROLE role);

}
