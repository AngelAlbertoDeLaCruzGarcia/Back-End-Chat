package com.backEnd.springboot.apirest.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.backEnd.springboot.apirest.entity.Persons;

public interface PersonsRepository extends CrudRepository<Persons, Integer>{

}
