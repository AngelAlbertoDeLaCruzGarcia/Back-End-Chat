package com.backEnd.springboot.apirest.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backEnd.springboot.apirest.entity.Chats;
import com.backEnd.springboot.apirest.excepciones.Mensaje;
import com.backEnd.springboot.apirest.service.ChatsService;
import com.backEnd.springboot.apirest.dto.ChatsDto;
import com.cloudinary.utils.StringUtils;

@RestController
@RequestMapping("/chats")
@CrossOrigin(origins = "")
public class ChatsController {
	
	@Autowired
	ChatsService chatsService;
	Log logs = LogFactory.getLog(getClass());

	@GetMapping("/list")
	public ResponseEntity<List<Chats>> index() {
		List<Chats> list = chatsService.list();
		return new ResponseEntity<List<Chats>>(list, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/detail/{id}")
	public ResponseEntity<Chats> getByid(@PathVariable("id") int id) {
		if (!chatsService.existsByid(id)) {
			logs.warn("Chat no encontrado - detail - ChatsController");
			return new ResponseEntity(new Mensaje("No existe el chat "), HttpStatus.NOT_FOUND);
		} else {
			Chats chat = chatsService.getOne(id).get();
			logs.info("Chat encontrado - detail - ChatsController");
			return new ResponseEntity<Chats>(chat, HttpStatus.OK);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ChatsDto chatDto) {
		try {
			if (StringUtils.isBlank(chatDto.getName())) {
				logs.warn("Error de validacion - create - ChatsController");
				return new ResponseEntity(new Mensaje("El nombre es requerido"), HttpStatus.BAD_REQUEST);
			}
			chatDto.setCreateAt(new Date());
			Chats chat = new Chats(chatDto.getName(), chatDto.getCreateAt(), chatDto.getUpdateAt(),
					chatDto.getDeleteAt());
			chatsService.save(chat);
			logs.info("Se registro un chat - Create - ChatsController");
			return new ResponseEntity(new Mensaje("Chat registrado correctamente"), HttpStatus.OK);
		} catch (Exception e) {
			logs.error("Ocurrio un error en el ChatsController");
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@SuppressWarnings("unused")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody ChatsDto chatsDto, @PathVariable("id") int id) {
		try {
			if (StringUtils.isBlank(chatsDto.getName())) {
				logs.warn("Error de validacion - update - ChatsController");
				return new ResponseEntity<>(new Mensaje("El nombre es requerido"), HttpStatus.BAD_REQUEST);
			}
			Chats chat = chatsService.getOne(id).get();
			chat.setName(chatsDto.getName());
			chat.setUpdateAt(new Date());
			chatsService.save(chat);
			logs.info("Chat actualizado - update - ChatsController");
			return new ResponseEntity<>(new Mensaje("Nombre de chat actualizado"), HttpStatus.OK);
		} catch (Exception e) {
			logs.error("Ocurrio un error en el ChatsController");
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!chatsService.existsByid(id)) {
			logs.warn("Chat no encontrado - delete - ChatsController");
			return new ResponseEntity(new Mensaje("No existe chat"), HttpStatus.NOT_FOUND);
		} else {
			chatsService.delete(id);
			logs.info("Chat eliminado - delete - ChatsController");
			return new ResponseEntity(new Mensaje("Chat eliminado"), HttpStatus.OK);
		}
	}
}