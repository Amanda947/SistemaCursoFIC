/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amanda.testes.sistemaFIC.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author compo
 */
@Entity
@Table(name = "tb_curso")
public class Curso implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String nome, cargaHoraria, descricao;
    
    @OneToMany(mappedBy = "curso", cascade = CascadeType.PERSIST)
    private List<TurmaCurso> turmas = new ArrayList();

    public Curso(String nome, String cargaHoraria, String descricao) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.descricao = descricao;
    }

    public Curso() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<TurmaCurso> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<TurmaCurso> turmas) {
        this.turmas = turmas;
    }
    
    public boolean isValidFields() {
    if (nome == null || nome.isEmpty()) {
        return false;
    }
    if (cargaHoraria == null || cargaHoraria.isEmpty()) {
        return false;
    }
    if (descricao == null || descricao.isEmpty()) {
        return false;
    }
    return true;
}

}
