package com.backEnd.springboot.apirest.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.backEnd.springboot.apirest.entity.Notifications;

public interface NotificationsRepository extends CrudRepository<Notifications, Integer>{
	
	@Query(value = "SELECT * FROM notifications WHERE receiver_id = :id ORDER BY create_at DESC", nativeQuery = true)
	public List<Notifications> findByReceiver_id(int id);

}
