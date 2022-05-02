package com.backEnd.springboot.apirest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backEnd.springboot.apirest.entity.Chats;
import com.backEnd.springboot.apirest.repositorio.ChatsRepository;

@Service
@Transactional
public class ChatsService {
	@Autowired
	ChatsRepository chatsRepository;

	public List<Chats> list() {
		return (List<Chats>) chatsRepository.findAll();
	}

	public Optional<Chats> getOne(int id) {
		return chatsRepository.findById(id);
	}

	public void save(Chats chat) {
		chatsRepository.save(chat);
	}

	public void delete(int id) {
		chatsRepository.deleteById(id);
	}

	public boolean existsByid(int id) {
		return chatsRepository.existsById(id);
	}
}
