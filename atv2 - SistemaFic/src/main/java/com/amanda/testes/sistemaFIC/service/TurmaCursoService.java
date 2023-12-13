/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amanda.testes.sistemaFIC.service;

import com.amanda.testes.sistemaFIC.models.entity.TurmaCurso;
import com.amanda.testes.sistemaFIC.models.repository.TurmaCursoRepository;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmaCursoService {

    @Autowired
    private TurmaCursoRepository repository;

    public List<TurmaCurso> findAll() {
        return repository.findAll();
    }

    public TurmaCurso findById(Integer id) {
        Optional<TurmaCurso> obj = repository.findById(id);
        if (obj.isPresent() && obj.get().getEstudantesMatriculadoses().isEmpty()) {
            throw new IllegalArgumentException("Turma não tem estudantes");

        }
        return obj.get();
    }

    public TurmaCurso save(TurmaCurso obj) {
        this.validate(obj);
        return repository.save(obj);
    }

    private void validate(TurmaCurso obj) {
        if (!obj.isValidFields()) {
            throw new IllegalArgumentException("Preencha todos os campos corretamente!");
        }
    }
}
