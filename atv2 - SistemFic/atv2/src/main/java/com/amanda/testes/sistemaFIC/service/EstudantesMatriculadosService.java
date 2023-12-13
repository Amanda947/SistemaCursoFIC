/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amanda.testes.sistemaFIC.service;

import com.amanda.testes.sistemaFIC.models.entity.EstudantesMatriculados;
import com.amanda.testes.sistemaFIC.models.entity.TurmaCurso;

import com.amanda.testes.sistemaFIC.models.repository.EstudantesMatriculadosRepository;
import com.amanda.testes.sistemaFIC.models.repository.TurmaCursoRepository;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudantesMatriculadosService {
    @Autowired
    private EstudantesMatriculadosRepository repository;

    @Autowired
    private TurmaCursoRepository turmaRepository;

    
    public List<EstudantesMatriculados> findAll(){
        return repository.findAll();
    }

    public EstudantesMatriculados findById(Integer id){
        Optional<EstudantesMatriculados> obj = repository.findById(id);
        return obj.get();
    }

    public EstudantesMatriculados save(EstudantesMatriculados obj){
        this.validate(obj);
        EstudantesMatriculados objSaved = repository.save(obj);
        
        // Atualiza a quantidade de vagas disponiveis do curso
        TurmaCurso turma = objSaved.getTurmaCurso();
        Integer vagasDisponiveis = turma.getVagasDisponiveis() - 1;
        turma.setVagasDisponiveis(vagasDisponiveis);
        turmaRepository.save(turma);
        
        return objSaved;
    }

    private void validate(EstudantesMatriculados obj) {
        //Verifica se os campos são validos
        if (!obj.podeMatricular()) {
            throw new IllegalArgumentException("Estudante não pode ser matriculado.!");
        }
    }
}