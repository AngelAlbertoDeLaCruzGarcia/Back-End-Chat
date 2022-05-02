package com.backEnd.springboot.apirest.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backEnd.springboot.apirest.dto.IndustriesDto;
import com.backEnd.springboot.apirest.entity.Industries;
import com.backEnd.springboot.apirest.excepciones.Mensaje;
import com.backEnd.springboot.apirest.service.IndustriesService;
import com.cloudinary.utils.StringUtils;



@RestController
@RequestMapping("/industries")
@CrossOrigin(origins = "http://localhost:9090")
public class IndustriesController {
@Autowired
IndustriesService industriesService;
Log logs = LogFactory.getLog(getClass());

@GetMapping("/list")
public ResponseEntity<List<Industries>> list(){
	List<Industries> list = industriesService.list();
	return new ResponseEntity<List<Industries>>(list,HttpStatus.OK);
	
}

@SuppressWarnings({ "unchecked", "rawtypes" })
@GetMapping("/detail/{id}")
public ResponseEntity<Industries> getByid(@PathVariable("id") int id) {
	if (!industriesService.existsByid(id)) {
		return new ResponseEntity(new Mensaje("No existe el cliente"), HttpStatus.NOT_FOUND);
	} else {
		Industries industries = industriesService.getOne(id).get();
		return new ResponseEntity<Industries>(industries, HttpStatus.OK);
	}
}

@SuppressWarnings({ "unchecked", "rawtypes" })
@PostMapping(value = "/create")
public ResponseEntity<?>create(@RequestBody IndustriesDto industriesdto){
try {
	
	if (StringUtils.isBlank(industriesdto.getIndustrie())) {
		logs.warn("Error de validacion - Create - IndustriesController");
		return new ResponseEntity(new Mensaje("El nombre es requerido"),HttpStatus.BAD_REQUEST);	
	}
	industriesdto.setCreateAt(new Date());
	Industries industries = new Industries(industriesdto.getIndustrie(),industriesdto.getCreateAt(),industriesdto.getUpdateAt(),industriesdto.getDeleteAt());
	industriesService.save(industries);
	logs.info("Se registro una industrie correctamente - Create - IndustriesController");
	return new ResponseEntity(new Mensaje("Industria registrada correctamente"), HttpStatus.OK);
	
} catch (Exception e) {
	logs.error("Ocurrio un error en el IndustrieController");
	return new ResponseEntity(new Mensaje("ocurrio un error al crear una industria"), HttpStatus.BAD_REQUEST);	
}	
}
@SuppressWarnings({ "unchecked", "rawtypes" })
@PutMapping("/update/{id}")
public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody IndustriesDto industriesdto) {
	try {
	
		if (!industriesService.existsByid(id)) {
			logs.warn("No existe la industria - update - IndustriaController");
			return new ResponseEntity(new Mensaje("No existe la industria"), HttpStatus.NOT_FOUND);
		} else if (StringUtils.isBlank(industriesdto.getIndustrie())) {
			logs.warn("Error de validacion - update - IndustriesController");
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		}
			Industries industries = industriesService.getOne(id).get();
			industries.setIndustrie(industriesdto.getIndustrie());
			industries.setUpdateAt(new Date());
			industriesService.save(industries);
			logs.info("Se actualizo una industria - update - IndustrieController");
			return new ResponseEntity(new Mensaje("Industria actualizada"), HttpStatus.OK);
	} catch (Exception e) {
		logs.error("Ocurrio un error en el IndustrieController");
		return new ResponseEntity(new Mensaje("ocurrio un error al actualizar la industria"), HttpStatus.BAD_REQUEST);		}
	}
@SuppressWarnings({ "unchecked", "rawtypes" })
@DeleteMapping("/delete/{id}")
public ResponseEntity<?> delete(@PathVariable("id") int id){
	if (!industriesService.existsByid(id)) {
		return new ResponseEntity(new Mensaje("No existe la industria"), HttpStatus.NOT_FOUND);
	}else {
		industriesService.delete(id);
		return new ResponseEntity(new Mensaje("industria eliminada"), HttpStatus.OK);
	}
}
}
