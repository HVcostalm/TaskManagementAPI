package com.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagement.entities.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{

}
