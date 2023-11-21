package com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}