package com.student_information_system.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student_information_system.entities.Administartor_Entity;
import com.student_information_system.entities.Student_Entity;
@Repository
public interface Student_repo extends JpaRepository<Student_Entity, String> {

	Optional<Administartor_Entity> findByname(String name);

}
