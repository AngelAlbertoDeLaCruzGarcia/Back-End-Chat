package com.backEnd.springboot.apirest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backEnd.springboot.apirest.entity.Industries;
import com.backEnd.springboot.apirest.repositorio.IndustriesRepository;

@Service
@Transactional
public class IndustriesService {
	@Autowired
	IndustriesRepository industriesrepository;
	
	public List<Industries>list(){
		return industriesrepository.findAll();
	}
	public Optional<Industries>getOne(int id){
		return industriesrepository.findById(id);
	}
	public Optional<Industries> getByEmail(Long uuid){
		return industriesrepository.findByuuid(uuid);
	}
	public void save(Industries industries) {
		industriesrepository.save(industries);
	}
	public void delete(int id) {
		industriesrepository.deleteById(id);
	}
	public boolean existsByid(int id) {
		return industriesrepository.existsById(id);
	}
	public boolean existsByemail(Long uuid) {
		return industriesrepository.existsByuuid(uuid);
	}
}
