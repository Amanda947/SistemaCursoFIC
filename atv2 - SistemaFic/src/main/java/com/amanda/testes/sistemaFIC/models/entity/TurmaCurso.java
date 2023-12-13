/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amanda.testes.sistemaFIC.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author compo
 */
@Entity
@Table(name = "tb_turma_curso")
public class TurmaCurso implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String local;
    private Integer vagas, vagasDisponiveis;
    private LocalDate inicioAulas, fimAulas, inicioMatriculas, fimMatriculas;

    @OneToMany(mappedBy = "turmaCurso", cascade = CascadeType.PERSIST)
    private List<EstudantesMatriculados> estudantesMatriculadoses = new ArrayList();

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    public TurmaCurso(String local, Integer vagas, LocalDate inicioAulas, LocalDate fimAulas, LocalDate inicioMatriculas, LocalDate fimMatriculas, Curso curso) {
        this.local = local;
        this.vagas = vagas;
        this.vagasDisponiveis = vagas;
        this.inicioAulas = inicioAulas;
        this.fimAulas = fimAulas;
        this.inicioMatriculas = inicioMatriculas;
        this.fimMatriculas = fimMatriculas;
        this.curso = curso;
    }

    public TurmaCurso() {
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Integer getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(Integer vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public LocalDate getInicioAulas() {
        return inicioAulas;
    }

    public void setInicioAulas(LocalDate inicioAulas) {
        this.inicioAulas = inicioAulas;
    }

    public LocalDate getFimAulas() {
        return fimAulas;
    }

    public void setFimAulas(LocalDate fimAulas) {
        this.fimAulas = fimAulas;
    }

    public LocalDate getInicioMatriculas() {
        return inicioMatriculas;
    }

    public void setInicioMatriculas(LocalDate inicioMatriculas) {
        this.inicioMatriculas = inicioMatriculas;
    }

    public LocalDate getFimMatriculas() {
        return fimMatriculas;
    }

    public void setFimMatriculas(LocalDate fimMatriculas) {
        this.fimMatriculas = fimMatriculas;
    }

    public List<EstudantesMatriculados> getEstudantesMatriculadoses() {
        return estudantesMatriculadoses;
    }

    public void setEstudantesMatriculadoses(List<EstudantesMatriculados> estudantesMatriculadoses) {
        this.estudantesMatriculadoses = estudantesMatriculadoses;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<LocalDate> getPeriodoMatriculas() {
        List<LocalDate> list = new ArrayList();
        list.add(inicioMatriculas);
        list.add(fimMatriculas);
        return list;
    }

    public boolean isValidFields() {
        if (local == null || local.isEmpty()) {
            return false;
        }
        if (vagas == null || vagas < 0) {
            return false;
        }
        if (vagasDisponiveis == null || vagasDisponiveis < 0 || vagasDisponiveis > vagas) {
            return false;
        }
        if (inicioAulas == null || fimAulas == null || inicioMatriculas == null || fimMatriculas == null) {
            return false;
        }
        if (inicioMatriculas.isAfter(inicioAulas) || inicioMatriculas.isAfter(fimAulas) || fimMatriculas.isAfter(inicioAulas) || fimMatriculas.isAfter(fimAulas)) {
            return false;
        }
        return true;
    }
}
