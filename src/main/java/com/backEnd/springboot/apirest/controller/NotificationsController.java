package com.backEnd.springboot.apirest.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backEnd.springboot.apirest.dto.NotificationsDto;
import com.backEnd.springboot.apirest.entity.Notifications;
import com.backEnd.springboot.apirest.excepciones.Mensaje;
import com.backEnd.springboot.apirest.service.NotificationsService;

@RestController
@RequestMapping("/noti")
@CrossOrigin("")
public class NotificationsController {
	
	Log logs = LogFactory.getLog(getClass());

	@Autowired
	NotificationsService notiService;

	@RequestMapping("/user")
	public ResponseEntity<?> user(@RequestParam("id") int id){
		List<Notifications> noti = notiService.getByReceiver(id);
		if(noti.isEmpty()) {
			logs.warn("Notificaciones no encontrado - Notifications - NotificationsController");
			return new ResponseEntity<>(new Mensaje("No hay notificaciones"),HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<Notifications>>(noti,HttpStatus.OK);
		}
	}
	
	@SuppressWarnings("unused")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody NotificationsDto notiDto, @PathVariable("id") int id, Errors errores) {
		try {
			if (errores.hasErrors()) {
				logs.warn("Error de validacion - update - NotificationsController");
				return new ResponseEntity<>(errores.getFieldErrors(), HttpStatus.BAD_REQUEST);
			}
			Notifications noti = notiService.getOne(id).get();
			noti.setDisplay(notiDto.isDisplay());
			noti.setUpdateAt(new Date());
			notiService.save(noti);
			logs.info("Notificacion actualizado - update - NotificationsController");
			return new ResponseEntity<>(new Mensaje("Notifiacion actualizada"), HttpStatus.OK);
		} catch (Exception e) {
			logs.error("Ocurrio un error en el ChatsController");
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id){
		if(!notiService.existById(id)) {
			logs.warn("Notificaciones no encontrado - delete - NotificationsController");
			return new ResponseEntity(new Mensaje("No se encuentra notificacion"),HttpStatus.NOT_FOUND);
		}else {
			notiService.delete(id);
			logs.info("Mensaje eliminado - delete - ChatsController");
			return new ResponseEntity(new Mensaje("Chat eliminado"), HttpStatus.OK);
		}
	}
	
}
