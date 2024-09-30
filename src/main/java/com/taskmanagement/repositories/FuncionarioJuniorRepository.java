package com.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagement.entities.FuncionarioJunior;

public interface FuncionarioJuniorRepository extends JpaRepository<FuncionarioJunior, Long> {
	
}
