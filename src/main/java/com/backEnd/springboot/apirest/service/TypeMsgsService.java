package com.backEnd.springboot.apirest.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backEnd.springboot.apirest.repositorio.TypeMsgsRepository;
import com.backEnd.springboot.apirest.entity.TypeMsgs;

@Service
@Transactional
public class TypeMsgsService {

	@Autowired
	TypeMsgsRepository tMsgRepo;

	public List<TypeMsgs> list() {
		return (List<TypeMsgs>) tMsgRepo.findAll();
	}

	public Optional<TypeMsgs> getOne(int id) {
		return tMsgRepo.findById(id);
	}

	public List<TypeMsgs> getByName(String name) {
		return tMsgRepo.findByNameLike("%" + name + "%");
	}	
	
	public void save(TypeMsgs tMsg) {
		tMsgRepo.save(tMsg);
	}

	public void delete(int id) {
		tMsgRepo.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return tMsgRepo.existsById(id);
	}
	
}
