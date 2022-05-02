package com.backEnd.springboot.apirest.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backEnd.springboot.apirest.entity.Inboxs;
import com.backEnd.springboot.apirest.excepciones.Mensaje;
import com.backEnd.springboot.apirest.service.InboxsService;

@RestController
@RequestMapping("/inboxs")
@CrossOrigin
public class InboxsController {
	
	@Autowired
	InboxsService inboxsService;
	
	Log logs = LogFactory.getLog(getClass());

	@GetMapping("/name")
	public ResponseEntity<?> list(@RequestParam("person") int person, @RequestParam("chat") int chat) {
		try {
			if(person > 0 & chat > 0) 
			{
				List<Inboxs> inboxs = inboxsService.getByPersonAndChat(person, chat);
				if (!inboxs.isEmpty())
					return new ResponseEntity<List<Inboxs>>(inboxs, HttpStatus.OK);
				else 
					logs.warn("Mensajes no encontrado - inboxs - InboxsController");
					return new ResponseEntity<>(new Mensaje("No se encontro ningun dato"), HttpStatus.NOT_FOUND);
			}else
				logs.warn("Id chat no valido - inboxs - InboxsController");
				return new ResponseEntity<>(new Mensaje("Id chat no valido"),HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/person")
	public ResponseEntity<List<Inboxs>> person(@RequestParam("person") int person) {
		List<Inboxs> inboxs = inboxsService.getByPerson(person);
		return new ResponseEntity<List<Inboxs>>(inboxs, HttpStatus.OK);
	} 
}
