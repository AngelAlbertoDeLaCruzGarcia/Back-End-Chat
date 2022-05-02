package com.backEnd.springboot.apirest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.backEnd.springboot.apirest.entity.Inboxs;
import com.backEnd.springboot.apirest.repositorio.InboxsRepository;

@Service
@Transactional
public class InboxsService {
	
	@Autowired
	InboxsRepository inboxsRepo;
	
	public List<Inboxs> getByPersonAndChat(int person, int chat){
		return (List<Inboxs>) inboxsRepo.findByPersons_idAndChats_id(person, chat);
	}
	
	public Optional<Inboxs> getOne(int id){
		return inboxsRepo.findById(id);
	}

	public void save(Inboxs inbox) {
		inboxsRepo.save(inbox);
	}
	
	public void delete(int id) {
		inboxsRepo.deleteById(id);
	}
	
	public boolean existById(int id) {
		return inboxsRepo.existsById(id);
	}
	
	public List<Inboxs> getByPerson(int persons_id) {
		return inboxsRepo.findByPerson(persons_id);
	}
}
