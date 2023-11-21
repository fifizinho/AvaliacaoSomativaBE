package com.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.entities.Curso;
import com.projeto.repository.CursoRepository;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;
    
    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Curso getCursoById(Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        return curso.orElse(null);
    }

    public Curso salvarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso updateCurso(Long id, Curso updatedCurso) {
        Optional<Curso> existingCurso = cursoRepository.findById(id);
        if (existingCurso.isPresent()) {
            updatedCurso.setId(id);
            return cursoRepository.save(updatedCurso);
        }
        return null;
    }
    public boolean deleteCurso(Long id) {
        Optional<Curso> existingCurso = cursoRepository.findById(id);
        if (existingCurso.isPresent()) {
        	cursoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}