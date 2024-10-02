package com.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagement.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
}
