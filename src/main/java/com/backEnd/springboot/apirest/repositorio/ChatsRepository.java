package com.backEnd.springboot.apirest.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.backEnd.springboot.apirest.entity.Chats;

public interface ChatsRepository extends CrudRepository<Chats,Integer>{
	
}
