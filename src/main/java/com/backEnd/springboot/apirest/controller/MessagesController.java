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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backEnd.springboot.apirest.dto.MessagesDto;
import com.backEnd.springboot.apirest.entity.Chats;
import com.backEnd.springboot.apirest.entity.Inboxs;
import com.backEnd.springboot.apirest.entity.Messages;
import com.backEnd.springboot.apirest.entity.Notifications;
import com.backEnd.springboot.apirest.entity.Persons;
import com.backEnd.springboot.apirest.entity.TypeMsgs;
import com.backEnd.springboot.apirest.excepciones.Mensaje;
import com.backEnd.springboot.apirest.service.ChatsService;
import com.backEnd.springboot.apirest.service.InboxsService;
import com.backEnd.springboot.apirest.service.MessagesService;
import com.backEnd.springboot.apirest.service.NotificationsService;
import com.backEnd.springboot.apirest.service.PersonsService;
import com.backEnd.springboot.apirest.service.TypeMsgsService;

//import org.springframework.boot.actuate.endpoint.Sanitizer;

@RestController
@RequestMapping("/msgs")
@CrossOrigin(origins = "")
public class MessagesController {

	@Autowired
	MessagesService msgsService;
	@Autowired
	TypeMsgsService tMsgService;
	@Autowired
	ChatsService chatsService;
	@Autowired
	InboxsService inboxsService;
	@Autowired
	PersonsService personsService;
	@Autowired
	NotificationsService notiService;

	Log logs = LogFactory.getLog(getClass());
	
	@GetMapping("/chat")
	public ResponseEntity<?> list(@RequestParam int id) {
		try {
			if(id > 0) 
			{
				List<Messages> list = msgsService.getByChat(id);
				if (!list.isEmpty())
					return new ResponseEntity<List<Messages>>(list, HttpStatus.OK);
				else 
					logs.warn("Mensajes no encontrado - chat - MessagesController");
					return new ResponseEntity<>(new Mensaje("No se encontro ningun dato"), HttpStatus.NOT_FOUND);
			}else
				logs.warn("Id chat no valido - chat - MessagesController");
				return new ResponseEntity<>(new Mensaje("Id chat no valido"),HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "null" })
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody MessagesDto msgDto, Errors errores) {
		try {
			if (errores.hasErrors()) {
				logs.warn("Error de validacion - create - MessagesController");
				return new ResponseEntity(errores.getFieldErrors(), HttpStatus.BAD_REQUEST);
			}
			Chats chat = null;
			Inboxs	inbox1 = null,	inbox2 = null;
			if(!personsService.getExitsById(msgDto.getSender())) {
				logs.warn("Emisor de mensaje no encontrado - create - MessagesController");
				return new ResponseEntity(new Mensaje("No existe el usario emisor"),HttpStatus.NOT_FOUND);
			}
			if(!personsService.getExitsById(msgDto.getReceiver())) {
				logs.warn("Receptor de mensaje no encontrado - create - MessagesController");
				return new ResponseEntity(new Mensaje("No existe el usario receptor"),HttpStatus.NOT_FOUND);
			}
			Persons sender = personsService.getOne(msgDto.getSender()).get();
			Persons receiver = personsService.getOne(msgDto.getReceiver()).get();
			if (!chatsService.existsByid(msgDto.getChat())) {
				
				if(msgDto.getChat() > 0 ) {
					logs.warn("Chat no encontrado - create - MessagesController");
					return new ResponseEntity(new Mensaje("No existe chat"),HttpStatus.NOT_FOUND);
				}
				chat = new Chats(null, new Date(), null, null);
				inbox1 = new Inboxs(new Date(),null,null,chat,sender);
				inbox2 = new Inboxs(new Date(),null,null,chat,receiver);
				chatsService.save(chat);
				inboxsService.save(inbox1);
				inboxsService.save(inbox2);
			}else
				chat = chatsService.getOne(msgDto.getChat()).get();
			
			TypeMsgs typeMsg = tMsgService.getByName(msgDto.getTypeMsg()).get(0);
			msgDto.setCreateAt(new Date());
			
			Messages msg = new Messages(msgDto.getMessage(), msgDto.getUrl(), msgDto.getCreateAt(),
					msgDto.getUpdateAt(), msgDto.getDeleteAt(), typeMsg, chat, sender);
			msgsService.save(msg);
			
			int finalText = 50;
			if( msgDto.getMessage().length() < 50 ) {
				finalText = msgDto.getMessage().length();
			}
			Notifications noti = new Notifications(msgDto.getMessage().substring(0 ,finalText), null, new Date(), null,
					false, chat, receiver, sender);
			notiService.save(noti);
			logs.info("Se registro un nuevo mensaje - Create - MessagesController");
			return new ResponseEntity(new Mensaje("Mensaje registrado"), HttpStatus.OK);
		} catch (Exception e) {
			logs.error("Ocurrio un error en el MessagesController");
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping("/inboxs")
	public ResponseEntity<?> getFromInboxa(@RequestParam("chat") int chat) {	
		List<Messages> msg = msgsService.getMsgInbox(chat);
		if(msg.isEmpty()) {
			return new ResponseEntity<>(new Mensaje("Mensajes no encontrados por chat"),HttpStatus.NOT_FOUND);	
		}else{
			return new ResponseEntity<>(msg,HttpStatus.OK);	
		}
	}
/*
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/det")
	public ResponseEntity<?> sasd() {
		try {
			String[] angel = new String[] {"ANuevo", "Otro"};
			
			String nombre = "url", res = "";
			String nombre2 = "<script>alert(\"Harmful Script\");</script> <p style=\"a style\" class=\"a-different-class\">Test</p>";
			Sanitizer s = new Sanitizer();
			/////s.setKeysToSanitize("arrr", "sss");					
			return new ResponseEntity( HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity("Errores: " +e,HttpStatus.OK);
		}
	}
*/	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/detail/{id}")
	public ResponseEntity<Messages> getByid(@PathVariable("id") int id) {
		if (!msgsService.existsByid(id)) {
			logs.warn("Mensaje no encontrado - detail - MessagesController");
			return new ResponseEntity(new Mensaje("No existe el mensaje"), HttpStatus.NOT_FOUND);
		} else {
			Messages msg = msgsService.getOne(id).get();
			logs.info("Mensaje encontrado - detail - MessagesController");
			return new ResponseEntity<Messages>(msg, HttpStatus.OK);
		}
	}
	
	@PutMapping("/logicaldelete/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id) {
		try {
			Messages msg = msgsService.getOne(id).get();
			msg.setDeleteAt(new Date());
			msgsService.save(msg);
			logs.info("Mensaje actualizado - logicaldelete - MessagesController");
			return new ResponseEntity<>(new Mensaje("Mensaje eliminado"), HttpStatus.OK);
		} catch (Exception e) {
			logs.error("Ocurrio un error en el MessagesController");
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!msgsService.existsByid(id)) {
			logs.warn("Mensaje no encontrado - delete - ChatsController");
			return new ResponseEntity(new Mensaje("No existe chat"), HttpStatus.NOT_FOUND);
		} else {
			msgsService.delete(id);
			logs.info("Mensaje eliminado - delete - ChatsController");
			return new ResponseEntity(new Mensaje("Chat eliminado"), HttpStatus.OK);
		}
	}

}