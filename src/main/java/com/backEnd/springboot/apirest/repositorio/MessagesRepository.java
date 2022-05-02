package com.backEnd.springboot.apirest.repositorio;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import com.backEnd.springboot.apirest.entity.Messages;

public interface MessagesRepository extends CrudRepository<Messages, Integer> {
	
	public List<Messages> findAllByOrderByIdAsc();
	
	@Query(value = "SELECT * FROM messages WHERE chats_id = :chat_id ORDER BY create_at DESC", nativeQuery = true)
	public List<Messages> findByChat(int chat_id);
	
	@Query(value = "SELECT * FROM messages WHERE chats_id = :chat AND messages.create_at = (SELECT MAX(create_at) FROM messages WHERE chats_id = :chat)", nativeQuery = true)
	public List<Messages> findMsgInbox(int chat);
	
}
