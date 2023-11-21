package com.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.entities.Curso;
import com.projeto.services.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cursos", description = "API REST DE GERENCIAMENTO DE Cursos")
@RestController
@RequestMapping("/cursos")
public class CursoController {
    
    private final CursoService cursoService;
    
    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Localiza Curso por ID")
    public ResponseEntity<Curso> getProductById(@PathVariable Long id) {
    	Curso curso = cursoService.getCursoById(id);
        if (curso != null) {
            return ResponseEntity.ok(curso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    @Operation(summary = "Apresenta todos os Cursos")
    public ResponseEntity<List<Curso>> getAllCursos() {
        List<Curso> cursos = cursoService.getAllCursos();
        return ResponseEntity.ok(cursos);
    }
    @PostMapping("/")
    @Operation(summary = "Cadastra um Curso")
    public ResponseEntity<Curso> criarCurso(@RequestBody @Valid Curso curso) {
    	Curso criarCurso= cursoService.salvarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarCurso);
    }
   

    @PutMapping("/{id}")
    @Operation(summary = "Altera o Curso")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody @Valid Curso curso) {
    	Curso updatedCurso = cursoService.updateCurso(id, curso);
        if (updatedCurso != null) {
            return ResponseEntity.ok(updatedCurso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta o Curso")
    public ResponseEntity<String> deleteCurso(@PathVariable Long id) {
        boolean deleted = cursoService.deleteCurso(id);
        if (deleted) {
        	 return ResponseEntity.ok().body("O curso foi excluído com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}