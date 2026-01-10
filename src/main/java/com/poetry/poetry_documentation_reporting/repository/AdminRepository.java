package com.poetry.poetry_documentation_reporting.repository;


import com.poetry.poetry_documentation_reporting.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
