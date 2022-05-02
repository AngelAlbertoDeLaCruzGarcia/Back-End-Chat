package com.backEnd.springboot.apirest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backEnd.springboot.apirest.entity.Notifications;
import com.backEnd.springboot.apirest.repositorio.NotificationsRepository;

@Service
public class NotificationsService {
	
	@Autowired
	NotificationsRepository notiRepo;
		
	public List<Notifications> getByReceiver(int id){
		return (List<Notifications>) notiRepo.findByReceiver_id(id);
	} 
	
	public Optional<Notifications> getOne(int id) {
		return notiRepo.findById(id);
	}
	
	public void save(Notifications noti) {
		notiRepo.save(noti);
	}
	
	public Boolean existById(int id) {
		return notiRepo.existsById(id);
	}
	
	public void delete(int id) {
		notiRepo.deleteById(id);
	}

}
