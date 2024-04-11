package com.student_information_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student_information_system.entities.Administartor_Entity;

@Repository
public interface Administartor_repo extends JpaRepository<Administartor_Entity, String>  {

	
	
}
