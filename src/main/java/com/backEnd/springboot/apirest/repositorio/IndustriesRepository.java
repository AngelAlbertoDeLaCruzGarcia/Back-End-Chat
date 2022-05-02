package com.backEnd.springboot.apirest.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backEnd.springboot.apirest.entity.Industries;

public interface IndustriesRepository extends JpaRepository<Industries, Integer> {

	Optional<Industries> findByuuid(Long uuid);
	boolean existsByuuid(Long uuid);
}
