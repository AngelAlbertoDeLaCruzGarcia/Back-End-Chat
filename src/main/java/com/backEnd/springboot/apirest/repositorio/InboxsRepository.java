package com.backEnd.springboot.apirest.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.backEnd.springboot.apirest.entity.Inboxs;

public interface InboxsRepository extends CrudRepository<Inboxs, Integer> {

	@Query(value = "SELECT * FROM inboxs WHERE persons_id = :persons_id",nativeQuery = true)
	public List<Inboxs> findByPerson(int persons_id);
	
	@Query(value = "SELECT * FROM inboxs WHERE chats_id = :chats_id AND persons_id != :persons_id",nativeQuery = true)
	public List<Inboxs> findByPersons_idAndChats_id(int persons_id, int chats_id);

}
