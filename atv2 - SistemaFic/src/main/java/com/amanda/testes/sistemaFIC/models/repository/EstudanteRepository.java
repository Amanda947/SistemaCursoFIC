/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amanda.testes.sistemaFIC.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.amanda.testes.sistemaFIC.models.entity.Estudante;


@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, Integer> {
}

