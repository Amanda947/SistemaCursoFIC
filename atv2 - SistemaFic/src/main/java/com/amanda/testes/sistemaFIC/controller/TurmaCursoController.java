/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amanda.testes.sistemaFIC.controller;

import com.amanda.testes.sistemaFIC.service.TurmaCursoService;
import com.amanda.testes.sistemaFIC.models.entity.TurmaCurso;
import com.amanda.testes.sistemaFIC.models.entity.EstudantesMatriculados;
import com.amanda.testes.sistemaFIC.service.EstudantesMatriculadosService;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author compo
 */
@RestController
@RequestMapping("turma")
public class TurmaCursoController {

    @Autowired
    private TurmaCursoService service;

    @Autowired
    private EstudantesMatriculadosService estudantesMatriculadosService;

    
    @GetMapping
    public ResponseEntity<List<TurmaCurso>> list() {
        List<TurmaCurso> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<TurmaCurso> findById(@PathVariable Integer id){
        TurmaCurso obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @PostMapping
    public ResponseEntity<TurmaCurso> save(@RequestBody TurmaCurso obj) {
        TurmaCurso obj_saved = service.save(obj);
        return ResponseEntity.ok().body(obj_saved);
    }
    
    @PostMapping("/matricular")
    public ResponseEntity<String> save(@RequestBody EstudantesMatriculados matricula) {
        EstudantesMatriculados obj_saved = estudantesMatriculadosService.save(matricula);
        return ResponseEntity.ok("Estudante matriculado");
    }
}
