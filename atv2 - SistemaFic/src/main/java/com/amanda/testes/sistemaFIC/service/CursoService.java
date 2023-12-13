/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amanda.testes.sistemaFIC.service;

import com.amanda.testes.sistemaFIC.models.entity.Curso;
import com.amanda.testes.sistemaFIC.models.repository.CursoRepository;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;

    public List<Curso> findAll(){
        return repository.findAll();
    }

    public Curso findById(Integer id){
        Optional<Curso> obj = repository.findById(id);
        return obj.get();
    }

    public Curso save(Curso obj){
        this.validate(obj);
        return repository.save(obj);
    }

    private void validate(Curso obj) {
        //Verifica se os campos são validos
        if (!obj.isValidFields()) {
            throw new IllegalArgumentException("Preencha todos os campos!");
        }
    }
}