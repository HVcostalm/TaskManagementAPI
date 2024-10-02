package com.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagement.entities.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long>{

}
