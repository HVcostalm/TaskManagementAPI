package com.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagement.entities.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

}
