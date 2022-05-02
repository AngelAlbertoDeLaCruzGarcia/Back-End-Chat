package com.backEnd.springboot.apirest.repositorio;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.backEnd.springboot.apirest.entity.TypeMsgs;

public interface TypeMsgsRepository extends CrudRepository<TypeMsgs, Integer>{

	public List<TypeMsgs> findByNameLike(String name);

}
