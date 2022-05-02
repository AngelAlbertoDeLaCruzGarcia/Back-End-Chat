package com.backEnd.springboot.apirest.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backEnd.springboot.apirest.entity.Companies;

public interface CompaniesRepository extends JpaRepository<Companies, Integer>{

}
