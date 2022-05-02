package com.backEnd.springboot.apirest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backEnd.springboot.apirest.entity.Persons;
import com.backEnd.springboot.apirest.repositorio.PersonsRepository;

@Service
@Transactional
public class PersonsService {
	@Autowired
	PersonsRepository personRepo;
	
	public List<Persons> list(){
		return (List<Persons>) personRepo.findAll();
	}
	
	public Optional<Persons> getOne(int id) {
		return personRepo.findById(id);
	}
	
	public boolean getExitsById(int id) {
		return personRepo.existsById(id);
	}

}
