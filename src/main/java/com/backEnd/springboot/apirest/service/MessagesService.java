package com.backEnd.springboot.apirest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backEnd.springboot.apirest.entity.Messages;
import com.backEnd.springboot.apirest.repositorio.MessagesRepository;

@Service
@Transactional
public class MessagesService {
	@Autowired
	MessagesRepository msgsRepository;

	public List<Messages> getByChat(int id) {
		return msgsRepository.findByChat(id);
	}

	public List<Messages> getMsgInbox(int chat){
		return msgsRepository.findMsgInbox(chat);
	}
	public Optional<Messages> getOne(int id) {
		return msgsRepository.findById(id);
	}

	public void save(Messages msg) {
		msgsRepository.save(msg);
	}

	public void delete(int id) {
		msgsRepository.deleteById(id);
	}

	public boolean existsByid(int id) {
		return msgsRepository.existsById(id);
	}
}
