package com.backEnd.springboot.apirest.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.backEnd.springboot.apirest.service.TypeMsgsService;
import com.cloudinary.utils.StringUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.backEnd.springboot.apirest.dto.TypeMsgsDto;
import com.backEnd.springboot.apirest.entity.TypeMsgs;
import com.backEnd.springboot.apirest.excepciones.Mensaje;

@RestController
@RequestMapping("/typemsgs")
public class TypeMsgsController {

	@Autowired
	TypeMsgsService tMsgService;
	Log logs = LogFactory.getLog(getClass());

	@GetMapping("/list")
	public ResponseEntity<List<TypeMsgs>> list() {
		List<TypeMsgs> list = tMsgService.list();
		return new ResponseEntity<List<TypeMsgs>>(list, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/detail/{id}")
	public ResponseEntity<TypeMsgs> getByid(@PathVariable("id") int id) {
		if (!tMsgService.existsById(id)) {
			logs.warn("Tipo de mensaje no encontrado - detail - TypeMsgsController");
			return new ResponseEntity(new Mensaje("No existe el chat "), HttpStatus.NOT_FOUND);
		} else {
			TypeMsgs tMsg = tMsgService.getOne(id).get();
			logs.info("Tipo de mensaje encontrado - detail - TypeMsgsController");
			return new ResponseEntity<TypeMsgs>(tMsg, HttpStatus.OK);
		}
	}

	@GetMapping("/details")
	public ResponseEntity<List<TypeMsgs>> getByName(@RequestParam("name") String name) {
		List<TypeMsgs> tMsg = tMsgService.getByName(name);
		return new ResponseEntity<List<TypeMsgs>>(tMsg, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody TypeMsgsDto tMsgDto) {
		try {
			if (StringUtils.isBlank(tMsgDto.getName())) {
				logs.warn("Error de validacion - create - TypeMsgsController");
				return new ResponseEntity(new Mensaje("El nombre es requerido"), HttpStatus.BAD_REQUEST);
			}
			tMsgDto.setCreateAt(new Date());
			TypeMsgs tMsg = new TypeMsgs(tMsgDto.getName(), tMsgDto.getCreateAt(), tMsgDto.getUpdateAt(),
					tMsgDto.getDeleteAt());
			tMsgService.save(tMsg);
			logs.info("Se registro un Tipo de mensaje de chat - Create - TypeMsgsController");
			return new ResponseEntity(new Mensaje("Tipo de mensaje de chat registrado correctamente"), HttpStatus.OK);
		} catch (Exception e) {
			logs.error("Ocurrio un error en el TypeMsgsController");
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings("unused")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody TypeMsgsDto tMsgDto, @PathVariable("id") int id) {
		try {
			if (StringUtils.isBlank(tMsgDto.getName())) {
				logs.warn("Error de validacion - update - TypeMsgsController");
				return new ResponseEntity<>(new Mensaje("El nombre es requerido"), HttpStatus.BAD_REQUEST);
			}
			TypeMsgs type = tMsgService.getOne(id).get();
			type.setName(tMsgDto.getName());
			type.setUpdateAt(new Date());
			tMsgService.save(type);
			logs.info("Tipo de mensaje actualizado - update - TypeMsgsController");
			return new ResponseEntity<>(new Mensaje("Tipo de mensaje de chat actualizado"), HttpStatus.OK);
		} catch (Exception e) {
			logs.error("Ocurrio un error en el TypeMsgsController");
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!tMsgService.existsById(id)) {
			logs.warn("Tipo de mensaje no encontrado - delete - TypeMsgsController");
			return new ResponseEntity(new Mensaje("No existe tipo de mensaje de chat"), HttpStatus.NOT_FOUND);
		} else {
			tMsgService.delete(id);
			logs.info("Tipo de mensaje eliminado - delete - TypeMsgsController");
			return new ResponseEntity(new Mensaje("Tipo de mensaje de chat eliminado"), HttpStatus.OK);
		}
	}
}
