package com.taskmanagement.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagement.entities.FuncionarioProjeto;

public interface FuncionarioProjetoRepository extends JpaRepository<FuncionarioProjeto, Date>{

}
